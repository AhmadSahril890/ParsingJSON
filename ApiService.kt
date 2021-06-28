package id.ac.perjalananceritaini.parsingjsonachmadsahril.network

import android.telecom.Call
import id.ac.perjalananceritaini.parsingjsonachmadsahril.model.ResponseUser

interface ApiService {
    //Menampilkan user dengan query
    @GET("api/users")
    fun getlistUsers(@Query("page")page: String): retrofit2.Call<ResponseUser>
}