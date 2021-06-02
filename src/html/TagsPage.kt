package com.tonypepe.html

import io.ktor.html.*
import kotlinx.html.HTML
import kotlinx.html.div

fun HTML.tagsPageHtml(tags: Map<String, Int>) {
    insert(PageTemplate()) {
        head { myHead("Tags") }
        content {
            insert(MainContentTemplate()) {
                content {
                    tags.keys.forEach {
                        div(classes = "chip chip-md success-color white-text  example z-depth-2 mr-0") {
                            +it
                        }
                    }
                }
            }
        }
    }
}
