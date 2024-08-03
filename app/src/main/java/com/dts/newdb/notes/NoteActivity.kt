package com.dts.newdb.notes

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.dts.newdb.R

class NoteActivity : AppCompatActivity() {

    private lateinit var editTextFileName: EditText
    private lateinit var editTextNoteContent: EditText
    private lateinit var buttonSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)

        editTextFileName = findViewById(R.id.editTextFileName)
        editTextNoteContent = findViewById(R.id.editTextNoteContent)
        buttonSave = findViewById(R.id.buttonSave)

        buttonSave.setOnClickListener {
            val fileName = editTextFileName.text.toString()
            val noteContent = editTextNoteContent.text.toString()

            if (fileName.isNotEmpty() && noteContent.isNotEmpty()) {
                val noteStorage = NoteStorage(this)
                noteStorage.saveFile(fileName, noteContent)

                val resultIntent = Intent().apply {
                    putExtra("FILE_NAME", fileName)
                }
                setResult(Activity.RESULT_OK, resultIntent)
                finish()
            }
        }
    }
}
