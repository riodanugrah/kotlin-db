package com.dts.newdb.notes

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.dts.newdb.R

class DetailActivity : AppCompatActivity() {

    private lateinit var editTextFileName: EditText
    private lateinit var editTextNoteContent: EditText
    private lateinit var buttonSave: Button
    private lateinit var toolbar: Toolbar

    private lateinit var noteStorage: NoteStorage
    private lateinit var oldFileName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        toolbar = findViewById(R.id.toolbar)
        editTextFileName = findViewById(R.id.editTextFileName)
        editTextNoteContent = findViewById(R.id.editTextNoteContent)
        buttonSave = findViewById(R.id.buttonSave)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Detail Catatan"

        oldFileName = intent.getStringExtra("FILE_NAME") ?: ""
        val fileContent = intent.getStringExtra("FILE_CONTENT") ?: ""

        editTextFileName.setText(oldFileName)
        editTextNoteContent.setText(fileContent)

        buttonSave.setOnClickListener {
            saveNote()
        }

        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        noteStorage = NoteStorage(this)
    }

    private fun saveNote() {
        val newFileName = editTextFileName.text.toString()
        val newContent = editTextNoteContent.text.toString()

        if (newFileName.isNotBlank() && newContent.isNotBlank()) {
            if (oldFileName != newFileName) {
                noteStorage.deleteFile(oldFileName) // Hapus file lama
            }
            noteStorage.saveFile(newFileName, newContent) // Simpan file baru
            Toast.makeText(this, "Catatan disimpan", Toast.LENGTH_SHORT).show()
            setResult(RESULT_OK) // Set hasil untuk Activity Result
            finish()
        } else {
            Toast.makeText(this, "Nama dan isi catatan tidak boleh kosong", Toast.LENGTH_SHORT).show()
        }
    }
}
