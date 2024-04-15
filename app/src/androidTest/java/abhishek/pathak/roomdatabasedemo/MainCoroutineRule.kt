package abhishek.pathak.roomdatabasedemo

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import kotlin.coroutines.CoroutineContext

@ExperimentalCoroutinesApi
class MainCoroutineRule : TestWatcher(), CoroutineScope {
    @OptIn(DelicateCoroutinesApi::class)
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    override val coroutineContext: CoroutineContext
        get() = mainThreadSurrogate + SupervisorJob()

    override fun starting(description: Description?) {
        super.starting(description)
        Dispatchers.setMain(mainThreadSurrogate)
    }

    override fun finished(description: Description?) {
        super.finished(description)
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }
}