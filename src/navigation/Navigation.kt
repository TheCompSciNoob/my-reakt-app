package navigation

import react.*
import react.dom.div
import react.dom.h3
import react.dom.ul
import react.router.dom.navLink

fun RBuilder.navigation() = child(NAVIGATION)

val NAVIGATION: FunctionalComponent<RProps> = functionalComponent {
    div(classes = "nav") {
        h3 { +"Logo" }
        ul(classes = "nav-links") {
            navLink("/about", className = "nav-style") {
                +"About"
            }
            navLink("/shop", className = "nav-style") {
                +"Shop"
            }
        }
    }
}

