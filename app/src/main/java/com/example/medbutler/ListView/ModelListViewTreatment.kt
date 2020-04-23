package com.example.medbutler.ListView

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.medbutler.MainActivity
import com.example.medbutler.R
import com.example.medbutler.classes.controller.MainController

class ModelListViewTreatment : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.image_listview_layout_all)
        var listview = findViewById<ListView>(R.id.listviewAll)
        var list = mutableListOf<ModelTreatment>()

        list.add(
            ModelTreatment(
                "Treatment & Care normal",
                "Treatment animated theme one",
                R.drawable.treat1_1, R.drawable.treat1_2
            )
        )
        list.add(
            ModelTreatment(
                "Treatment & Care open apple",
                "Treatment animated theme two",
                R.drawable.treat2_1, R.drawable.treat2_2
            )
        )
        list.add(
            ModelTreatment(
                "Heart beating monitor classic",
                "Treatment animated theme three",
                R.drawable.treat3_1, R.drawable.treat3_2
            )
        )
        list.add(
            ModelTreatment(
                "Heart beating monitor purple",
                "Treatment animated theme four",
                R.drawable.treat3_3,
                R.drawable.treat3_2
            )
        )

        listview.adapter = CustomListviewAdapter(
            this,
            R.layout.listview_layout_treatment,
            list
        )
        listview.setOnItemClickListener { parent: AdapterView<*>, view: View, position: Int, id: Long ->
            if (position == 0) {
                Toast.makeText(this@ModelListViewTreatment, "you selected Treatment &amp; Care normal", Toast.LENGTH_SHORT).show()
                MainController.getcurrent().getappearanceInfo().settreatmentImageFirstId("treat1_1")
                MainController.getcurrent().getappearanceInfo().settreatmentImageSecondId("treat1_2")
                finish()
            }
            if (position == 1) {
                Toast.makeText(this@ModelListViewTreatment, "you selected Treatment &amp; Care open apple", Toast.LENGTH_SHORT).show()
                MainController.getcurrent().getappearanceInfo().settreatmentImageFirstId("treat2_1")
                MainController.getcurrent().getappearanceInfo().settreatmentImageSecondId("treat2_2")
                finish()
            }
            if (position == 2) {
                Toast.makeText(this@ModelListViewTreatment, "you selected Heart beating monitor classic", Toast.LENGTH_SHORT).show()
                MainController.getcurrent().getappearanceInfo().settreatmentImageFirstId("treat3_1")
                MainController.getcurrent().getappearanceInfo().settreatmentImageSecondId("treat3_2")
                finish()
            }
            if (position == 3) {
                Toast.makeText(this@ModelListViewTreatment, "you selected Heart beating monitor purple", Toast.LENGTH_SHORT).show()
                MainController.getcurrent().getappearanceInfo().settreatmentImageFirstId("treat3_3")
                MainController.getcurrent().getappearanceInfo().settreatmentImageSecondId("treat3_2")
                finish()
            }
        }
    }
}