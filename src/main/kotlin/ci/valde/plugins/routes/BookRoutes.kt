package ci.valde.plugins.routes

import ci.valde.DataManagerMongoDB
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.locations.*
import io.ktor.response.*
import io.ktor.routing.*

@Location("book/list")
data class BookListLocation(val sortBy: String, val asc: Boolean)

fun Route.books() {
    val dataManager = DataManagerMongoDB()
    authenticate("bookStoreAuth"){
        get<BookListLocation>{
            call.respond(dataManager.sortedBooks(it.sortBy, it.asc))
        }
    }


}