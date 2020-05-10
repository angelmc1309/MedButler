package com.example.medbutler.classes.model

import java.io.Serializable

class UserAppearenceInfo:Serializable {
    private var background:String = "background_feather_silver"
    private var changeToolbarWithColorTheme:Boolean = true
    private var colorTheme:Int = -16728876
    private var darkerToolbarColorText:Int = -16757931
    private var toolbarColor:Int = -16728876
    private var brighterToolbarColor:Int = -16714497
    private var darkerToolbarColor:Int = -16743276
    private var profileImagePersonId:String = "man_profile_image"
    private var profileImageBackgroundId:String = "man_profile_background"
    private var calendarImageId:String = "cal_im"
    private var calendarImageBackgroundId:String = "cal_back"
    private var settingsImageId:String = "set1_im"
    private var settingsImageBackgroundId:String = "set1_gray"
    private var treatmentImageFirstId:String = "treat2_1"
    private var treatmentImageSecondId:String = "treat2_2"

    constructor(){
    }

    constructor(background:String,changeToolbarWithColorTheme:Boolean,colorTheme:Int,darkerToolbarColorText:Int,toolbarColor:Int,
                brighterToolbarColor:Int,darkerToolbarColor:Int,profileImagePersonId:String,profileImageBackgroundId:String,calendarImageId:String,
                calendarImageBackgroundId:String,settingsImageId:String,settingsImageBackgroundId:String,treatmentImageFirstId:String,treatmentImageSecondId:String){
        this.background = background
        this.changeToolbarWithColorTheme = changeToolbarWithColorTheme
        this.colorTheme = colorTheme
        this.darkerToolbarColorText = darkerToolbarColorText
        this.toolbarColor = toolbarColor
        this.brighterToolbarColor = brighterToolbarColor
        this.darkerToolbarColor = darkerToolbarColor
        this.profileImagePersonId = profileImagePersonId
        this.profileImageBackgroundId = profileImageBackgroundId
        this.calendarImageId = calendarImageId
        this.calendarImageBackgroundId = calendarImageBackgroundId
        this.settingsImageId = settingsImageId
        this.settingsImageBackgroundId = settingsImageBackgroundId
        this.treatmentImageFirstId = treatmentImageFirstId
        this.treatmentImageSecondId = treatmentImageSecondId
    }

    fun setbackground(background: String) {
        this.background = background
    }
    fun getbackground(): String {
        return this.background
    }
    fun setchangeToolbarWithColorTheme(changeToolbarWithColorTheme: Boolean) {
        this.changeToolbarWithColorTheme = changeToolbarWithColorTheme
    }
    fun getchangeToolbarWithColorTheme(): Boolean {
        return this.changeToolbarWithColorTheme
    }
    fun setcolorTheme(colorTheme: Int) {
        this.colorTheme = colorTheme
    }
    fun getcolorTheme(): Int {
        return this.colorTheme
    }
    fun setdarkerToolbarColorText(darkerToolbarColorText: Int) {
        this.darkerToolbarColorText = darkerToolbarColorText
    }
    fun getdarkerToolbarColorText(): Int {
        return this.darkerToolbarColorText
    }
    fun settoolbarColor(toolbarColor: Int) {
        this.toolbarColor = toolbarColor
    }
    fun gettoolbarColor(): Int {
        return this.toolbarColor
    }
    fun setbrighterToolbarColor(brighterToolbarColor: Int) {
        this.brighterToolbarColor = brighterToolbarColor
    }
    fun getbrighterToolbarColor(): Int {
        return this.brighterToolbarColor
    }
    fun setdarkerToolbarColor(darkerToolbarColor: Int) {
        this.darkerToolbarColor = darkerToolbarColor
    }
    fun getdarkerToolbarColor(): Int {
        return this.darkerToolbarColor
    }
    fun setprofileImagePersonId(profileImagePersonId: String) {
        this.profileImagePersonId = profileImagePersonId
    }
    fun getprofileImagePersonId(): String {
        return this.profileImagePersonId
    }
    fun setprofileImageBackgroundId(profileImageBackgroundId: String) {
        this.profileImageBackgroundId = profileImageBackgroundId
    }
    fun getprofileImageBackgroundId(): String {
        return this.profileImageBackgroundId
    }
    fun setcalendarImageId(calendarImageId: String) {
        this.calendarImageId = calendarImageId
    }
    fun getcalendarImageId(): String {
        return this.calendarImageId
    }
    fun setcalendarImageBackgroundId(calendarImageBackgroundId: String) {
        this.calendarImageBackgroundId = calendarImageBackgroundId
    }
    fun getcalendarImageBackgroundId(): String {
        return this.calendarImageBackgroundId
    }
    fun setsettingsImageId(settingsImageId: String) {
        this.settingsImageId = settingsImageId
    }
    fun getsettingsImageId(): String {
        return this.settingsImageId
    }
    fun setsettingsImageBackgroundId(settingsImageBackgroundId: String) {
        this.settingsImageBackgroundId = settingsImageBackgroundId
    }
    fun getsettingsImageBackgroundId(): String {
        return this.settingsImageBackgroundId
    }
    fun settreatmentImageFirstId(treatmentImageFirstId: String) {
        this.treatmentImageFirstId = treatmentImageFirstId
    }
    fun gettreatmentImageFirstId(): String {
        return this.treatmentImageFirstId
    }
    fun settreatmentImageSecondId(treatmentImageSecondId: String) {
        this.treatmentImageSecondId = treatmentImageSecondId
    }
    fun gettreatmentImageSecondId(): String {
        return this.treatmentImageSecondId
    }
}