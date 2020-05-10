package com.example.medbutler.classes.view.ListView

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.medbutler.R
import com.example.medbutler.classes.controller.MainController

class ModelListViewCalendar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.image_listview_layout_all)
        var listview = findViewById<ListView>(R.id.listviewAll)
        var list = mutableListOf<ModelOther>()

        list.add(
            ModelOther(
                "Calendar model picture one",
                R.drawable.cal_im
            )
        )
        list.add(
            ModelOther(
                "Calendar model picture two",
                R.drawable.cal2_im
            )
        )
        list.add(
            ModelOther(
                "Calendar model picture three",
                R.drawable.cal3_im
            )
        )
        list.add(
            ModelOther(
                "Calendar model picture four",
                R.drawable.cal4_im
            )
        )
        list.add(
            ModelOther(
                "Calendar model picture five",
                R.drawable.cal5_im
            )
        )

        listview.adapter = CustomAdapterOther(
            this,
            R.layout.listview_layout_calendar,
            list
        )
        listview.setOnItemClickListener { parent: AdapterView<*>, view: View, position: Int, id: Long ->
            if (position == 0) {
                Toast.makeText(this@ModelListViewCalendar, "you selected Calendar model picture one", Toast.LENGTH_SHORT).show()
                MainController.getcurrent().getappearanceInfo().setcalendarImageId("cal_im")
                MainController.getcurrent().getappearanceInfo().setcalendarImageBackgroundId("cal_back")
            }
            if (position == 1) {
                Toast.makeText(this@ModelListViewCalendar, "you selected Calendar model picture two", Toast.LENGTH_SHORT).show()
                MainController.getcurrent().getappearanceInfo().setcalendarImageId("cal2_im")
                MainController.getcurrent().getappearanceInfo().setcalendarImageBackgroundId("cal2_back")
            }
            if (position == 2) {
                Toast.makeText(this@ModelListViewCalendar, "you selected Calendar model picture three", Toast.LENGTH_SHORT).show()
                MainController.getcurrent().getappearanceInfo().setcalendarImageId("cal3_im")
                MainController.getcurrent().getappearanceInfo().setcalendarImageBackgroundId("cal3_back")
            }
            if (position == 3) {
                Toast.makeText(this@ModelListViewCalendar, "you selected Calendar model picture four", Toast.LENGTH_SHORT).show()
                MainController.getcurrent().getappearanceInfo().setcalendarImageId("cal4_im")
                MainController.getcurrent().getappearanceInfo().setcalendarImageBackgroundId("cal4_back")
            }
            if (position == 4) {
                Toast.makeText(this@ModelListViewCalendar, "you selected Calendar model picture five", Toast.LENGTH_SHORT).show()
                MainController.getcurrent().getappearanceInfo().setcalendarImageId("cal5_im")
                MainController.getcurrent().getappearanceInfo().setcalendarImageBackgroundId("cal5_back")
            }
            MainController.saveUserAll()
            finish()
        }
    }
}