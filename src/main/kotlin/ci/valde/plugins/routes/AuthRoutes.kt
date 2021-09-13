package ci.valde.plugins.routes

import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.sessions.*

fun Route.auth() {
    authenticate("bookStoreAuth") {
        get("/api/auth") {
            val principal = call.principal<UserIdPrincipal>()!!
            call.respondText("Hello ${principal.name}")
        }
    }
    authenticate("bookStoreAuth") {
        get("/protected/route/form") {
            val principal = call.principal<UserIdPrincipal>()!!
            call.respondText("Hello ${principal.name}")
        }
    }

//    get("/session/increment") {
//        val session = call.sessions.get<MySession>() ?: MySession()
//        call.sessions.set(session.copy(count = session.count + 1))
//        call.respondText("Counter is ${session.count}. Refresh to increment.")
//    }
}