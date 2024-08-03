package com.dts.newdb.login

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.dts.newdb.R
import java.io.File

class HomeActivity : AppCompatActivity() {
    private lateinit var userInfoTextView: TextView
    private lateinit var logoutButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        userInfoTextView = findViewById(R.id.textViewUserInfo)
        logoutButton = findViewById(R.id.buttonLogout)

        val username = intent.getStringExtra("username") ?: "Unknown"
        val file = File(getExternalFilesDir(null), "$username.txt")
        if (file.exists()) {
            val userInfo = file.readText().split("\n").joinToString("\n") { it }
            userInfoTextView.text = userInfo
        } else {
            userInfoTextView.text = "No information available"
        }

        logoutButton.setOnClickListener {
            finish()
        }
    }
}
