package com.dts.newdb.notes

import android.content.Context
import java.io.File

class NoteStorage(private val context: Context) {

    private val fileDir: File = context.filesDir

    fun saveFile(fileName: String, content: String) {
        val file = File(fileDir, "$fileName.txt")
        file.writeText(content)
    }

    fun deleteFile(fileName: String) {
        val file = File(fileDir, "$fileName.txt")
        if (file.exists()) {
            file.delete()
        }
    }

    fun getFiles(): List<Pair<String, String>> {
        val files = fileDir.listFiles()
        return files?.mapNotNull { file ->
            val fileContent = file.readText()
            Pair(file.nameWithoutExtension, fileContent)
        } ?: emptyList()
    }
}
