package com.example.imagebutton

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.net.URL

class MainActivity : AppCompatActivity() {

    private lateinit var btnDownloadImage1: Button
    private lateinit var btnDownloadImage2: Button
    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDownloadImage1 = findViewById(R.id.btn_download_image_1)
        btnDownloadImage2 = findViewById(R.id.btn_download_image_2)
        imageView = findViewById(R.id.image_view)

        btnDownloadImage1.setOnClickListener {
            downloadImage("https://www.soorban.com/images/news/2022/03/1648435357_B0oM0.jpg")
        }

        btnDownloadImage2.setOnClickListener {
            downloadImage("https://www.soorban.com/images/news/2022/03/1648435355_R5mB3.jpg")
        }
    }

    private fun downloadImage(imageUrl: String) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val bitmap = BitmapFactory.decodeStream(URL(imageUrl).openStream())

                launch(Dispatchers.Main) {
                    imageView.setImageBitmap(bitmap)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
