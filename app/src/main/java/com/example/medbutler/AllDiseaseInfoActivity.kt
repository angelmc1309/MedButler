package com.example.medbutler

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.medbutler.classes.controller.MainController
import com.example.medbutler.classes.model.AllDiseases
import com.example.medbutler.classes.model.Disease
import kotlinx.android.synthetic.main.activity_all_disease_information.*
import java.io.Serializable


class AllDiseaseInfoActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_disease_information)
        updateAppearance()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onResume() {
        updateAppearance()
        super.onResume()
    }

    class CustomAdapter2 (var mCtx: Context, var resources:Int, var items:List<Disease>):
        ArrayAdapter<Disease>(mCtx, resources, items) {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
            val view: View = layoutInflater.inflate(resources, null)

            val lay: RelativeLayout = view.findViewById(R.id.relatLayoutListDisease)
            val titleTextView: TextView = view.findViewById(R.id.textListDisease)

            var mItem: Disease = items[position]
            titleTextView.text = mItem.toString()
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

        val array_exemple = MainController.getcurrent().listOfDisease

        listViewUserDiseases.adapter = CustomAdapter2(this, R.layout.simple_list_item_custom2, array_exemple)

        listViewUserDiseases.setOnItemClickListener { parent, view, position, id ->
            var listItemId:Disease = array_exemple.get(position)
            val intentDiseaseInfo= Intent(this, DiseaseInformationActivity::class.java)
            intent.putExtra("extra_object_disease", listItemId as Serializable);
            startActivity(intentDiseaseInfo)
        }

        val array_exemple2 = AllDiseases.list

        listViewAllDiseases.adapter = CustomAdapter2(this, R.layout.simple_list_item_custom2, array_exemple2)

        listViewAllDiseases.setOnItemClickListener { parent, view, position, id ->
            var listItemId:Disease = array_exemple2.get(position)
            val intentDiseaseInfo= Intent(this, DiseaseInformationActivity::class.java)
            intentDiseaseInfo.putExtra("extra_object_disease", listItemId as Serializable);
            startActivity(intentDiseaseInfo)
        }

        justifyListViewHeightBasedOnChildren(listViewUserDiseases);
        justifyListViewHeightBasedOnChildren(listViewAllDiseases);
    }

    fun justifyListViewHeightBasedOnChildren(listView: ListView) {
        val adapter = listView.adapter ?: return
        val vg: ViewGroup = listView
        var totalHeight = 0
        for (i in 0 until adapter.count) {
            val listItem = adapter.getView(i, null, vg)
            listItem.measure(0, 0)
            totalHeight += listItem.measuredHeight
        }
        val par = listView.layoutParams
        par.height = totalHeight + listView.dividerHeight * (adapter.count - 1)
        listView.layoutParams = par
        listView.requestLayout()
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