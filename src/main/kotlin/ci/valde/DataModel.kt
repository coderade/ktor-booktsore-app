package ci.valde

data class Book(var id: String, var title: String, var author: String, var price: Float)
data class ShoppingCart(var id: String, var userId: String, val items: ArrayList<ShoppingItem>)
data class ShoppingItem(var id: String, var quantity: Int)
data class User(var id: String, var name: String, var username: String, var password: String)

