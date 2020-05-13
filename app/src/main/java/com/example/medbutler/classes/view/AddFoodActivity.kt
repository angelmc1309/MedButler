package com.example.medbutler.classes.view

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import com.example.medbutler.R
import com.example.medbutler.classes.controller.*
import com.example.medbutler.classes.model.Day
import kotlinx.android.synthetic.main.activity_add_food_layout.*
import java.io.Serializable
import kotlin.random.Random

class AddFoodActivity : AppCompatActivity() {

    lateinit var optionMeal: Spinner
    var resultMeal:Int = -1
    lateinit var extraObjectDayId:String

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_food_layout)
        extraObjectDayId = intent.getStringExtra("extra_object_dayID")!!
        val back_num = createbackground()
        optionMeal = findViewById(R.id.spinnerMeal) as Spinner

        /*
        Importance -->  0 = indiferente/indifferent
                        1 = opcional/optional
                        2 = importante/important
                        3 = Muy importante/Very important
        */

        val optionsMeal = arrayOf("first meal", "second meal", "third meal", "fourth meal","fifth meal")
        if(back_num == 1 || back_num == 3 || back_num == 5 || back_num == 8){
            titleAddFood.setTextColor(resources.getColor(R.color.white))
            foodName.setTextColor(resources.getColor(R.color.white))
            foodName.setHintTextColor(resources.getColor(R.color.white_hint))
            selectFood.setTextColor(resources.getColor(R.color.white))
            optionMeal.adapter = ArrayAdapter<String>(this,R.layout.simple_list_item_1_white, optionsMeal)
        } else{
            titleAddFood.setTextColor(resources.getColor(R.color.md_black))
            foodName.setTextColor(resources.getColor(R.color.md_black))
            foodName.setHintTextColor(resources.getColor(R.color.black_transp))
            selectFood.setTextColor(resources.getColor(R.color.md_black))
            optionMeal.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, optionsMeal)
        }

        optionMeal.setSelection(0)

        optionMeal.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                resultMeal = -1 // Nothing Selected
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                resultMeal = position
            }
        }
        updateAppearance()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onResume() {
        updateAppearance()
        super.onResume()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun updateAppearance(){

        // background
        val backgroundLay: RelativeLayout = findViewById(R.id.backgroundAddFood)
        val context: Context = backgroundLay.getContext()
        val idBack = resources.getIdentifier(MainController.getcurrent().getappearanceInfo().getbackground(), "drawable", packageName)
        // val drawable = resources.getDrawable(idBack)
        backgroundLay.setBackgroundResource(idBack)

        val addBut: TextView = findViewById(R.id.btnAddFood)
        val disBut: TextView = findViewById(R.id.btnDiscardFood)

        addBut.setBackgroundTintList(ColorStateList.valueOf(MainController.getcurrent().getappearanceInfo().getbrighterToolbarColor()))
        addBut.setBackgroundResource(R.drawable.rounded_button)
        addBut.setTextColor(MainController.getcurrent().getappearanceInfo().getdarkerToolbarColorText())
        disBut.setBackgroundTintList(ColorStateList.valueOf(MainController.getcurrent().getappearanceInfo().getbrighterToolbarColor()))
        disBut.setBackgroundResource(R.drawable.rounded_button)
        disBut.setTextColor(MainController.getcurrent().getappearanceInfo().getdarkerToolbarColorText())
        /*
        addBut.setBackgroundColor(MainActivity.brighterToolbarColor)
        addBut.setTextColor(MainActivity.darkerToolbarColorText)
        disBut.setBackgroundColor(MainActivity.brighterToolbarColor)
        disBut.setTextColor(MainActivity.darkerToolbarColorText)
         */
    }

    fun addFood(view: View) {

        if(!foodName.text.toString().isEmpty() && resultMeal != -1){
            MainController.setFood(extraObjectDayId,resultMeal,foodName.text.toString())
            MainController.saveUserAll()
            val intent= Intent(this, DayActivity::class.java)
            val day: Day = MainController.getcurrent().calendar.find(extraObjectDayId)!!
            intent.putExtra("extra_object_day", day as Serializable)
            startActivity(intent)
        } else if(foodName.text.toString().isEmpty()){
            foodName.error="Reminder's name empty!!"
            foodName.requestFocus()
        }
    }

    fun discardFood(view: View) {
        val intent= Intent(this, DayActivity::class.java)
        val day: Day = MainController.getcurrent().calendar.find(extraObjectDayId)!!
        intent.putExtra("extra_object_day", day as Serializable)
        startActivity(intent)
    }

    fun createbackground(): Int {
        var num:Int
        var stringName:String
        var numSum:Int
        val context: Context = food_image_back.getContext()
        num = Random.nextInt(0,8)
        numSum = num + 1
        stringName = "food_background$numSum"
        val id: Int = context.getResources().getIdentifier(stringName, "drawable", context.getPackageName())
        food_image_back.setImageResource(id)
        return numSum
    }
}
