package com.example.medbutler

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.medbutler.classes.controller.MainController
import com.example.medbutler.classes.model.Disease
import kotlinx.android.synthetic.main.activity_disease_information.*


class DiseaseInformationActivity : AppCompatActivity() {

    lateinit var extraObjectId:String

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_disease_information)
        val extraObject:Disease = intent.extras!!.get("extra_object_disease") as Disease
        extraObjectId = extraObject.id
        updateAppearance()
        val first_string:String = "que_es_$extraObjectId"
        //Canviar estil titol "que es"

        val first_dropdown_string_title:String = "causas"
        val first_dropdown_string:String = "causas_$extraObjectId"

        val second_dropdown_string_title:String = "prevencion"
        val second_dropdown_string:String = "prevencion_$extraObjectId"

        val third_dropdown_string_title:String = "tratamientos"
        val third_dropdown_string:String = "tratamientos_$extraObjectId"


        val first_string_Id = resIdByName(first_string, "string")

        val first_dropdown_title_Id = resIdByName(first_dropdown_string_title, "string")
        val first_dropdown_Id = resIdByName(first_dropdown_string, "string")

        val second_dropdown_title_Id = resIdByName(second_dropdown_string_title, "string")
        val second_dropdown_Id = resIdByName(second_dropdown_string, "string")

        val third_dropdown_title_Id = resIdByName(third_dropdown_string_title, "string")
        val third_dropdown_Id = resIdByName(third_dropdown_string, "string")

        val stringFirst = SpannableString(getString(first_string_Id))
        stringFirst.setSpan(RelativeSizeSpan(1.7f), 0, 6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        stringFirst.setSpan(StyleSpan(Typeface.BOLD), 0, 6, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        text_whatIsIt.setText(stringFirst)

        val first_dropdown_title:String = getString(first_dropdown_title_Id)
        val first_dropdown_text:String = getString(first_dropdown_Id)

        val second_dropdown_title:String = getString(second_dropdown_title_Id)
        val second_dropdown_text:String = getString(second_dropdown_Id)

        val third_dropdown_title:String = getString(third_dropdown_title_Id)
        val third_dropdown_text:String = getString(third_dropdown_Id)

        first_dropdown_text_view.setTitleText("    $first_dropdown_title")
        first_dropdown_text_view.setContentText(first_dropdown_text)

        second_dropdown_text_view.setTitleText("    $second_dropdown_title")
        second_dropdown_text_view.setContentText(second_dropdown_text)

        third_dropdown_text_view.setTitleText("    $third_dropdown_title")
        third_dropdown_text_view.setContentText(third_dropdown_text)

    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onResume() {
        updateAppearance()
        super.onResume()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_gohome_toolbar,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_gohome -> actiongoHome()
        }
        return super.onOptionsItemSelected(item)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun updateAppearance(){
        // background
        val backgroundLay: ConstraintLayout = findViewById(R.id.backgroundLayout)
        val context: Context = backgroundLay.getContext()
        val idBack = resources.getIdentifier(MainController.getcurrent().getappearanceInfo().getbackground(), "drawable", packageName)
        // val drawable = resources.getDrawable(idBack)
        backgroundLay.setBackgroundResource(idBack)

        var toolbar: LinearLayout = findViewById(R.id.my_toolbar)
        var userBut: ImageButton = findViewById(R.id.userButton)
        var calendarButt: ImageButton = findViewById(R.id.calendarButton)
        var settingsBut: ImageButton = findViewById(R.id.settingsButton)
        var medsBut: ImageButton = findViewById(R.id.medsButton)
        var toolbarTreatment: LinearLayout = findViewById(R.id.toolbar_treatment)
        var medListBut: TextView = findViewById(R.id.med_list)
        var dietListBut: TextView = findViewById(R.id.diet_list)
        toolbar.setBackgroundColor(MainController.getcurrent().getappearanceInfo().getdarkerToolbarColor())
        toolbarTreatment.setBackgroundColor(MainController.getcurrent().getappearanceInfo().gettoolbarColor())
        medListBut.setBackgroundColor(MainController.getcurrent().getappearanceInfo().getbrighterToolbarColor())
        medListBut.setTextColor(MainController.getcurrent().getappearanceInfo().getdarkerToolbarColorText())
        dietListBut.setBackgroundColor(MainController.getcurrent().getappearanceInfo().gettoolbarColor())
        dietListBut.setTextColor(MainController.getcurrent().getappearanceInfo().getdarkerToolbarColorText())
        userBut.setBackgroundColor(MainController.getcurrent().getappearanceInfo().gettoolbarColor())
        calendarButt.setBackgroundColor(MainController.getcurrent().getappearanceInfo().gettoolbarColor())
        settingsBut.setBackgroundColor(MainController.getcurrent().getappearanceInfo().gettoolbarColor())
        medsBut.setBackgroundColor(MainController.getcurrent().getappearanceInfo().gettoolbarColor())
    }

    fun Context.resIdByName(resIdName: String?, resType: String): Int {
        resIdName?.let {
            return resources.getIdentifier(it, resType, packageName)
        }
        throw Resources.NotFoundException()
    }

    fun actionCalendar(view: View){
        val intent= Intent(this, Calendar::class.java)
        startActivity(intent)
    }
    fun actionUser(view: View){
        val intent= Intent(this, UserProfile::class.java)
        startActivity(intent)
    }
    fun actionSettings(view: View){
        val intent= Intent(this, SettingsActivity::class.java)
        startActivity(intent)
    }
    fun actiongoHome(){
        val intent= Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
    fun actionMedList(view: View) {
        val intent= Intent(this, MedListActivity::class.java)
        startActivity(intent)
    }
}