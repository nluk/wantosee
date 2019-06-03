package pl.nluk.wantosee.connectivity

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class CallbackWrapper<T>(val success: ((Response<T>) -> Unit)?, val failure: ((t: Throwable) -> Unit)? = null) : Callback<T> {
    override fun onFailure(call: Call<T>, t: Throwable) {
        failure?.invoke(t)
    }

    override fun onResponse(call: Call<T>, response: Response<T>) {
      success?.invoke(response)
    }
}