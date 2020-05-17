package com.example.medbutler.classes.view

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import android.widget.AdapterView.OnItemLongClickListener
import android.widget.ListView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.medbutler.R
import com.example.medbutler.classes.controller.MainController
import com.example.medbutler.classes.model.Day
import com.example.medbutler.classes.model.Food
import com.example.medbutler.classes.model.Reminder
import com.example.medbutler.classes.model.Task
import kotlinx.android.synthetic.main.day_layout.*
import java.io.Serializable

class DayActivity : AppCompatActivity() {

    lateinit var extraObjectId:String

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.day_layout)
        val extraObject:Day = intent.extras!!.get("extra_object_day") as Day
        extraObjectId = extraObject.getDate()
        updateAppearance()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onResume() {
        updateAppearance()
        super.onResume()
    }

    class CustomAdapter2 (var mCtx: Context, var resources:Int, var items:List<Task>):
        ArrayAdapter<Task>(mCtx, resources, items) {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
            val view: View = layoutInflater.inflate(resources, null)

            val lay: RelativeLayout = view.findViewById(R.id.relatLayoutListDay)
            val titleTextView: TextView = view.findViewById(R.id.textNameinDay)
            val firstTextView: TextView = view.findViewById(R.id.textinDayFirst)
            val secondTextView: TextView = view.findViewById(R.id.textinDaySecond)

            var mItem: Task = items[position]
            titleTextView.text = mItem.gettaskName()

            if (mItem.getallowNotification()!!){
                firstTextView.text = context.getString(R.string.notification_on)
            }else{
                firstTextView.text = context.getString(R.string.notification_off)
            }

            val formattedHour = String.format("%02d", mItem.gettaskStartTimeHour())
            val formattedMinute = String.format("%02d", mItem.gettaskStartTimeMinute())
            secondTextView.text = context.getString(R.string.time_select,formattedHour,formattedMinute)
            return view
        }
    }

    class CustomAdapter3 (var mCtx: Context, var resources:Int, var items:List<Reminder>):
        ArrayAdapter<Reminder>(mCtx, resources, items) {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
            val view: View = layoutInflater.inflate(resources, null)

            val lay: RelativeLayout = view.findViewById(R.id.relatLayoutListDay)
            val titleTextView: TextView = view.findViewById(R.id.textNameinDay)
            val firstTextView: TextView = view.findViewById(R.id.textinDayFirst)
            val secondTextView: TextView = view.findViewById(R.id.textinDaySecond)

            var mItem: Reminder = items[position]
            titleTextView.text = mItem.getreminderName()

            if (mItem.getallowNotification()!!){
                firstTextView.text = context.getString(R.string.notification_on)
            }else{
                firstTextView.text = context.getString(R.string.notification_off)
            }

            var textOptionsImportance:String
            if (mItem.getimportance() == 0){
                textOptionsImportance = "indifferent"
            } else if (mItem.getimportance() == 1){
                textOptionsImportance = "optional"
            } else if (mItem.getimportance() == 2){
                textOptionsImportance = "important"
            } else {
                textOptionsImportance = "very important"
            }

            secondTextView.text = textOptionsImportance

            return view
        }
    }

    class CustomAdapter4 (var mCtx: Context, var resources:Int, var items:List<Food>):
        ArrayAdapter<Food>(mCtx, resources, items) {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
            val view: View = layoutInflater.inflate(resources, null)

            val lay: RelativeLayout = view.findViewById(R.id.relatLayoutListFood)
            val titleTextView: TextView = view.findViewById(R.id.textNameinDayFood)
            val firstTextView: TextView = view.findViewById(R.id.textinDayFirstFood)

            var mItem: Food = items[position]
            if (position == 0){
                titleTextView.text = context.getString(R.string.first_meal)
            } else if (position == 1){
                titleTextView.text = context.getString(R.string.second_meal)
            } else if (position == 2){
                titleTextView.text = context.getString(R.string.third_meal)
            } else if (position == 3){
                titleTextView.text = context.getString(R.string.fourth_meal)
            } else if (position == 4){
                titleTextView.text = context.getString(R.string.fifth_meal)
            }
            if (mItem.getname().equals("")){
                firstTextView.text = context.getString(R.string.not_introduced)
            }else{
                firstTextView.text = mItem.getname()
            }

            return view
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun updateAppearance(){
        // background
        val backgroundLay: ConstraintLayout = findViewById(R.id.dayConstraintLayout)
        val context: Context = backgroundLay.getContext()
        val idBack = resources.getIdentifier(MainController.getcurrent().getappearanceInfo().getbackground(), "drawable", packageName)
        // val drawable = resources.getDrawable(idBack)
        backgroundLay.setBackgroundResource(idBack)
        val array_exemple = MainController.getReminderArray(extraObjectId)!!

        listViewReminders.adapter =
            CustomAdapter3(
                this,
                R.layout.simple_list_item_custom3,
                array_exemple
            )

        listViewReminders.setOnItemClickListener { parent, view, position, id ->
            var listItemId:Reminder = array_exemple.get(position)
            val intentModifReminder= Intent(this, ModifReminderActivity::class.java)
            intentModifReminder.putExtra("extra_object_reminder", listItemId as Serializable)
            startActivity(intentModifReminder)
        }

        listViewReminders.setOnItemLongClickListener(OnItemLongClickListener { arg0, arg1, pos, id ->
            var listItemId:Reminder = array_exemple.get(pos)
            withCustomStyleReminder(listItemId)
            true
        })

        val array_exemple2 = MainController.getTaskArray(extraObjectId)!!

        listViewTasks.adapter =
            CustomAdapter2(
                this,
                R.layout.simple_list_item_custom3,
                array_exemple2
            )

        listViewTasks.setOnItemClickListener { parent, view, position, id ->
            var listItemId:Task = array_exemple2.get(position)
            val intentModifTask= Intent(this, ModifTaskActivity::class.java)
            intentModifTask.putExtra("extra_object_task", listItemId as Serializable)
            startActivity(intentModifTask)
        }

        listViewTasks.setOnItemLongClickListener(OnItemLongClickListener { arg0, arg1, pos, id ->
            var listItemId:Task = array_exemple2.get(pos)
            withCustomStyleTask(listItemId)
            true
        })

        val array_exemple3 = MainController.getFoodArray(extraObjectId)!!

        listViewTodaysFood.adapter =
            CustomAdapter4(
                this,
                R.layout.simple_list_item_custom4,
                array_exemple3
            )

        listViewTodaysFood.setOnItemClickListener { parent, view, position, id ->
            var listItemId:Food = array_exemple3.get(position)
            if (!listItemId.getname().equals("")){
                val intentModifFood= Intent(this, ModifFoodActivity::class.java)
                intentModifFood.putExtra("extra_object_food", listItemId as Serializable)
                startActivity(intentModifFood)
            }
        }

        listViewTodaysFood.setOnItemLongClickListener(OnItemLongClickListener { arg0, arg1, pos, id ->
             var listItemId:Food = array_exemple3.get(pos)
            if (!listItemId.getname().equals("")) {
                withCustomStyleFood(listItemId)
            }
            true
        })

        justifyListViewHeightBasedOnChildren(listViewTodaysFood)
        justifyListViewHeightBasedOnChildren(listViewReminders)
        justifyListViewHeightBasedOnChildren(listViewTasks)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun withCustomStyleFood(listItemId:Food) {

        val positiveButtonClick = { dialog: DialogInterface, which: Int ->
            MainController.deleteFood(listItemId.getfoodDate(),listItemId.getnumber())
            MainController.saveUserAll()
            this.onResume()
        }
        val modifButtonClick = { dialog: DialogInterface, which: Int ->
            val intentModifFood= Intent(this, ModifFoodActivity::class.java)
            intentModifFood.putExtra("extra_object_food", listItemId as Serializable)
            startActivity(intentModifFood)
        }

        val builder = AlertDialog.Builder(ContextThemeWrapper(this, R.style.AlertDialogCustom))

        with(builder)
        {
            setTitle("Dialog on Android")
            setMessage("Are you sure you want to delete this Meal?")
            setPositiveButton(R.string.yes, DialogInterface.OnClickListener(function = positiveButtonClick))
            setNegativeButton(R.string.cancel) { dialog, id -> dialog.cancel() }
            setNeutralButton(R.string.modif_food, modifButtonClick)
            show()
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun withCustomStyleReminder(listItemId:Reminder) {

        val positiveButtonClick = { dialog: DialogInterface, which: Int ->
            MainController.removeReminder(listItemId.getreminderDate(),listItemId.id)
            MainController.saveUserAll()
            this.onResume()
        }
        val modifButtonClick = { dialog: DialogInterface, which: Int ->
            val intentModifReminder= Intent(this, ModifReminderActivity::class.java)
            intentModifReminder.putExtra("extra_object_reminder", listItemId as Serializable)
            startActivity(intentModifReminder)
        }

        val builder = AlertDialog.Builder(ContextThemeWrapper(this, R.style.AlertDialogCustom))

        with(builder)
        {
            setTitle("Dialog on Android")
            setMessage("Are you sure you want to delete this reminder?")
            setPositiveButton(R.string.yes, DialogInterface.OnClickListener(function = positiveButtonClick))
            setNegativeButton(R.string.cancel) { dialog, id -> dialog.cancel() }
            setNeutralButton(R.string.modif_reminder, modifButtonClick)
            show()
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun withCustomStyleTask(listItemId:Task) {

        val positiveButtonClick = { dialog: DialogInterface, which: Int ->
            MainController.removeTask(listItemId.gettaskDate(),listItemId.id)
            MainController.saveUserAll()
            this.onResume()
        }
        val modifButtonClick = { dialog: DialogInterface, which: Int ->
            val intentModifTask= Intent(this, ModifTaskActivity::class.java)
            intentModifTask.putExtra("extra_object_task", listItemId as Serializable)
            startActivity(intentModifTask)
        }

        val builder = AlertDialog.Builder(ContextThemeWrapper(this, R.style.AlertDialogCustom))

        with(builder)
        {
            setTitle("Dialog on Android")
            setMessage("Are you sure you want to delete this task?")
            setPositiveButton(R.string.yes, DialogInterface.OnClickListener(function = positiveButtonClick))
            setNegativeButton(R.string.cancel) { dialog, id -> dialog.cancel() }
            setNeutralButton(R.string.modif_task, modifButtonClick)
            show()
        }
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_gohome_calendar_toolbar,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_gocalendar -> { actiongoCalendar()}
            R.id.action_gohome -> { actiongoHome()}
        }
        return super.onOptionsItemSelected(item)
    }

    fun actionAddReminder(view: View) {
        val intentAddReminder= Intent(this, AddReminderActivity::class.java)
        intentAddReminder.putExtra("extra_object_dayID", extraObjectId as String)
        startActivity(intentAddReminder)
        //Toast.makeText(this,"Functionality Add REMINDER not implemented yet",Toast.LENGTH_LONG).show()
    }
    fun actionAddTask(view: View) {
        val intentAddTask= Intent(this, AddTaskActivity::class.java)
        intentAddTask.putExtra("extra_object_dayID", extraObjectId as String)
        startActivity(intentAddTask)
        //Toast.makeText(this,"Functionality Add TASK not implemented yet",Toast.LENGTH_LONG).show()
    }
    fun actionAddFood(view: View) {
        val intentAddFood = Intent(this, AddFoodActivity::class.java)
        intentAddFood.putExtra("extra_object_dayID", extraObjectId as String)
        startActivity(intentAddFood)
        //Toast.makeText(this,"Functionality Add FOOD not implemented yet",Toast.LENGTH_LONG).show()
    }
    fun actiongoCalendar(){
        val intent= Intent(this, Calendar::class.java)
        startActivity(intent)
    }
    fun actiongoHome(){
        val intent= Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}