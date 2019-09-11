package root

import about.About
import home.Home
import joke.joke
import navigation.navigation
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.router.dom.browserRouter
import react.router.dom.redirect
import react.router.dom.route
import react.router.dom.switch
import shop.Shop

class Root: RComponent<RProps, RState>() {
    override fun RBuilder.render() {
        browserRouter {
            navigation()
            switch {
                route("/home", Home::class, exact = true)
                route("/about", About::class, exact = true)
                route("/shop", Shop::class, exact = true)
                route<IdProps>("/shop/:id") { joke(it.match.params.id) }
                redirect("/", "/home")
            }
        }
    }
}

interface IdProps: RProps {
    var id: String
}

fun RBuilder.root() = child(Root::class) {}