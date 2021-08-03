curl -X PUT -d '{"id": "1", "title":"The Lord of the Rings - The Return of the King", "author": "J. R. R. Tolkien", "price": 200.0}' \
"localhost:8080/books/1" -H "Content-Type: application/json"