package util

import org.w3c.fetch.Headers
import org.w3c.fetch.RequestInit
import org.w3c.fetch.Response
import kotlin.browser.window
import kotlin.js.Promise

object DadJokeApi {

    val BASE_URL = "https://icanhazdadjoke.com/"

    val HEADERS = Headers().apply {
        append("Accept", "application/json")
    }

    fun searchJokes(
            page: Int,
            limit: Int,
            term: String
    ): Promise<Response> = window.fetch(
            input = "$BASE_URL/search?page=$page&limit=$limit&term=$term",
            init = RequestInit(headers = HEADERS)
    )

    fun getJokeById(
            id: String
    ): Promise<Response> = window.fetch(
            input = "$BASE_URL/j/$id",
            init = RequestInit(headers = HEADERS)
    )
}