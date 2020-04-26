package com.example.medbutler.classes.view.ListView

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.medbutler.R
import com.example.medbutler.classes.controller.MainController

class ModelListViewBackground : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.image_listview_layout_all)
        var listview = findViewById<ListView>(R.id.listviewAll)
        var list = mutableListOf<ModelOther>()

        list.add(
            ModelOther(
                "Default",
                R.drawable.default_background
            )
        )
        list.add(
            ModelOther(
                "White Forms",
                R.drawable.backgroud_white_forms
            )
        )
        list.add(
            ModelOther(
                "Feather Grey Silver",
                R.drawable.background_feather_silver
            )
        )
        list.add(
            ModelOther(
                "Blue Texture",
                R.drawable.backgroud_blue_texture
            )
        )
        list.add(
            ModelOther(
                "Blue Texture Clarity",
                R.drawable.backgroud_blue_texture_clarity
            )
        )
        list.add(
            ModelOther(
                "Blue Geometric",
                R.drawable.backgroud_blue_geometric
            )
        )
        list.add(
            ModelOther(
                "Default Blue",
                R.drawable.default_blue_background
            )
        )
        list.add(
            ModelOther(
                "Default Blue 2",
                R.drawable.default_blue_background2
            )
        )
        list.add(
            ModelOther(
                "Pinck-Blue",
                R.drawable.background_pinck_blue
            )
        )
        list.add(
            ModelOther(
                "Pinck Hexagon",
                R.drawable.background_pinck_hexagon
            )
        )
        list.add(
            ModelOther(
                "Pinck Sky",
                R.drawable.background_pinck_sky
            )
        )
        list.add(
            ModelOther(
                "Pinck Original",
                R.drawable.background_pinck_original
            )
        )

        listview.adapter = CustomAdapterOther(
            this,
            R.layout.listview_layout_background,
            list
        )
        listview.setOnItemClickListener { parent: AdapterView<*>, view: View, position: Int, id: Long ->
            if (position == 0) {
                Toast.makeText(this@ModelListViewBackground, "you selected Default Background", Toast.LENGTH_SHORT).show()
                MainController.getcurrent().getappearanceInfo().setbackground("default_background")
            }
            if (position == 1) {
                Toast.makeText(this@ModelListViewBackground, "you selected White Forms Background", Toast.LENGTH_SHORT).show()
                MainController.getcurrent().getappearanceInfo().setbackground("backgroud_white_forms")
            }
            if (position == 2) {
                Toast.makeText(this@ModelListViewBackground, "you selected Feather Grey Silver Background", Toast.LENGTH_SHORT).show()
                MainController.getcurrent().getappearanceInfo().setbackground("background_feather_silver")
            }
            if (position == 3) {
                Toast.makeText(this@ModelListViewBackground, "you selected Blue Texture Background", Toast.LENGTH_SHORT).show()
                MainController.getcurrent().getappearanceInfo().setbackground("backgroud_blue_texture")
            }
            if (position == 4) {
                Toast.makeText(this@ModelListViewBackground, "you selected Blue Texture Clarity Background", Toast.LENGTH_SHORT).show()
                MainController.getcurrent().getappearanceInfo().setbackground("backgroud_blue_texture_clarity")
            }
            if (position == 5) {
                Toast.makeText(this@ModelListViewBackground, "you selected Blue Geometric Background", Toast.LENGTH_SHORT).show()
                MainController.getcurrent().getappearanceInfo().setbackground("backgroud_blue_geometric")
            }
            if (position == 6) {
                Toast.makeText(this@ModelListViewBackground, "you selected Default Blue Background", Toast.LENGTH_SHORT).show()
                MainController.getcurrent().getappearanceInfo().setbackground("default_blue_background")
            }
            if (position == 7) {
                Toast.makeText(this@ModelListViewBackground, "you selected Default Blue 2 Background", Toast.LENGTH_SHORT).show()
                MainController.getcurrent().getappearanceInfo().setbackground("default_blue_background2")
            }
            if (position == 8) {
                Toast.makeText(this@ModelListViewBackground, "you selected Pinck-Blue Background", Toast.LENGTH_SHORT).show()
                MainController.getcurrent().getappearanceInfo().setbackground("background_pinck_blue")
            }
            if (position == 9) {
                Toast.makeText(this@ModelListViewBackground, "you selected Pinck Hexagon Background", Toast.LENGTH_SHORT).show()
                MainController.getcurrent().getappearanceInfo().setbackground("background_pinck_hexagon")
            }
            if (position == 10) {
                Toast.makeText(this@ModelListViewBackground, "you selected Pinck Sky Background", Toast.LENGTH_SHORT).show()
                MainController.getcurrent().getappearanceInfo().setbackground("background_pinck_sky")
            }
            if (position == 11) {
                Toast.makeText(this@ModelListViewBackground, "you selected Pinck Original Background", Toast.LENGTH_SHORT).show()
                MainController.getcurrent().getappearanceInfo().setbackground("background_pinck_original")
            }
            MainController.saveUserAll()
            finish()
        }
    }
}