package com.example.medbutler.ListView

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.medbutler.MainActivity
import com.example.medbutler.R

class ModelListViewProfile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.image_listview_layout_all)
        var listview = findViewById<ListView>(R.id.listviewAll)
        var list = mutableListOf<ModelOther>()

        list.add(
            ModelOther(
                "Man profile model one",
                R.drawable.man_profile_image
            )
        )
        list.add(
            ModelOther(
                "Woman profile model one",
                R.drawable.woman_profile_image
            )
        )
        list.add(
            ModelOther(
                "Man profile model two",
                R.drawable.man_profile_image2
            )
        )
        list.add(
            ModelOther(
                "Woman profile model two",
                R.drawable.woman_profile_image2
            )
        )
        list.add(
            ModelOther(
                "Man profile model three",
                R.drawable.man_profile_image3
            )
        )
        list.add(
            ModelOther(
                "Woman profile model three",
                R.drawable.woman_profile_image3
            )
        )

        listview.adapter = CustomAdapterOther(
            this,
            R.layout.listview_layout_profile,
            list
        )
        listview.setOnItemClickListener { parent: AdapterView<*>, view: View, position: Int, id: Long ->
            if (position == 0) {
                Toast.makeText(this@ModelListViewProfile, "you selected Man profile model one", Toast.LENGTH_SHORT).show()
                MainActivity.profileImagePersonId = "man_profile_image"
                MainActivity.profileImageBackgroundId = "man_profile_background"
                finish()
            }
            if (position == 1) {
                Toast.makeText(this@ModelListViewProfile, "you selected Woman profile model one", Toast.LENGTH_SHORT).show()
                MainActivity.profileImagePersonId = "woman_profile_image"
                MainActivity.profileImageBackgroundId = "woman_profile_background"
                finish()
            }
            if (position == 2) {
                Toast.makeText(this@ModelListViewProfile, "you selected Man profile model two", Toast.LENGTH_SHORT).show()
                MainActivity.profileImagePersonId = "man_profile_image2"
                MainActivity.profileImageBackgroundId = "man_profile_background2"
                finish()
            }
            if (position == 3) {
                Toast.makeText(this@ModelListViewProfile, "you selected Woman profile model two", Toast.LENGTH_SHORT).show()
                MainActivity.profileImagePersonId = "woman_profile_image2"
                MainActivity.profileImageBackgroundId = "woman_profile_background2"
                finish()
            }
            if (position == 4) {
                Toast.makeText(this@ModelListViewProfile, "you selected Man profile model three", Toast.LENGTH_SHORT).show()
                MainActivity.profileImagePersonId = "man_profile_image3"
                MainActivity.profileImageBackgroundId = "man_profile_background3"
                finish()
            }
            if (position == 5) {
                Toast.makeText(this@ModelListViewProfile, "you selected Woman profile model three", Toast.LENGTH_SHORT).show()
                MainActivity.profileImagePersonId = "woman_profile_image3"
                MainActivity.profileImageBackgroundId = "woman_profile_background3"
                finish()
            }
        }
    }
}