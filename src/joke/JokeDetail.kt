package joke

import kotlinx.coroutines.*
import react.*
import react.dom.div
import react.dom.h1
import react.dom.p
import react.router.dom.RouteResultProps
import root.IdProps
import util.DadJokeApi
import util.JokeInfo

fun RBuilder.jokeDetail(
        props: RouteResultProps<IdProps>
): ReactElement = child(JOKE_DETAIL, props)

val JOKE_DETAIL: FunctionalComponent<RouteResultProps<IdProps>> = functionalComponent { props ->
    val (info, setInfo) = useState<JokeInfo?>(null)
    useEffectWithCleanup {
        val job = if (info == null)
            GlobalScope.fetchJokeAsync(
                    id = props.match.params.id,
                    handler = setInfo
            )
        else
            null
        return@useEffectWithCleanup {
            job?.cancel()
        }
    }

    div(classes = "joke-detail") {
        h1 { +(info?.id ?: "") }
        p { +(info?.joke ?: "") }
    }
}

private inline fun CoroutineScope.fetchJokeAsync(
        id: String,
        crossinline handler: (JokeInfo) -> Unit
): Job = launch {
    val response = DadJokeApi.getJokeById(id).await()
    val json = response.text().await()
    handler(JSON.parse(json))
}