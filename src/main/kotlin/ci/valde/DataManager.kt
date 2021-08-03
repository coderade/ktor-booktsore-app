package ci.valde

class DataManager {
    var books = ArrayList<Book>()

    fun generateId(): String {
        return books.size.toString()
    }

    fun init() {
        books.add(Book(generateId(), "Harry Potter 1", "J. K. Rowling", 100.0f))
        books.add(Book(generateId(), "Harry Potter 2", "J. K. Rowling", 80.0f))
        books.add(Book(generateId(), "Harry Potter 3", "J. K. Rowling", 90.0f))
        books.add(Book(generateId(), "Harry Potter 4", "J. K. Rowling", 100.0f))
        books.add(Book(generateId(), "Harry Potter 5", "J. K. Rowling", 110.0f))
        books.add(Book(generateId(), "Harry Potter 6", "J. K. Rowling", 50.0f))
        books.add(Book(generateId(), "Harry Potter 7", "J. K. Rowling", 70.0f))
    }


    fun newBook(book: Book){
        books.add(book)
    }

    fun updateBook(bookId: String, book: Book) : Book?{
        val foundBook = books.find{
            it.id == bookId
        }


        foundBook?.title = book.title
        foundBook?.author = book.author
        foundBook?.price = book.price

        return foundBook

    }

    fun deleteBook(bookId: String): Book?{

        val foundBook = books.find{
            it.id == bookId
        }

        books.remove(foundBook)
        return foundBook
    }
}