package com.dts.newdb.notes

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.widget.Toolbar
import com.dts.newdb.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class NotesHarianActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var noteAdapter: NoteAdapter
    private lateinit var fabAddNote: FloatingActionButton
    private lateinit var noteStorage: NoteStorage
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes_harian)

        recyclerView = findViewById(R.id.recyclerViewFiles)
        fabAddNote = findViewById(R.id.fabAddNote)
        toolbar = findViewById(R.id.toolbar)

        setSupportActionBar(toolbar)
        supportActionBar?.title = "Catatan Harian"

        noteStorage = NoteStorage(this)

        recyclerView.layoutManager = LinearLayoutManager(this)
        updateFileList()

        fabAddNote.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_ADD_NOTE)
        }
    }

    private fun updateFileList() {
        val files = noteStorage.getFiles()
        noteAdapter = NoteAdapter(files,
            onDelete = { fileName ->
                noteStorage.deleteFile(fileName)
                updateFileList() // Refresh list after delete
            },
            onEdit = { fileName, fileContent ->
                val intent = Intent(this, DetailActivity::class.java).apply {
                    putExtra("FILE_NAME", fileName)
                    putExtra("FILE_CONTENT", fileContent)
                }
                startActivityForResult(intent, REQUEST_CODE_EDIT_NOTE)
            }
        )
        recyclerView.adapter = noteAdapter
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            updateFileList() // Refresh list after editing or adding note
        }
    }

    companion object {
        private const val REQUEST_CODE_ADD_NOTE = 1
        private const val REQUEST_CODE_EDIT_NOTE = 2
    }
}
