package com.tonypepe.html

import com.tonypepe.data.Post
import io.ktor.html.*
import kotlinx.html.*

fun HTML.indexHtml(posts: List<Post>) {
    insert(PageTemplate()) {
        head {
            myHead("KBlogger")
        }
        content {
            insert(MainContent()) {
                content {
                    posts.forEach { post ->
                        postPreview(post)
                    }
                    div(classes = "d-flex justify-content-end mb-4") {
                        a(href = "#", classes = "btn btn-outline-dark text-uppercase") {
                            +"Other Posts"
                        }
                    }
                }
            }
        }
    }
}

@HtmlTagMarker
fun FlowContent.postPreview(post: Post) {
    div(classes = "post-preview") {
        a(href = "#") {
            h2(classes = "post-title") {
                +post.title
            }
        }
        p {
            +post.preview
        }
        p(classes = "post-meta") {
            +"Posted by "
            a(href = "#") { +"TonyPepe" }
            +" on ${post.date}"
        }
    }
    hr(classes = "my-4")
}
