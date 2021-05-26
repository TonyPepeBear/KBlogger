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
