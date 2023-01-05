package com.example.todo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_update_card.*

class UpdateCard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_card)

        val id = intent.getStringExtra("id")
//        Toast.makeText(this, "$pos", Toast.LENGTH_LONG).show()
        if(id != null){
            val title = DataObject.getData(id).title
            val priority = DataObject.getData(id).priority

            create_title.setText(title)
            create_priority.setText(priority)

            delete_button.setOnClickListener {
                val db = DBHelper(this, null)
                DataObject.deleteData(id)
                db.deleteTask(id)
                myIntent()
            }

        update_button.setOnClickListener {
            val db = DBHelper(this, null)
            DataObject.updateData(
                id,
                create_title.text.toString(),
                create_priority.text.toString()
            )
            db.updateTask(
                id,
                create_title.text.toString(),
                create_priority.text.toString()
            )
            myIntent()
        }
        }
    }

    private fun myIntent() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}