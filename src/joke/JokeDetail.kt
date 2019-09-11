package joke

import kotlinx.coroutines.*
import org.w3c.fetch.Headers
import org.w3c.fetch.RequestInit
import react.*
import react.dom.div
import react.dom.h1
import react.dom.p
import shop.JokeInfo
import kotlin.browser.window
import kotlin.coroutines.CoroutineContext

class JokeDetail : RComponent<JokeProps, JokeState>(), CoroutineScope {
    override val coroutineContext: CoroutineContext = Job()

    override fun JokeState.init() {
        jokeInfo = JokeInfo("", "")
    }

    override fun RBuilder.render() {
        div(classes = "joke-detail") {
            h1 { +state.jokeInfo.id }
            p { +state.jokeInfo.joke }
        }
    }

    override fun componentDidMount() {
        fetchJoke()
    }

    override fun componentWillUnmount() {
        coroutineContext.cancelChildren()
    }

    private fun fetchJoke() = launch {
        val headers = Headers().apply {
            append("Accept", "application/json")
        }
        val query = "https://icanhazdadjoke.com/j/${props.id}"
        val response = window.fetch(query, RequestInit(headers = headers)).await()
        val json = response.text().await()
        setState { jokeInfo = JSON.parse(json) }
    }
}

interface JokeProps : RProps {
    var id: String
}

interface JokeState : RState {
    var jokeInfo: JokeInfo
}

fun RBuilder.joke(
        id: String
): ReactElement = child(JokeDetail::class) {
    attrs.id = id
}