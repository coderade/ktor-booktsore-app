package ci.valde.ui.templates.home

import ci.valde.ui.Endpoints
import ci.valde.ui.templates.GeneralViewTemplate
import io.ktor.html.*
import kotlinx.html.*

class HomeTemplate(private val basicTemplate: GeneralViewTemplate = GeneralViewTemplate()) : Template<HTML> {
    val greeting = Placeholder<FlowContent>()
    override fun HTML.apply() {
        insert(basicTemplate) {
            menu {
                menuItems {
                    a(classes = "nav-link", href = Endpoints.HOME.url) {
                        +"Home"
                    }
                }
                menuItems {
                    a(classes = "nav-link", href = Endpoints.LOGIN.url) {
                        +"Login"
                    }
                }
                menuItems {
                    a(classes = "nav-link", href = Endpoints.LOGOUT.url) {
                        +"Logout"
                    }
                }
            }
            content {
                div(classes = "mt-2") {
                    h2 {
                        +"Welcome to the Bookstore"
                    }
                    p {
                        +"- We have many good deals on a lot of different topics"
                    }
                    p {
                        +"- Let us know if you are looking for something that we do not currently have"
                    }
                }
            }
        }
    }

}