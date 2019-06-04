package pl.nluk.wantosee.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

abstract class BaseFragment : Fragment() {

    var TAG = ""

    @LayoutRes
    abstract fun layoutRes() : Int

    open fun onBackPressed() {
        Log.println(Log.WARN, TAG, "$TAG did not override onBackPressed")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutRes(), container, false)
    }

    fun navigate(action : Int){
        findNavController().navigate(action)
    }

    fun navigateWithBundle(action: Int, bundle: Bundle) {
        findNavController().navigate(action, bundle)
    }

    abstract fun useDefaultBackAction(): Boolean


}