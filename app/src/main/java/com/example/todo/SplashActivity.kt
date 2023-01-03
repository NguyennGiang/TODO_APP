package com.example.todo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import java.util.jar.Attributes

class SplashActivity : AppCompatActivity() {
    private lateinit var db: DBHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        db = DBHelper(this, null)
        DataObject.listdata = db.getTasks() as MutableList<CardInfo>

        var s = "hello: "
        for (a in DataObject.listdata){
            s += a.title + " "
        }
        Toast.makeText(this, "$s", Toast.LENGTH_LONG).show()

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }, 2000)
    }
}