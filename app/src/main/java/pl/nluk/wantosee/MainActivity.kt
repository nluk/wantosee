package pl.nluk.wantosee

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import pl.nluk.wantosee.retrofit.Rest

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Rest.init()
    }
}
