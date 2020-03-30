package com.example.medbutler.ListView

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.medbutler.MainActivity
import com.example.medbutler.R

class ModelListViewSettings : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.image_listview_layout_all)
        var listview = findViewById<ListView>(R.id.listviewAll)
        var list = mutableListOf<ModelOther>()

        list.add(
            ModelOther(
                "Settings model picture one",
                R.drawable.set1_im
            )
        )
        list.add(
            ModelOther(
                "Settings model picture two",
                R.drawable.set2_im
            )
        )
        list.add(
            ModelOther(
                "Settings model picture three",
                R.drawable.set3_im
            )
        )
        list.add(
            ModelOther(
                "Settings model picture four",
                R.drawable.set4_im
            )
        )
        list.add(
            ModelOther(
                "Settings model picture five",
                R.drawable.set5_im
            )
        )
        list.add(
            ModelOther(
                "Settings model picture six",
                R.drawable.set6_im
            )
        )
        list.add(
            ModelOther(
                "Settings model picture seven",
                R.drawable.set7_im
            )
        )

        listview.adapter = CustomAdapterOther(
            this,
            R.layout.listview_layout_calendar,
            list
        )
        listview.setOnItemClickListener { parent: AdapterView<*>, view: View, position: Int, id: Long ->
            if (position == 0) {
                Toast.makeText(this@ModelListViewSettings, "you selected Settings model picture one", Toast.LENGTH_SHORT).show()
                MainActivity.settingsImageId = "set1_im"
                MainActivity.settingsImageBackgroundId = "set1_gray"
                finish()
            }
            if (position == 1) {
                Toast.makeText(this@ModelListViewSettings, "you selected Settings model picture two", Toast.LENGTH_SHORT).show()
                MainActivity.settingsImageId = "set2_im"
                MainActivity.settingsImageBackgroundId = "set2_gray"
                finish()
            }
            if (position == 2) {
                Toast.makeText(this@ModelListViewSettings, "you selected Settings model picture three", Toast.LENGTH_SHORT).show()
                MainActivity.settingsImageId = "set3_im"
                MainActivity.settingsImageBackgroundId = "set3_gray"
                finish()
            }
            if (position == 3) {
                Toast.makeText(this@ModelListViewSettings, "you selected Settings model picture four", Toast.LENGTH_SHORT).show()
                MainActivity.settingsImageId = "set4_im"
                MainActivity.settingsImageBackgroundId = "set4_gray"
                finish()
            }
            if (position == 4) {
                Toast.makeText(this@ModelListViewSettings, "you selected Settings model picture five", Toast.LENGTH_SHORT).show()
                MainActivity.settingsImageId = "set5_im"
                MainActivity.settingsImageBackgroundId = "set5_gray"
                finish()
            }
            if (position == 5) {
                Toast.makeText(this@ModelListViewSettings, "you selected Settings model picture six", Toast.LENGTH_SHORT).show()
                MainActivity.settingsImageId = "set6_im"
                MainActivity.settingsImageBackgroundId = "set6_gray"
                finish()
            }
            if (position == 6) {
                Toast.makeText(this@ModelListViewSettings, "you selected Settings model picture seven", Toast.LENGTH_SHORT).show()
                MainActivity.settingsImageId = "set7_im"
                MainActivity.settingsImageBackgroundId = "set7_gray"
                finish()
            }
        }
    }
}