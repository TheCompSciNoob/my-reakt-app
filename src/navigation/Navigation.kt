package navigation

import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.div
import react.dom.h3
import react.dom.ul
import react.router.dom.navLink

class Navigation : RComponent<RProps, RState>() {
    override fun RBuilder.render() {
        div(classes = "nav") {
            h3 { +"Logo" }
            ul(classes = "nav-links") {
                navLink("/about", className = "nav-style") {
                    +"About"
                }
                navLink("/shop", className = "nav-style") {
                    +"shop"
                }
            }
        }
    }
}

fun RBuilder.navigation() = child(Navigation::class) { }