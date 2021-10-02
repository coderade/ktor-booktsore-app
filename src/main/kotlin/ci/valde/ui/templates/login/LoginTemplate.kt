package ci.valde.ui.templates.login

import ci.valde.ui.Endpoints
import ci.valde.ui.templates.GeneralViewTemplate
import io.ktor.html.*
import kotlinx.html.*

class LoginTemplate(private val basicTemplate: GeneralViewTemplate = GeneralViewTemplate()) : Template<HTML> {
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
                        insert(greeting)
                    }
                }
                form(
                    method = FormMethod.post,
                    encType = FormEncType.multipartFormData,
                    action = Endpoints.LOGIN.url
                ) {
                    div(classes = "mb-3") {
                        input(classes = "form-control", type = InputType.text, name = "username") {
                            this.placeholder = "Type your username here..."
                            this.attributes["aria-label"] = "Username"
                            this.attributes["aria-describedby"] = "basic-addon1"
                        }
                    }
                    div(classes = "mb-3") {
                        input(classes = "form-control", type = InputType.text, name = "password") {
                            this.placeholder = "Type your password here..."
                            this.attributes["aria-label"] = "Password"
                            this.attributes["aria-describedby"] = "basic-addon1"
                        }
                    }
                    div(classes = "mb-3") {
                        button(classes = "btn btn-primary", type = ButtonType.submit) {
                            +"Login"
                        }
                    }
                }
            }
        }
    }

}