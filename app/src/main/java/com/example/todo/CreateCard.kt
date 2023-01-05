package com.example.todo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_create_card.*

class CreateCard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_card)

        save_button.setOnClickListener {
            if (create_title.text.toString().trim { it <= ' ' }.isNotEmpty()
                && create_priority.text.toString().trim { it <= ' ' }.isNotEmpty()
            ) {
                var title = create_title.text.toString()
                var priority = create_priority.text.toString()
                val task = CardInfo(title, priority)
                DataObject.setData(task)

                val db = DBHelper(this, null)
                val a = db.insertTask(task)

//                DataObject.listdata = db.getTasks() as MutableList<CardInfo>
//                var s = "hello: "
//                for (a in DataObject.listdata){
//                    s += a.title
//                }
//                Toast.makeText(this, "$s", Toast.LENGTH_LONG).show()
//                Toast.makeText(this, "$a", Toast.LENGTH_LONG).show()

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}