package shop

import kotlinx.coroutines.*
import kotlinx.html.js.onClickFunction
import react.*
import react.dom.button
import react.dom.div
import react.dom.h1
import react.dom.ul
import react.router.dom.navLink
import util.DadJokeApi
import util.JokeInfo
import util.JokeResponse

fun RBuilder.shop(): ReactElement = child(SHOP)

val SHOP: FunctionalComponent<RProps> = functionalComponent {
    val (response, setResponse) = useState<JokeResponse?>(null)
    useEffectWithCleanup {
        //fetch shop coroutine
        val job = if (response == null)
            GlobalScope.fetchShopAsync(setResponse)
        else
            null
        //clean up
        return@useEffectWithCleanup {
            job?.cancel()
        }
    }

    div {
        h1 { +"Shop Page" }
        button {
            +"Refresh"
            attrs.onClickFunction = { setResponse(null) }
        }
        response?.results?.let(::shopInventory)
    }
}

private fun RBuilder.shopInventory(inventory: Array<JokeInfo>) {
    ul(classes = "shop-item") {
        inventory.forEach {
            navLink(to = "/Shop/${it.id}") {
                +it.id
            }
        }
    }
}

private inline fun CoroutineScope.fetchShopAsync(
        crossinline handler: (JokeResponse) -> Unit
): Job = launch {
    val response = DadJokeApi.searchJokes(
            page = 0,
            limit = 5,
            term = "dog"
    ).await()
    val json = response.text().await()
    handler(JSON.parse(json))
}