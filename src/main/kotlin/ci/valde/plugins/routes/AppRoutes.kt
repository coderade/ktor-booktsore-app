package ci.valde.plugins.routes

import ci.valde.Constants
import ci.valde.plugins.auth.SecurityHandler
import ci.valde.ui.Endpoints
import ci.valde.ui.templates.home.HomeTemplate
import ci.valde.ui.templates.login.LoginSuccesfullTemplate
import ci.valde.ui.templates.login.LoginTemplate
import ci.valde.ui.templates.login.LogoutTemplate
import io.ktor.application.*
import io.ktor.html.*
import io.ktor.http.content.*
import io.ktor.request.*
import io.ktor.routing.*
import io.ktor.sessions.*
import org.slf4j.LoggerFactory


data class Session(val username: String)

fun Route.app() {

    get(Endpoints.LOGIN.url) {
        call.respondHtmlTemplate(LoginTemplate()) {}
    }

    get(Endpoints.HOME.url) {
        call.respondHtmlTemplate(HomeTemplate()) {}
    }

    get(Endpoints.LOGOUT.url) {
        call.sessions.clear(Constants.COOKIE_NAME.value)
        call.respondHtmlTemplate(LogoutTemplate()) {}
    }

    post(Endpoints.DO_LOGIN.url) {
        val logger = LoggerFactory.getLogger("LoginView")
        val multipart = call.receiveMultipart()

        call.request.headers.forEach { key, values ->
            logger.info("key $key - values $values")
        }

        var username = ""
        var password = ""

        while (true) {
            val part = multipart.readPart() ?: break
            when (part) {
                is PartData.FormItem -> {
                    logger.info("FormItem: ${part.name} = ${part.value}")
                    if (part.name == "username")
                        username = part.value
                    if (part.name == "password")
                        password = part.value
                }
                is PartData.FileItem -> {
                    logger.info("FileItem: ${part.name} -> ${part.originalFileName} of ${part.contentType}")
                }
            }
            part.dispose()
        }
        if (SecurityHandler().isValid(username, password)) {
            call.sessions.set(Constants.COOKIE_NAME.value, Session(username))
            call.respondHtmlTemplate(LoginSuccesfullTemplate()) {
                greeting {
                    +"You are logged in as $username and a cookie has been created"
                }
            }
        } else {
            call.respondHtmlTemplate(LoginTemplate()) {
                greeting {
                    +"Username or password was invalid... Try again."
                }

            }

        }
    }
}