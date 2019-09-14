package home

import react.*
import react.dom.div
import react.dom.h1

fun RBuilder.home(): ReactElement = child(HOME)

val HOME: FunctionalComponent<RProps> = functionalComponent {
    div {
        h1 { +"Home Page" }
    }
}