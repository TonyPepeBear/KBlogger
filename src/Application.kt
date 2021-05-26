package com.tonypepe

import com.fasterxml.jackson.databind.SerializationFeature
import com.tonypepe.data.Posts
import com.tonypepe.html.indexHtml
import com.tonypepe.html.notFoundHtml
import com.tonypepe.html.postHtml
import io.ktor.application.*
import io.ktor.client.*
import io.ktor.client.engine.apache.*
import io.ktor.features.*
import io.ktor.html.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.jackson.*
import io.ktor.routing.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }
    install(StatusPages) {
        status(HttpStatusCode.NotFound) {
            call.respondHtml { notFoundHtml() }
        }
    }

    val client = HttpClient(Apache) {}
    Posts.initPosts()

    routing {
        get("/") {
            call.respondHtml {
                indexHtml(Posts.data)
            }
        }

        get("/post/{id}") {
            val s = call.parameters["id"]
            println(s)
            val post = Posts.map[s]
            if (post != null) {
                call.respondHtml {
                    postHtml(post)
                }
            }
        }
    }

    routing {
        static("/css") {
            resources("css")
        }
        static("/js") {
            resources("js")
        }
    }
}

