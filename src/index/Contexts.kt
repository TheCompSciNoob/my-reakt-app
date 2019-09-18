package index

import kotlinx.coroutines.CoroutineScope
import react.RContext
import react.RSetState
import react.createContext
import util.JokeResponse

val MainScopeContext: RContext<CoroutineScope> = createContext()

val ShopContext: RContext<Pair<JokeResponse?, RSetState<JokeResponse?>>> = createContext()