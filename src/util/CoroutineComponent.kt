package util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.plus
import react.*

fun <P : RProps> coroutineComponent(
        scopeContext: RContext<CoroutineScope>,
        func: RBuilder.(P, CoroutineScope) -> Unit
): FunctionalComponent<P> = functionalComponent { props ->

    // CoroutineScope lifecycle, cleanup when unmount
    val uiScope: CoroutineScope = useContext(scopeContext) + Job()
    useEffectWithCleanup(emptyList()) { { uiScope.cancel() } }

    // Invoke functional component
    func(props, uiScope)
}