package com.dts.newdb.login

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dts.newdb.R
import java.io.File

class RegisterActivity : AppCompatActivity() {
    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var fullNameEditText: EditText
    private lateinit var schoolEditText: EditText
    private lateinit var addressEditText: EditText
    private lateinit var registerButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        usernameEditText = findViewById(R.id.editTextUsername)
        passwordEditText = findViewById(R.id.editTextPassword)
        emailEditText = findViewById(R.id.editTextEmail)
        fullNameEditText = findViewById(R.id.editTextFullName)
        schoolEditText = findViewById(R.id.editTextSchool)
        addressEditText = findViewById(R.id.editTextAddress)
        registerButton = findViewById(R.id.buttonRegister)

        registerButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()
            val email = emailEditText.text.toString()
            val fullName = fullNameEditText.text.toString()
            val school = schoolEditText.text.toString()
            val address = addressEditText.text.toString()

            if (username.isNotEmpty() && password.isNotEmpty()) {
                if (register(username, password, email, fullName, school, address)) {
                    Toast.makeText(this, "Registration successful for $username", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this, "Username already exists", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Username and password cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun register(username: String, password: String, email: String, fullName: String, school: String, address: String): Boolean {
        val file = File(getExternalFilesDir(null), "$username.txt")
        if (file.exists()) {
            return false
        }
        file.writeText("$password\n$email\n$fullName\n$school\n$address")
        return true
    }
}
