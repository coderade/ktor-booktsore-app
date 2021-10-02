package ci.valde.ui.templates

import io.ktor.html.*
import kotlinx.html.*

class NavTemplate : Template<FlowContent> {
    val menuItems = PlaceholderList<UL, FlowContent>()
    override fun FlowContent.apply() {
        div {
            nav(classes = "navbar navbar-expand-md navbar-dark bg-dark") {
                a(classes = "navbar-brand", href = "#") { +"My Bookstore" }

                button(
                    classes = "navbar-toggler",
                    type = ButtonType.button
                ) {
                    this.attributes["data-toggle"] = "collapse"
                    this.attributes["data-target"] = "#navbarExampleDefault"
                    this.attributes["aria-controls"] = "navbarExampleDefault"
                    this.attributes["aria-expanded"] = "false"
                    this.attributes["aria-label"] = "Toggle Navigation"
                    span(classes = "navbar-toggle-icon") {}
                }
                div(classes = "collapse navbar-collapse") {
                    this.id = "navbarExampleDefault"
                    ul(classes = "navbar-nav mr-auto") {
                        each(menuItems) {
                            li {
                                this.insert(it)
                            }
                        }
                    }
                }
            }

        }
    }

}