package ci.valde.ui.templates

import io.ktor.html.*
import kotlinx.html.*

class GeneralViewTemplate: Template<HTML> {
    val content =  Placeholder<HtmlBlockTag>()
    val menu = TemplatePlaceholder<NavTemplate>()

    override fun HTML.apply() {
        head{
            link(
                rel = "stylesheet",
                href = "https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css",
                type = "text/css"
            ) {
                this.integrity = "sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
                this.attributes["crossorigin"] = "anonymous"
            }
        }

        body{
            insert(NavTemplate(), menu)

            div(classes = "container") {
                div(classes = "row") {
                    div(classes = "col-md-6 offset-md-3") {
                        insert(content)
                    }
                }
            }

            script(type="javascript",  src="https://code.jquery.com/jquery-3.5.1.slim.min.js"){
                integrity = "sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
                this.attributes["crossorigin"] = "anonymous"
            }
            script(type="javascript", src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"){
                integrity = "sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
                this.attributes["crossorigin"] = "anonymous"
            }
            script(type="javascript", src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"){
                integrity = "sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
                this.attributes["crossorigin"] = "anonymous"
            }
        }
    }
    
}