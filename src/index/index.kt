package index

import kotlinext.js.require
import kotlinext.js.requireAll
import kotlinx.coroutines.MainScope
import react.dom.render
import root.root
import kotlin.browser.document

fun main() {
    requireAll(require.context("src", true, js("/\\.css$/")))

    //test .env for future api keys
    println(process.env.REACT_APP_API_KEY_TEST)

    render(document.getElementById("root")) {
        MainScopeContext.Provider(
                value = MainScope()
        ) {
            root()
        }
    }
}

external object process {
    object env {
        val REACT_APP_API_KEY_TEST: String
    }
}