package pl.nluk.wantosee.connectivity

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient

object Common {
    lateinit var okHttpClient: OkHttpClient
    lateinit var gson : Gson
    fun init(){
        GsonBuilder().create();
    }
}