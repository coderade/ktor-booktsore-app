package ci.valde.plugins

import ci.valde.plugins.routes.auth
import ci.valde.plugins.routes.books
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.features.*
import io.ktor.application.*
import io.ktor.locations.*
import io.ktor.response.*

fun Application.configureRouting() {

    routing {
        // Static feature. Try to access `/static/index.html`
        static("/static") {
            resources("static")
        }
        install(StatusPages) {
            exception<AuthenticationException> {
                call.respond(HttpStatusCode.Unauthorized)
            }
            exception<AuthorizationException> {
                call.respond(HttpStatusCode.Forbidden)
            }

        }

        install(Locations){}
        auth()
        books()

    }
}

class AuthenticationException : RuntimeException()
class AuthorizationException : RuntimeException()
