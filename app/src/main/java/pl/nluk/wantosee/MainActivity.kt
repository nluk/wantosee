package pl.nluk.wantosee

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import pl.nluk.wantosee.base.BaseFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onBackPressed() {
        val fragment = getCurrentFragment() as BaseFragment
        if (fragment.useDefaultBackAction()) super.onBackPressed()
        else fragment.onBackPressed()
    }

    fun getCurrentFragment(): Fragment {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        return navHostFragment!!.childFragmentManager.fragments[0]
    }
}
