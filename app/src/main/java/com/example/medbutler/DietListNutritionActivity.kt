package com.example.medbutler

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.ms.square.android.expandabletextview.ExpandableTextView

class DietListNutritionActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diet_nutrition)
            updateAppearance()
        var expandableTextView:ExpandableTextView = findViewById(R.id.expandable_text_view)
        expandableTextView.text = "\n" + "Nutrition recommendations\n\nLimite los alimentos con colesterol. Si está tratando de bajar su colesterol, debe consumir menos de 200 mg al día. El colesterol se encuentra en alimentos de origen animal como el hígado y otras vísceras, yemas de huevo, camarones y productos lácteos de leche entera.\n" +
                "\n" +
                "Coma mucha fibra soluble. Los alimentos ricos en fibra soluble ayudan a evitar que el tracto digestivo absorba el colesterol. Estos alimentos incluyen\n" +
                "\n" +
                "Cereales de grano entero como la avena y el salvado de avena\n" +
                "Frutas como manzanas, plátanos, naranjas, peras y ciruelas\n" +
                "Legumbres como frijoles, lentejas, garbanzos, frijoles de carete y habas\n" +
                "Consuma muchas frutas y verduras. Una dieta rica en frutas y verduras puede aumentar las sustancias importantes que reducen el colesterol en su dieta. Estas sustancias, llamadas estanoles o esteroles vegetales, funcionan como fibra soluble.\n" +
                "\n" +
                "Coma pescado rico en ácidos grasos omega-3. Estos ácidos no disminuirán su nivel de colesterol malo (LDL), pero pueden ayudar a subir su nivel de colesterol bueno (HDL). Estas grasas también pueden proteger a su corazón de coágulos de sangre e inflamación y reducir su riesgo de ataque cardíaco. Buenas fuentes de ácidos grasos omega-3 incluyen el salmón, el atún (enlatado o fresco) y la caballa. Intente comer estos pescados dos veces a la semana.\n" +
                "\n" +
                "Limite la sal. Debe intentar limitar la cantidad de sodio (sal) que consume a no más de 2,300 miligramos (aproximadamente una cucharadita de sal) por día. Eso incluye toda la sal que consume, ya sea que se haya agregado en la cocina o en la mesa, o que ya esté presente en los productos alimenticios. Limitar la sal no reducirá el colesterol, pero puede bajar el riesgo de enfermedades cardíacas al ayudar a reducir la presión arterial. Puede reducir la sal eligiendo alimentos con bajo contenido de sal y \"sin sal agregada\", además de preferir condimentos en la mesa o al cocinar en vez de sal.\n" +
                "\n" +
                "Limite el alcohol. El alcohol añade calorías adicionales, las que pueden llevar al aumento de peso. Tener sobrepeso puede elevar su nivel de colesterol malo y disminuir su nivel de colesterol bueno. Demasiado alcohol también puede aumentar su riesgo de enfermedades del corazón, porque puede elevar su presión arterial y el nivel de triglicéridos. Una bebida es un vaso de vino, cerveza o una pequeña cantidad de licor fuerte, y la recomendación es:"
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
        var toolbarTreatment: LinearLayout = findViewById(R.id.toolbar_treatment)
        var medListBut: TextView = findViewById(R.id.med_list)
        var dietListBut: TextView = findViewById(R.id.diet_list)
        var addDietBut: Button = findViewById(R.id.addDiet)
        toolbar.setBackgroundColor(MainActivity.darkerToolbarColor)
        toolbarTreatment.setBackgroundColor(MainActivity.toolbarColor)
        medListBut.setBackgroundColor(MainActivity.brighterToolbarColor)
        medListBut.setTextColor(MainActivity.darkerToolbarColorText)
        dietListBut.setBackgroundColor(MainActivity.toolbarColor)
        dietListBut.setTextColor(MainActivity.darkerToolbarColorText)
        userBut.setBackgroundColor(MainActivity.toolbarColor)
        calendarButt.setBackgroundColor(MainActivity.toolbarColor)
        settingsBut.setBackgroundColor(MainActivity.toolbarColor)
        medsBut.setBackgroundColor(MainActivity.toolbarColor)
        addDietBut.setBackgroundTintList(ColorStateList.valueOf(MainActivity.brighterToolbarColor))
        addDietBut.setTextColor(MainActivity.darkerToolbarColorText)
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
    fun actionAddDiet(view: View){
        val intent= Intent(this, AddDietActivity::class.java)
        startActivity(intent)
    }
}