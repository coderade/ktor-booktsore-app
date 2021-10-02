package ci.valde.plugins

import ci.valde.Constants
import ci.valde.plugins.routes.Session
import io.ktor.auth.*
import io.ktor.sessions.*
import io.ktor.application.*

fun Application.configureSecurity() {

    val users = listOf<String>("shopper1", "shopper2", "shopper3")
    install(Authentication){
        basic(name = "bookStoreAuth") {
            realm = "Book Store"
//            validate { credentials ->
//                if (credentials.name == credentials.password) {
//                    UserIdPrincipal(credentials.name)
//                } else {
//                    null
//                }
//            }

            validate {
                if (users.contains(it.name) && it.password == "password") {
                    UserIdPrincipal(it.name)
                } else {
                    null
                }
            }
        }

        form(name = "myauth2") {
            userParamName = "user"
            passwordParamName = "password"
            challenge {
                /**/
            }
        }
    }
    install(Sessions) {
        cookie<Session>(Constants.COOKIE_NAME.value)
    }

}
