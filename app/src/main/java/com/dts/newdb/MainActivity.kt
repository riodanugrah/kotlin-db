package com.dts.newdb

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var buttonNotesHarian: Button
    private lateinit var buttonComingSoon: Button
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "VSGA Menu"

        buttonNotesHarian = findViewById(R.id.buttonNotesHarian)
        buttonComingSoon = findViewById(R.id.buttonComingSoon)

        buttonNotesHarian.setOnClickListener {
            val intent = Intent(this, com.dts.newdb.notes.NotesHarianActivity::class.java)
            startActivity(intent)
        }

        buttonComingSoon.setOnClickListener {
            val intent = Intent(this, com.dts.newdb.login.LoginActivity::class.java)
            startActivity(intent)
        }
    }
}
