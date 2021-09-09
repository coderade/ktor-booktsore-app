package ci.valde

import org.slf4j.LoggerFactory
import kotlin.reflect.full.declaredMemberProperties


class DataManager {

    private val logger = LoggerFactory.getLogger(DataManager::class.java)
    var books = ArrayList<Book>()

    init {
        books.add(Book(generateId(), "Harry Potter and the Philosopher's Stone", "J. K. Rowling", 100.0f))
        books.add(Book(generateId(), "Harry Potter and the Chamber of Secrets", "J. K. Rowling", 80.0f))
        books.add(Book(generateId(), "Harry Potter and the Prisoner of Azkaban", "J. K. Rowling", 90.0f))
        books.add(Book(generateId(), "Harry Potter and the Goblet of Fire", "J. K. Rowling", 100.0f))
        books.add(Book(generateId(), "Harry Potter and the Order of the Phoenix", "J. K. Rowling", 110.0f))
        books.add(Book(generateId(), "Harry Potter and the Half-Blood Prince ", "J. K. Rowling", 50.0f))
        books.add(Book(generateId(), "Harry Potter and the Deathly Hallows", "J. K. Rowling", 70.0f))
        books.add(Book(generateId(), "The Lord of the Rings - The Return of the King", "J. R. R. Tolkien", 140.0f))
    }

    fun newBook(newBook: Book) : Book{
        books.add(newBook)
        return newBook
    }

    fun updateBook(book: Book) : Book?{
        val foundBook = books.find{
            it.id == book.id
        }

        foundBook?.title = book.title
        foundBook?.author = book.author
        foundBook?.price = book.price

        return foundBook

    }

    fun deleteBook(bookId: String): Book? {

        val foundBook = books.find{
            it.id == bookId
        }

        books.remove(foundBook)
        return foundBook
    }

    fun getAllBooks(): List<Book>{
        return books
    }

    fun sortedBooks(sortBy: String, asc: Boolean): List<Book> {
        val member = Book::class.declaredMemberProperties.find { it.name == sortBy }
        if (member == null) {
            logger.info("The field to sort does not exist")
            return getAllBooks()
        }

        return if (asc)
            getAllBooks().sortedBy { member.get(it).toString() }
        else
            getAllBooks().sortedByDescending { member.get(it).toString() }
    }

    private fun generateId(): String {
        return books.size.toString()
    }

}