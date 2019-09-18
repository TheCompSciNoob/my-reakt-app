package index

import kotlinx.coroutines.CoroutineScope
import react.RContext
import react.RSetState
import react.createContext
import util.JokeResponse

// CoroutineScope for the entire application
val MainScopeContext: RContext<CoroutineScope> = createContext()

// Allow access to the shop
val ShopContext: RContext<Pair<JokeResponse?, RSetState<JokeResponse?>>> = createContext()