package shop

data class ShopResponse(
        val current_page: Int,
        val limit: Int,
        val next_page: Int,
        val previous_page: Int,
        val results: Array<JokeInfo>,
        val search_term: String,
        val status: Int,
        val total_jokes: Int,
        val total_pages: Int
)

data class JokeInfo(
        val id: String,
        val joke: String
)