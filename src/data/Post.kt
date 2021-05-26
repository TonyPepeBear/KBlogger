package com.tonypepe.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.commonmark.ext.front.matter.YamlFrontMatterExtension
import org.commonmark.ext.front.matter.YamlFrontMatterVisitor
import org.commonmark.parser.Parser
import org.commonmark.renderer.html.HtmlRenderer
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.io.path.extension
import kotlin.io.path.isRegularFile
import kotlin.io.path.readText
import kotlin.streams.toList

data class Post(
    val title: String,
    val preview: String,
    val date: String,
    val htmlContent: String
)

object Posts {
    var posts = listOf<Post>()

    fun initPosts() = GlobalScope.launch(Dispatchers.IO) {
        val paths = Files.walk(Paths.get("md-content"))
            .filter { it.isRegularFile() && it.extension == "md" }
            .toList()
        val parser = Parser.builder()
            .extensions(listOf(YamlFrontMatterExtension.create()))
            .build()
        val yamlVisitor = YamlFrontMatterVisitor()
        val render = HtmlRenderer.builder().build()

        val posts = mutableListOf<Post>()
        paths.forEach { path ->
            val text = path.readText()
            val parse = parser.parse(text)
            parse.accept(yamlVisitor)
            val content = render.render(parse)

            posts.add(
                Post(
                    yamlVisitor.data["title"]?.get(0) ?: "",
                    yamlVisitor.data["preview"]?.get(0) ?: "",
                    yamlVisitor.data["date"]?.get(0) ?: "",
                    content
                )
            )
        }

        this@Posts.posts = posts.sortedByDescending { it.date }
    }
}
