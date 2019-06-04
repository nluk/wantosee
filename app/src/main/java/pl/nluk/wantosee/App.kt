package pl.nluk.wantosee

import android.app.Application
import com.raizlabs.android.dbflow.config.FlowConfig
import com.raizlabs.android.dbflow.config.FlowManager
import pl.nluk.wantosee.connectivity.CommonConnectivity
import pl.nluk.wantosee.connectivity.rest.Rest

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        CommonConnectivity.init()
        Rest.init(CommonConnectivity)

        FlowManager.init(FlowConfig.Builder(applicationContext).build())
    }
}