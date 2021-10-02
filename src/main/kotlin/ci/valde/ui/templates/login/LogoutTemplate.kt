package ci.valde.ui.templates.login

import ci.valde.ui.Endpoints
import ci.valde.ui.templates.GeneralViewTemplate
import io.ktor.html.*
import kotlinx.html.*

class LogoutTemplate(private val basicTemplate: GeneralViewTemplate = GeneralViewTemplate()) : Template<HTML> {
    override fun HTML.apply() {
        insert(basicTemplate) {
            menu {
                menuItems {
                    a(classes = "nav-link", href = Endpoints.HOME.url) {
                        +"Home"
                    }
                }
                menuItems {
                    a(classes = "nav-link", href = Endpoints.LOGOUT.url) {
                        +"Login"
                    }
                }
            }
            content {
                div(classes = "mt-2") {
                    h2 {
                        +"You have been logged out!"
                    }
                }
            }
        }
    }

}