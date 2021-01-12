package com.example.memeshare

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadMeme()
    }

    fun loadMeme() {
        var progress = findViewById<ProgressBar>(R.id.progressBar)
        progress.visibility = View.VISIBLE
        var memeIv = findViewById<ImageView>(R.id.memeIv)

        val queue = Volley.newRequestQueue(this)
        val url = "https://meme-api.herokuapp.com/gimme"

        val JsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
                { response ->
                    var url = response.getString("url")
                    Glide.with(this).load(url).listener(object: RequestListener<Drawable> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            progress.visibility = View.GONE
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            progress.visibility = View.GONE
                            return false
                        }
                    }).into(memeIv)

                },
                {
                    Log.d("error", it.localizedMessage)
                })

        queue.add(JsonObjectRequest)
    }

    fun shareMeme(view: View) {

    }
    fun nextMeme(view: View) {
        loadMeme()
    }
}