package about

import react.*
import react.dom.div
import react.dom.h1

fun RBuilder.about(): ReactElement = child(ABOUT)

val ABOUT: FunctionalComponent<RProps> = functionalComponent {
    div {
        h1 { +"About Page" }
    }
}