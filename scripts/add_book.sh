curl -X POST -d '{"id": "100", "title":"The Lord of the Rings", "author": "J. R. R. Tolkien", "price": 200.0}' \
"localhost:8080/books/" -H "Content-Type: application/json"