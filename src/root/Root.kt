package root

import about.about
import home.home
import joke.jokeDetail
import navigation.navigation
import react.*
import react.router.dom.*
import shop.shop

fun RBuilder.root(): ReactElement = child(ROOT)

interface IdProps : RProps {
    var id: String
}

val ROOT: FunctionalComponent<RProps> = functionalComponent {
    browserRouter {
        navigation()
        switch {
            route("/home", exact = true, render = ::home)
            route("/about", exact = true, render = ::about)
            route("/shop", exact = true, render = ::shop)
            route("/shop/:id", exact = true, render = ::jokeDetail)
            redirect("/", "/home")
        }
    }
}