package about

import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.div
import react.dom.h1

class About: RComponent<RProps, RState>() {
    override fun RBuilder.render() {
        div {
            h1 { +"About Page" }
        }
    }
}