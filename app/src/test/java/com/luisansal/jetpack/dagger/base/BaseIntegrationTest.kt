package com.luisansal.jetpack.dagger.base


import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.luisansal.jetpack.AppTest
import com.luisansal.jetpack.dagger.di.AppComponentTest
import com.luisansal.jetpack.model.database.MyRoomDatabase
import org.junit.After
import org.junit.Before
import org.junit.Ignore
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowLog
import org.robolectric.shadows.ShadowLooper

@Ignore
@RunWith(RobolectricTestRunner::class)
@Config(application = AppTest::class, sdk = [27])
abstract class BaseIntegrationTest : CoroutineIntegrationTest() {

    lateinit var daggerComponent : AppComponentTest

    val mContext = ApplicationProvider.getApplicationContext<Context>()

    @Before
    fun before() {
        //Habilitar los Logs, para propositos de test en producción no es recomendable habilitarlos
        ShadowLog.stream = System.out
        //Creación de instancia para el mContext y el Application
        val app = mContext.applicationContext as AppTest

        //Construcción e Injección de Dagger en el Test
        //Para que de esta manera las anotaciones Inject puedan funcionar
        daggerComponent = (app.mAppComponent as AppComponentTest)
    }

    @After
    fun after() {
        MyRoomDatabase.getDatabase(mContext)?.close()
    }

    fun waitUiThread(delay: Long = 100) {
        Thread.sleep(delay)
        ShadowLooper.runUiThreadTasksIncludingDelayedTasks()
    }

}