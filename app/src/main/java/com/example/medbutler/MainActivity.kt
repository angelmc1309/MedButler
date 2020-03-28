package com.example.medbutler

import android.animation.ValueAnimator
import android.content.Context
import android.content.Intent
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_page)
    }

    override fun onResume() {
        super.onResume()
        updateAppearance()
        //canvair init per actualitzar imatge. Afegir actualitzar color fons imatge/layout
    }
    companion object {
        var colorTheme:Int = -16728876
        var profileImagePersonId:String = "man_profile_image"
        var profileImageBackgroundId:String = "man_profile_background" //initicalitzat totes
        var calendarImageId:String = "cal_im"
        var calendarImageBackgroundId:String = "cal_back"
        var settingsImageId:String = "set1_im"
        var settingsImageBackgroundId:String = "set1_gray" //initicalitzat totes
        var treatmentImageFirstId:String = "treat1_1"
        var treatmentImageSecondId:String = "treat1_2" //initicalitzat totes
    }


    fun updateAppearance(){
        // Set PorterDuffColorFilter with color and mode
        val mode = PorterDuff.Mode.LIGHTEN //ADD
        val mode2 = PorterDuff.Mode.DARKEN

        // profileImagePerson
        val profileImagePerson:ImageView = findViewById(R.id.profileImagePerson)
        val context: Context = profileImagePerson.getContext()
        val id: Int = context.getResources().getIdentifier(profileImagePersonId, "drawable", context.getPackageName())
        profileImagePerson.setImageResource(id)

        // profileImageBackground
        val profileImageBackground:ImageView = findViewById(R.id.profileImageBackground)
        val id2: Int = context.getResources().getIdentifier(profileImageBackgroundId, "drawable", context.getPackageName())
        profileImageBackground.setImageResource(id2)
        profileImageBackground.setColorFilter(colorTheme, mode)
        val profileLayoutBackground:RelativeLayout = findViewById(R.id.profileLayout)
        profileLayoutBackground.setBackgroundColor(colorTheme)

        // calendarImage
        val calendarImage:ImageView = findViewById(R.id.calendarImage)
        val id3: Int = context.getResources().getIdentifier(calendarImageId, "drawable", context.getPackageName())
        calendarImage.setImageResource(id3)

        // calendarImageBackground
        val calendarImageBackground:ImageView = findViewById(R.id.calendarImageBackground)
        val id4: Int = context.getResources().getIdentifier(calendarImageBackgroundId, "drawable", context.getPackageName())
        calendarImageBackground.setImageResource(id4)
        calendarImageBackground.setColorFilter(colorTheme, mode)
        val calendarLayoutBackground:RelativeLayout = findViewById(R.id.calendarLayout)
        calendarLayoutBackground.setBackgroundColor(colorTheme)

        // settingsImage
        val settingsImage:ImageView = findViewById(R.id.settingsImage)
        val id5: Int = context.getResources().getIdentifier(settingsImageId, "drawable", context.getPackageName())
        settingsImage.setImageResource(id5)

        // settingsImageBackground
        val settingsImageBackground:ImageView = findViewById(R.id.settingsImageBackground)
        val id6: Int = context.getResources().getIdentifier(settingsImageBackgroundId, "drawable", context.getPackageName())
        settingsImageBackground.setImageResource(id6)
        settingsImageBackground.setColorFilter(colorTheme, mode2)
        val settingsLayoutBackground:RelativeLayout = findViewById(R.id.settingsLayout)
        settingsLayoutBackground.setBackgroundColor(colorTheme)

        // treatmentImageFirst
        val treatmentImageFirst:ImageView = findViewById(R.id.treatmentImageFirst)
        val id7: Int = context.getResources().getIdentifier(treatmentImageFirstId, "drawable", context.getPackageName())
        treatmentImageFirst.setImageResource(id7)

        // treatmentImageSecond
        val treatmentImageSecond:ImageView = findViewById(R.id.treatmentImageSecond)
        val id8: Int = context.getResources().getIdentifier(treatmentImageSecondId, "drawable", context.getPackageName())
        treatmentImageSecond.setImageResource(id8)
        val treatmentLayoutBackground:RelativeLayout = findViewById(R.id.treatmentLayout)
        treatmentLayoutBackground.setBackgroundColor(colorTheme)

        animacio(treatmentImageFirst,treatmentImageSecond)
    }

    fun animacio(imageFluix:ImageView,imageFort:ImageView){

        val animator = ValueAnimator.ofFloat(0f, 1f)
        animator.addUpdateListener(object: ValueAnimator.AnimatorUpdateListener{
            override fun onAnimationUpdate(animation: ValueAnimator?) {
                val animatedValue =animation?.animatedValue as Float
                imageFort.setAlpha(animation.animatedValue as Float)
            }
        })

        animator.duration = 1500
        animator.repeatMode = ValueAnimator.REVERSE
        animator.repeatCount = -1
        animator.start()
    }

    fun actionCalendar(view: View){
        val intent= Intent(this, Calendar::class.java)
        startActivity(intent)
    }
    fun actionUser(view: View){
        val intent= Intent(this, UserProfile::class.java)
        startActivity(intent)
    }
    fun actionMeds(view: View){
        val intent= Intent(this, MedListActivity::class.java)
        startActivity(intent)
    }
    fun actionSettings(view: View){
        val intent= Intent(this, SettingsActivity()::class.java)
        startActivity(intent)
    }
}
