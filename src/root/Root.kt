package root

import about.about
import home.home
import index.MainScopeContext
import index.ShopContext
import joke.jokeDetail
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import navigation.navigation
import react.*
import react.router.dom.browserRouter
import react.router.dom.redirect
import react.router.dom.route
import react.router.dom.switch
import shop.shop
import util.JokeResponse


//builder functions
fun RBuilder.root(): ReactElement = child(ROOT)

interface IdProps : RProps {
    var id: String
}

val ROOT: FunctionalComponent<RProps> = functionalComponent {
    // CoroutineScope context from index
    val mainScope: CoroutineScope = useContext(MainScopeContext)
    useEffectWithCleanup(emptyList()) {
        println("Main scope started.")
        return@useEffectWithCleanup {
            println("Main Scope cancelled.")
            mainScope.cancel()
        }
    }

    browserRouter {
        navigation()
        switch {
            route("/home", exact = true, render = ::home)
            route("/about", exact = true, render = ::about)
            ShopContext.Provider(
                    value = useState<JokeResponse?>(null)
            ) {
                route("/shop", exact = true, render = ::shop)
                route("/shop/:id", exact = true, render = ::jokeDetail)
            }
            redirect("/", "/home")
        }
    }
}