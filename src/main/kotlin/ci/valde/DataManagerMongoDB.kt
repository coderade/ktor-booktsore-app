package ci.valde

import com.mongodb.MongoClientSettings
import com.mongodb.client.MongoClients
import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import com.mongodb.client.model.Filters.eq
import org.bson.Document
import org.bson.codecs.configuration.CodecRegistries.fromProviders
import org.bson.codecs.configuration.CodecRegistries.fromRegistries
import org.bson.codecs.configuration.CodecRegistry
import org.bson.codecs.pojo.PojoCodecProvider
import org.bson.types.ObjectId

class DataManagerMongoDB {
    private val database: MongoDatabase
    private val bookCollection: MongoCollection<Book>

    init {
        val pojoCodecRegistry: CodecRegistry = fromProviders(
            PojoCodecProvider.builder()
                .automatic(true)
                .build()
        )

        val codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry)
        val settings: MongoClientSettings = MongoClientSettings.builder()
            //            .applyConnectionString("localhost") => add the connection string if not using localhost
            .codecRegistry(codecRegistry)
            .build()

        val mongoClient = MongoClients.create(settings)
        database = mongoClient.getDatabase("dev")
        bookCollection = database.getCollection(Book::class.java.name, Book::class.java)

        initBooks()
    }

    fun newBook(book: Book): Book {
        bookCollection.insertOne(book)
        return book
    }

    fun fuelUpBook(hexId: String) {
        bookCollection.updateOne(eq("_id", ObjectId(hexId)), Document("\$set", Document("fuel", 99.9f)))
    }


    fun updateBook(book: Book): Book {
        val bookFound = bookCollection.find(Document("_id", book.id)).first()
        bookFound?.title = book.title
        bookFound?.author = book.author
        bookFound?.price = book.price
        return bookFound!!
    }

    fun deleteAllBooks() {
        bookCollection.deleteMany(Document())
    }

    fun deleteBook(bookId: String): Book {
        val bookFound = bookCollection.find(Document("_id", bookId)).first()
        bookCollection.deleteOne(eq("_id", ObjectId(bookId)))
        return bookFound!!
    }


    fun allBooks(): List<Book> {
        val mongoResult = bookCollection.find()
        mongoResult.forEach {
            println("book: $it")
        }
        return mongoResult.toList()
    }

    fun sortedBooks(sortBy: String, asc: Boolean): List<Book> {
        val pageNumber = 1
        val pageSize = 1000
        val ascInt: Int = if (asc) 1 else -1
        return bookCollection
            .find()
            .sort(Document(mapOf(Pair(sortBy, ascInt), Pair("_id", -1))))
            .skip(pageNumber - 1)
            .limit(pageSize)
            .toList()
    }


    private fun initBooks() {
        deleteAllBooks()
        bookCollection.insertOne(Book(null, "Harry Potter and the Philosopher's Stone", "J. K. Rowling", 100.0f))
        bookCollection.insertOne(Book(null, "Harry Potter and the Chamber of Secrets", "J. K. Rowling", 80.0f))
        bookCollection.insertOne(Book(null, "Harry Potter and the Prisoner of Azkaban", "J. K. Rowling", 90.0f))
        bookCollection.insertOne(Book(null, "Harry Potter and the Goblet of Fire", "J. K. Rowling", 100.0f))
        bookCollection.insertOne(Book(null, "Harry Potter and the Order of the Phoenix", "J. K. Rowling", 110.0f))
        bookCollection.insertOne(Book(null, "Harry Potter and the Half-Blood Prince ", "J. K. Rowling", 50.0f))
        bookCollection.insertOne(Book(null, "Harry Potter and the Deathly Hallows", "J. K. Rowling", 70.0f))
        bookCollection.insertOne(
            Book(
                null,
                "The Lord of the Rings - The Return of the King",
                "J. R. R. Tolkien",
                140.0f
            )
        )
    }
}