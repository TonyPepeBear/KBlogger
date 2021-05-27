package com.tonypepe.html

import com.tonypepe.data.Post
import io.ktor.html.*
import kotlinx.html.HTML
import kotlinx.html.article
import kotlinx.html.div
import kotlinx.html.unsafe

fun HTML.postHtml(post: Post) {
    insert(PageTemplate(pageTitle = post.title, pageSubTitle = "")) {
        head {
            myHead(post.title)
        }
        content {
            article(classes = "mb-4") {
                insert(MainContentTemplage()) {
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

fun HTML.postListHtml(posts: List<Post>) {
    insert(PageTemplate(pageTitle = "All Posts", pageSubTitle = "Keep on posting. ${posts.count()} posts in total.")) {
        head {
            myHead("All Posts")
        }
        content {
            insert(MainContentTemplage()) {
                content {
                    posts.forEach {
                        postPreview(it)
                    }
                }
            }
        }
    }
}
