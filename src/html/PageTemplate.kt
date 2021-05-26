package com.tonypepe.html

import com.tonypepe.data.ServerConfig
import io.ktor.html.*
import kotlinx.html.*

class PageTemplate(
    private val serverConfig: ServerConfig = ServerConfig.instance,
    private val pageTitle: String = serverConfig.site_author,
    private val pageSubTitle: String = "A Blog Launch by Ktor",
) : Template<HTML> {
    val head = Placeholder<HEAD>()
    val content = Placeholder<BODY>()

    override fun HTML.apply() {
        head {
            insert(head)
        }
        body {
            navBar()
            insert(PageHeader()) {
                content {
                    h1 { +pageTitle }
                    span(classes = "subheading") {
                        +pageSubTitle
                    }
                }
            }
            insert(content)
            comment("Footer")
            myFooter(
                serverConfig.site_author,
                serverConfig.author_twitter_url,
                serverConfig.author_facebook_url,
                serverConfig.author_github_url
            )
            comment("Bootstrap core JS")
            script(src = "https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js") {}
            comment("Core theme JS")
            script(src = "/js/scripts.js") {}
            comment("highlight.js")
            script(src = "https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.7.2/highlight.min.js") {}
            script { +"hljs.highlightAll();" }
        }
    }
}

@HtmlTagMarker
fun HEAD.myHead(title: String) {
    meta {
        attributes["charset"] = "utf-8"
    }
    meta {
        attributes["name"] = "viewport"
        attributes["content"] = "with=device-width, initial-scale=1, shrink-to-fit=no"
    }
    title { +title }
    comment("Font Awesome icons")
    script(src = "https://use.fontawesome.com/releases/v5.15.3/js/all.js") {
        attributes["crossorigin"] = "anonymous"
    }
    comment("Google fonts")
    link(
        rel = "preconnect",
        href = "https://fonts.gstatic.com"
    )
    link(
        href = "https://fonts.googleapis.com/css2?family=Noto+Serif+TC:wght@500&display=swap",
        rel = "stylesheet"
    )
    link(
        href = "https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic",
        rel = "stylesheet",
        type = "text/css"
    )
    link(
        href = "https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800",
        rel = "stylesheet",
        type = "text/css"
    )
    comment("Core theme CSS (includes Bootstrap)")
    link(
        href = "/css/styles.css",
        rel = "stylesheet"
    )
    comment("highlight.js")
    link(
        href = "https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.7.2/styles/darcula.min.css",
        rel = "stylesheet"
    )
}

@HtmlTagMarker
fun FlowContent.navBar() {
    comment("Navigation")
    nav(classes = "navbar navbar-expand-lg navbar-light") {
        attributes["id"] = "mainNav"
        div(classes = "container px-4 pl-lg-5") {
            a(classes = "navbar-brand", href = "/") { +"KBlogger" }
            button(classes = "navbar-toggler", type = ButtonType.button) {
                attributes["data-bs-toggle"] = "collapse"
                attributes["data-bs-target"] = "#navbarResponsive"
                attributes["aria-controls"] = "navbarResponsive"
                attributes["aria-expanded"] = "false"
                attributes["aria-label"] = "Toggle navigation"
                +"Menu"
                i(classes = "fas fa-bars")
            }
            div(classes = "collapse navbar-collapse") {
                attributes["id"] = "navbarResponsive"
                ul(classes = "navbar-nav ms-auto py-4 py-lg-0") {
                    li(classes = "nav-item") {
                        a(
                            classes = "nav-link px-lg-3 py-3 py-lg-4",
                            href = "/"
                        ) {
                            +"Home"
                        }
                    }
                }
            }
        }
    }
}

class PageHeader : Template<BODY> {
    val content = Placeholder<FlowContent>()

    override fun BODY.apply() {
        header(classes = "masthead") {
            attributes["style"] =
                "background-image: url('https://raw.githubusercontent.com/StartBootstrap/startbootstrap-clean-blog/master/src/assets/img/home-bg.jpg'"
            div(classes = "container position-relative px-4 px-lg-5") {
                div(classes = "row gx-4 gx-lg-5 justify-content-center") {
                    div(classes = "col-md-10 col-lg-8 col-xl-7") {
                        div(classes = "site-heading") {
                            insert(content)
                        }
                    }
                }
            }
        }
    }
}

@HtmlTagMarker
fun FlowContent.myFooter(author: String, twitterURL: String = "", facebookURL: String = "", githubURL: String = "") {
    footer(classes = "border-top") {
        div(classes = "container px-4 px-lg-5") {
            div(classes = "row gx-4 gx-lg-5 justify-content-center") {
                div(classes = "col-md-10 col-lg-8 col-xl-7") {
                    ul(classes = "list-inline text-center") {
                        li(classes = "list-inline-item") {
                            a(href = twitterURL) {
                                span(classes = "fa-stack fa-lg") {
                                    i(classes = "fas fa-circle fa-stack-2x")
                                    i(classes = "fab fa-twitter fa-stack-1x fa-inverse")
                                }
                            }
                        }
                        li(classes = "list-inline-item") {
                            a(href = twitterURL) {
                                span(classes = "fa-stack fa-lg") {
                                    i(classes = "fas fa-circle fa-stack-2x")
                                    i(classes = "fab fa-facebook fa-stack-1x fa-inverse")
                                }
                            }
                        }
                        li(classes = "list-inline-item") {
                            a(href = githubURL) {
                                span(classes = "fa-stack fa-lg") {
                                    i(classes = "fas fa-circle fa-stack-2x")
                                    i(classes = "fab fa-github fa-stack-1x fa-inverse")
                                }
                            }
                        }
                    }
                    div(classes = "small text-center text-muted fst-italic") {
                        +"Copyright \u00a9 $author 2021"
                    }
                }
            }
        }
    }
}
