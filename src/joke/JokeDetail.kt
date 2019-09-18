package joke

import index.MainScopeContext
import index.ShopContext
import kotlinx.coroutines.await
import kotlinx.coroutines.launch
import react.*
import react.dom.div
import react.dom.h1
import react.dom.p
import react.router.dom.RouteResultProps
import root.IdProps
import util.DadJokeApi
import util.JokeInfo
import util.coroutineComponent

fun RBuilder.jokeDetail(
        props: RouteResultProps<IdProps>
): ReactElement = child(JOKE_DETAIL, props)

val JOKE_DETAIL: FunctionalComponent<RouteResultProps<IdProps>> = coroutineComponent(
        scopeContext = MainScopeContext
) { props, uiScope ->
    val (response, _) = useContext(ShopContext)

    val (info, setInfo) = useState<JokeInfo?>(null)
    useEffect(emptyList()) {
        response?.results?.first { it.id == props.match.params.id }?.let{setInfo}
                ?: uiScope.launch {
                    println("Update info.")
                    val newInfo = fetchJoke(props.match.params.id)
                    setInfo(newInfo)
                }
    }

    div(classes = "joke-detail") {
        h1 { +(info?.id ?: "") }
        p { +(info?.joke ?: "") }
    }
}

private suspend fun fetchJoke(
        id: String
): JokeInfo {
    val response = DadJokeApi.getJokeById(id).await()
    val json = response.text().await()
    return JSON.parse(json)
}