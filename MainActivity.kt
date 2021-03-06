package id.ac.perjalananceritaini.parsingjsonachmadsahril

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import id.ac.perjalananceritaini.parsingjsonachmadsahril.model.DataItem
import id.ac.perjalananceritaini.parsingjsonachmadsahril.model.ResponseUser
import id.ac.perjalananceritaini.parsingjsonachmadsahril.network.ApiConfig
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response

class MainActivity : AppCompatActivity {

    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = UserAdapter(mutableListOf())

        rv_users.setHasFixedSize(true)
        rv_users.layoutManager = LinearLayoutManager(this)
        rv_users.adapter = adapter
        getUser()
    }

    private fun getUser() {
        val client = ApiConfig.getApiService().getlistUsers("1")

        client.enqueue(object : retrofit2.Callback<ResponseUser> {
            override fun onResponse(call: retrofit2.Call<ResponseUser>, response: retrofit2.Response<ResponseUser>){
                if (response.isSuccessful){
                    val dataArray = response.body()?.data as List<DataItem>
                    for (data in dataArray) {
                        adapter.addUser(data)
                    }
                }
            }

            override fun onFailure(call: retrofit2.Call<ResponseUser>, t: Throwable){
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
                t.printStackTrace()
            }
        })
    }

}