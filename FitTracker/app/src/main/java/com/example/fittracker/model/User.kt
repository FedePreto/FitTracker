package com.example.fittracker.model

import android.widget.EditText

data class User(
    val username: String?,
    val name: String?,
    val lastname: String?,
    val email: String?

    ){

    constructor(): this("","","","")
}