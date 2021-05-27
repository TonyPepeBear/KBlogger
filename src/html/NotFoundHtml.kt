package com.tonypepe.html

import io.ktor.html.*
import kotlinx.html.HTML
import kotlinx.html.h1
import kotlinx.html.h2

fun HTML.notFoundHtml(message: String = "") {
    insert(PageTemplate()) {
        head { myHead("404 Not Found") }
        content {
            insert(MainContentTemplage()) {
                content {
                    h1 {
                        +"404 Not Fount"
                    }
                    h2 {
                        +"Oops Something Get Wrong!!"
                    }
                }
            }
        }
    }
}
