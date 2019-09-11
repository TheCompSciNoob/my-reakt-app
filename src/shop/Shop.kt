package shop

import kotlinx.coroutines.*
import org.w3c.fetch.Headers
import org.w3c.fetch.RequestInit
import react.*
import react.dom.div
import react.dom.h1
import react.dom.ul
import react.router.dom.navLink
import kotlin.browser.window
import kotlin.coroutines.CoroutineContext

class Shop : RComponent<RProps, ShopState>(), CoroutineScope {
    override val coroutineContext: CoroutineContext = Job()

    override fun RBuilder.render() {
        div {
            h1 { +"Shop Page" }
            shopInventory()
        }
    }

    override fun componentDidMount() {
        fetchShopAsync()
    }

    override fun componentWillUnmount() {
        coroutineContext.cancelChildren()
    }

    private fun fetchShopAsync(): Job = launch {
        val headers = Headers().apply {
            append("Accept", "application/json")
        }
        val response = window.fetch(
                input = "https://icanhazdadjoke.com/search?page=0&limit=5&term=dog",
                init = RequestInit(headers = headers)
        ).await()
        val json = response.text().await()
        setState { shopResponse = JSON.parse(json) }
    }

    private fun RBuilder.shopInventory() {
        ul(classes = "shop-item") {
            state.shopResponse?.results?.forEach {
                navLink(to = "/shop/${it.id}") {
                    +it.id
                }
            }
        }
    }
}

interface ShopState : RState {
    var shopResponse: ShopResponse?
}