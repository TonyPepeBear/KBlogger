package com.tonypepe.html

import io.ktor.html.*
import kotlinx.html.FlowContent
import kotlinx.html.div

class MainContentTemplage : Template<FlowContent> {
    val content = Placeholder<FlowContent>()

    override fun FlowContent.apply() {
        div(classes = "container px-4 px-lg-5") {
            div(classes = "row gx-4 gx-lg-5 justify-content-center") {
                div(classes = "col-md-10 col-lg-8 col-xl-7") {
                    insert(content)
                }
            }
        }
    }
}