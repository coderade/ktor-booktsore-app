package ci.valde.plugins.routes

import ci.valde.Book
import ci.valde.DataManager
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.books() {
    val dataManager = DataManager()
    route("/books") {
        get("/") {
            val result = dataManager.getAllBooks()
            call.respond(result)
        }

        post("/") {
            val book = call.receive(Book::class)
            val newBook = dataManager.newBook(book)
            call.respond(newBook)
        }

        put("/{id}") {
            val book = call.receive(Book::class)
            val result = dataManager.updateBook(book)
            call.respond(result!!)
        }


        delete("/{id}") {
            val id = call.parameters["id"]!!
            val result = dataManager.deleteBook(id)
            call.respond(result!!)
        }
    }
}