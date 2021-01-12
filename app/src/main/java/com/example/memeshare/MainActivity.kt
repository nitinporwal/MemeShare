package com.example.memeshare

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadMeme()
    }

    fun loadMeme() {

        var memeIv = findViewById<ImageView>(R.id.memeIv)

        val queue = Volley.newRequestQueue(this)
        val url = "https://meme-api.herokuapp.com/gimme"

        val JsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
                { response ->
                    var url = response.getString("url")
                    Glide.with(this).load(url).into(memeIv)
                },
                {
                    Log.d("error", it.localizedMessage)
                })

        queue.add(JsonObjectRequest)
    }

    fun shareMeme(view: View) {

    }
    fun nextMeme(view: View) {

    }
}