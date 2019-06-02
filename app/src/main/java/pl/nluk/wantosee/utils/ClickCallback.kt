package pl.nluk.wantosee.utils

import android.view.View

typealias OnClickLambda = ()->Unit

class ClickCallback(val onClick: OnClickLambda) : View.OnClickListener{
    override fun onClick(v: View?) {
        onClick()
    }
}