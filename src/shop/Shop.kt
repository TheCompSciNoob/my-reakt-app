package shop

import index.MainScopeContext
import index.ShopContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.await
import kotlinx.coroutines.launch
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
    val mainScope: CoroutineScope = useContext(MainScopeContext)
    val (response, setResponse) = useContext(ShopContext)
    //fetch the shop when this component starts
    useEffect {
        if (response == null) mainScope.launch {
            val shop = fetchShop()
            setResponse(shop)
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

private suspend fun CoroutineScope.fetchShop(): JokeResponse {
    val response = DadJokeApi.searchJokes(
            page = 0,
            limit = 5,
            term = "dog"
    ).await()
    val json = response.text().await()
    return JSON.parse(json)
}

//private inline fun CoroutineScope.fetchShopAsync(
//        crossinline handler: (JokeResponse) -> Unit
//): Job = launch {
//    val response = DadJokeApi.searchJokes(
//            page = 0,
//            limit = 5,
//            term = "dog"
//    ).await()
//    val json = response.text().await()
//    handler(JSON.parse(json))
//}