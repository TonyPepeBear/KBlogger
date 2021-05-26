package com.tonypepe.html

import com.tonypepe.data.Post
import io.ktor.html.*
import kotlinx.html.HTML
import kotlinx.html.article
import kotlinx.html.div
import kotlinx.html.unsafe

fun HTML.postHtml(post: Post) {
    insert(PageTemplate()) {
        head {
            myHead(post.title)
        }
        content {
            article(classes = "mb-4") {
                insert(MainContent()) {
                    content {
                        div {
                            unsafe { +post.htmlContent }
                        }
                    }
                }
            }
        }
    }
}

