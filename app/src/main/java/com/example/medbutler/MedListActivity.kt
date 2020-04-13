package com.example.medbutler

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.medbutler.classes.model.Med
import kotlinx.android.synthetic.main.activity_med_list.*
import kotlin.collections.ArrayList

class MedListActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_med_list)
        updateAppearance()
        var med1:Med = Med( "IB600","Ibuprofeno 600mg", 24,7,30, 1,false)
        var med2:Med = Med( "IB400","Ibuprofeno 400mg", 4,14,2, 5,true)
        var med3:Med = Med( "IB400","Ibuprofeno 400mg", 72,0,30, 8,false)
        var med4:Med = Med( "IB600","Ibuprofeno 600mg", 48,90,22, 8,true)
        var med5:Med = Med( "IB800","Ibuprofeno 800mg", 24,30,54, 7,true)
        var med6:Med = Med( "IB999900","Ibuprofeno 99999mg", 24,7,13, 12,false) // PER EL TEST
        val array_exemple = arrayListOf(med1,med2,med3,med4,med5,med6)   //Obenir array Usuari de Controler

        listViewMedList.adapter = CustomAdapter(this, R.layout.simple_list_item_custom, array_exemple)

        listViewMedList.setOnItemClickListener { parent, view, position, id ->
            var listItem:Med = array_exemple.get(position)
            val intentModifMed= Intent(this, ModifMedActivity::class.java)
            startActivity(intentModifMed)
        }
    }

    class CustomAdapter (var mCtx: Context, var resources:Int, var items:List<Med>):
        ArrayAdapter<Med>(mCtx, resources, items) {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
            val view: View = layoutInflater.inflate(resources, null)

            val lay:RelativeLayout = view.findViewById(R.id.relatLayout)
            val titleTextView: TextView = view.findViewById(R.id.textMedAtributes)

            var mItem: Med = items[position]
            titleTextView.text = mItem.toStringAllAtributes()
            return view
        }
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
        val idBack = resources.getIdentifier(MainActivity.background, "drawable", packageName)
        // val drawable = resources.getDrawable(idBack)
        backgroundLay.setBackgroundResource(idBack)

        var toolbar: LinearLayout = findViewById(R.id.my_toolbar)
        var userBut: ImageButton = findViewById(R.id.userButton)
        var calendarButt: ImageButton = findViewById(R.id.calendarButton)
        var settingsBut: ImageButton = findViewById(R.id.settingsButton)
        var medsBut: ImageButton = findViewById(R.id.medsButton)
        var addMedBut: Button = findViewById(R.id.addMedicine)
        var toolbarTreatment: LinearLayout = findViewById(R.id.toolbar_treatment)
        var medListBut: TextView = findViewById(R.id.med_list)
        var dietListBut: TextView = findViewById(R.id.diet_list)
        toolbar.setBackgroundColor(MainActivity.darkerToolbarColor)
        toolbarTreatment.setBackgroundColor(MainActivity.toolbarColor)
        medListBut.setBackgroundColor(MainActivity.toolbarColor)
        medListBut.setTextColor(MainActivity.darkerToolbarColorText)
        dietListBut.setBackgroundColor(MainActivity.brighterToolbarColor)
        dietListBut.setTextColor(MainActivity.darkerToolbarColorText)
        userBut.setBackgroundColor(MainActivity.toolbarColor)
        calendarButt.setBackgroundColor(MainActivity.toolbarColor)
        settingsBut.setBackgroundColor(MainActivity.toolbarColor)
        medsBut.setBackgroundColor(MainActivity.toolbarColor)
        addMedBut.setBackgroundTintList(ColorStateList.valueOf(MainActivity.brighterToolbarColor))
        addMedBut.setTextColor(MainActivity.darkerToolbarColorText)
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
    fun actionAddMed(view: View){
        val intent= Intent(this, AddMedActivity::class.java)
        startActivity(intent)
    }
    fun actiongoHome(){
        val intent= Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
    fun actionDietList(view: View) {
        val intent= Intent(this, DietListNutritionActivity::class.java)
        startActivity(intent)
    }
}
