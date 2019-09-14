package util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelChildren
import react.RComponent
import react.RProps
import react.RState
import kotlin.coroutines.CoroutineContext

abstract class CoroutineComponent<P : RProps, S : RState> : RComponent<P, S>(), CoroutineScope {
    override val coroutineContext: CoroutineContext = Job()

    override fun componentWillUnmount() {
        coroutineContext.cancelChildren()
    }
}