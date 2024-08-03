package com.dts.newdb.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.dts.newdb.R
import java.text.SimpleDateFormat
import java.util.*

class NoteAdapter(
    private val notes: List<Pair<String, String>>,
    private val onDelete: (String) -> Unit,
    private val onEdit: (String, String) -> Unit
) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val (fileName, content) = notes[position]
        holder.bind(fileName, content)
    }

    override fun getItemCount() = notes.size

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textViewFileName: TextView = itemView.findViewById(R.id.textViewFileName)
        private val textViewDate: TextView = itemView.findViewById(R.id.textViewDate)

        fun bind(fileName: String, content: String) {
            textViewFileName.text = fileName
            textViewDate.text = SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.getDefault()).format(Date())

            itemView.setOnClickListener {
                onEdit(fileName, content)
            }

            itemView.setOnLongClickListener {
                Toast.makeText(it.context, "Hold detected for $fileName", Toast.LENGTH_SHORT).show()
                AlertDialog.Builder(itemView.context)
                    .setTitle("Konfirmasi Hapus")
                    .setMessage("Apakah Anda yakin ingin menghapus catatan $fileName?")
                    .setPositiveButton("Yes") { _, _ ->
                        onDelete(fileName)
                    }
                    .setNegativeButton("No", null)
                    .show()
                true
            }
        }
    }
}