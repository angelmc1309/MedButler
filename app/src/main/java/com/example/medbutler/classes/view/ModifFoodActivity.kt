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
import com.example.medbutler.classes.model.Food
import kotlinx.android.synthetic.main.activity_modif_food_layout.*
import java.io.Serializable
import kotlin.random.Random

class ModifFoodActivity : AppCompatActivity() {

    lateinit var optionMeal: Spinner
    var resultMeal:Int = -1
    lateinit var extraObjectDayId:String
    lateinit var extraObjectFoodName:String
    lateinit var extraObject:Food

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modif_food_layout)
        extraObject = intent.extras!!.get("extra_object_food") as Food
        extraObjectFoodName = extraObject.getname()!!
        extraObjectDayId = extraObject.getfoodDate()
        val back_num = createbackground()
        optionMeal = findViewById(R.id.spinnerMealModif) as Spinner

        val optionsMeal = arrayOf("first meal", "second meal", "third meal", "fourth meal","fifth meal")
        if(back_num == 1 || back_num == 3 || back_num == 5 || back_num == 8){
            titleAddFoodModif.setTextColor(resources.getColor(R.color.white))
            foodNameModif.setTextColor(resources.getColor(R.color.white))
            foodNameModif.setHintTextColor(resources.getColor(R.color.white_hint))
            selectFoodModif.setTextColor(resources.getColor(R.color.white))
            optionMeal.adapter = ArrayAdapter<String>(this,R.layout.simple_list_item_1_white, optionsMeal)
        } else{
            titleAddFoodModif.setTextColor(resources.getColor(R.color.md_black))
            foodNameModif.setTextColor(resources.getColor(R.color.md_black))
            foodNameModif.setHintTextColor(resources.getColor(R.color.black_transp))
            selectFoodModif.setTextColor(resources.getColor(R.color.md_black))
            optionMeal.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, optionsMeal)
        }

        foodNameModif.setText(extraObjectFoodName)
        if (extraObject.getnumber() == 0){
            optionMeal.setSelection(0)
            resultMeal = 0
        } else if (extraObject.getnumber() == 1){
            optionMeal.setSelection(1)
            resultMeal = 1
        } else if (extraObject.getnumber() == 2){
            optionMeal.setSelection(2)
            resultMeal = 2
        } else if (extraObject.getnumber() == 3){
            optionMeal.setSelection(3)
            resultMeal = 3
        } else if (extraObject.getnumber() == 4){
            optionMeal.setSelection(4)
            resultMeal = 4
        }

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
        val backgroundLay: RelativeLayout = findViewById(R.id.backgroundAddFoodModif)
        val context: Context = backgroundLay.getContext()
        val idBack = resources.getIdentifier(MainController.getcurrent().getappearanceInfo().getbackground(), "drawable", packageName)
        // val drawable = resources.getDrawable(idBack)
        backgroundLay.setBackgroundResource(idBack)

        val addBut: TextView = findViewById(R.id.btnAddFoodModif)
        val disBut: TextView = findViewById(R.id.btnDiscardFoodModif)

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

    fun addFoodModif(view: View) {

        if(!foodNameModif.text.toString().isEmpty() && resultMeal != -1){
            MainController.setFood(extraObjectDayId,resultMeal,foodNameModif.text.toString())
            if (!(extraObject.getnumber() == resultMeal)){
                MainController.deleteFood(extraObjectDayId,extraObject.getnumber())
            }
            MainController.saveUserAll()
            val intent= Intent(this, DayActivity::class.java)
            val day: Day = MainController.getcurrent().calendar.find(extraObjectDayId)!!
            intent.putExtra("extra_object_day", day as Serializable)
            startActivity(intent)
        } else if(foodNameModif.text.toString().isEmpty()){
            foodNameModif.error="Meal name empty!!"
            foodNameModif.requestFocus()
        }
    }

    fun discardFoodModif(view: View) {
        val intent= Intent(this, DayActivity::class.java)
        val day: Day = MainController.getcurrent().calendar.find(extraObjectDayId)!!
        intent.putExtra("extra_object_day", day as Serializable)
        startActivity(intent)
    }

    fun createbackground(): Int {
        var num:Int
        var stringName:String
        var numSum:Int
        val context: Context = food_image_backModif.getContext()
        num = Random.nextInt(0,8)
        numSum = num + 1
        stringName = "food_background$numSum"
        val id: Int = context.getResources().getIdentifier(stringName, "drawable", context.getPackageName())
        food_image_backModif.setImageResource(id)
        return numSum
    }
}
