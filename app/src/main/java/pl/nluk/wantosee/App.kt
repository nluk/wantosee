package pl.nluk.wantosee

import android.app.Application
import com.raizlabs.android.dbflow.config.FlowConfig
import com.raizlabs.android.dbflow.config.FlowManager
import pl.nluk.wantosee.retrofit.Rest

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Rest.init()
        FlowManager.init(FlowConfig.Builder(applicationContext).build())
    }
}