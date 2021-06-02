package com.tonypepe.html

import com.tonypepe.data.Post
import io.ktor.html.*
import kotlinx.html.*

fun HTML.postHtml(post: Post) {
    insert(PageTemplate(pageTitle = post.title, pageSubTitle = "")) {
        head {
            myHead(post.title)
        }
        content {
            article(classes = "mb-4") {
                insert(MainContentTemplate()) {
                    content {
                        div {
                            unsafe { +post.htmlContent }
                        }
                        hr(classes = "my-4")
                        div(classes = "justify-content-center") {
                            post.tags.forEach { tag ->
                                a(classes = "border-bottom") {
                                    +"#$tag"
                                }
                                unsafe { +"&emsp;" }
                            }
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
            insert(MainContentTemplate()) {
                content {
                    posts.forEach {
                        postPreview(it)
                    }
                }
            }
        }
    }
}
