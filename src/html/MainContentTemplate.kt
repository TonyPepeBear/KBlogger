package com.tonypepe.html

import io.ktor.html.*
import kotlinx.html.FlowContent
import kotlinx.html.div

class MainContentTemplate : Template<FlowContent> {
    val content = Placeholder<FlowContent>()

    override fun FlowContent.apply() {
        div(classes = "container px-4 px-lg-5") {
            div(classes = "row gx-4 gx-lg-5 justify-content-center") {
                div(classes = "col-md-10 col-xl-8") {
                    insert(content)
                }
                div(classes = "col-xl-4 gy-4") {
                    div(classes = "card mb-4") {
                        div(classes = "card-header") { +"About" }
                        div(classes = "card-body") { +"About Me!!" }
                    }
                }
            }
        }
    }
}
