package ci.valde.ui

enum class Endpoints(var url: String) {
    LOGIN("/login"),
    LOGOUT("/logout"),
    DO_LOGIN("/do-login"),
    HOME("/home"),
    BOOKS("/books"),
    CART("/cart")
}