package com.tonypepe.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.commonmark.ext.front.matter.YamlFrontMatterExtension
import org.commonmark.ext.front.matter.YamlFrontMatterVisitor
import org.commonmark.ext.gfm.tables.TablesExtension
import org.commonmark.node.Image
import org.commonmark.parser.Parser
import org.commonmark.renderer.html.AttributeProvider
import org.commonmark.renderer.html.HtmlRenderer
import org.eclipse.jgit.api.Git
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.io.path.extension
import kotlin.io.path.isRegularFile
import kotlin.io.path.name
import kotlin.io.path.readText
import kotlin.streams.toList

data class Post(
    val title: String,
    val preview: String,
    val date: String,
    val htmlContent: String,
    var id: String = ""
)

object Posts {
    var data = listOf<Post>()
    var map = mapOf<String, Post>()

    fun initPosts() = GlobalScope.launch(Dispatchers.IO) {
        if (File("md-content").exists().not()) {
            Git.cloneRepository()
                .setURI(ServerConfig.instance.git_repo_url)
                .setDirectory(File("md-content"))
                .call()
        }
        val paths = Files.walk(Paths.get("md-content"))
            .filter { it.isRegularFile() && it.extension == "md" }
            .toList()
        val extensions = listOf(YamlFrontMatterExtension.create(), TablesExtension.create())
        val parser = Parser.builder()
            .extensions(extensions)
            .build()
        val render = HtmlRenderer.builder()
            .attributeProviderFactory {
                AttributeProvider { node, tagName, attributes ->
                    if (node is Image) {
                        attributes["class"] = "img-fluid"
                        attributes["alt"] = "Responsive image"
                    }
                    if (tagName == "table") {
                        attributes["class"] = "table table-striped table-bordered"
                    }
                }
            }
            .extensions(extensions)
            .build()

        val posts = mutableListOf<Post>()
        val m = mutableMapOf<String, Post>()
        paths.forEach { path ->
            val yamlVisitor = YamlFrontMatterVisitor()
            val text = path.readText()
            val parse = parser.parse(text)
            parse.accept(yamlVisitor)
            val content = render.render(parse)

            val element = Post(
                yamlVisitor.data["title"]?.getOrNull(0) ?: "",
                yamlVisitor.data["preview"]?.getOrNull(0) ?: "",
                yamlVisitor.data["date"]?.getOrNull(0) ?: "",
                content,
            )
            if (element.title.isBlank()) {
                println("Skip: ${path.name}")
                return@forEach
            }
            println(element.title)
            element.id = element.title.toMD5()
            posts.add(element)
            m[element.id] = element
            yamlVisitor.data
        }

        this@Posts.data = posts.sortedByDescending { it.date }
        this@Posts.map = m
    }

    suspend fun forceReload() {
        File("md-content").deleteRecursively()
        initPosts().join()
    }
}
