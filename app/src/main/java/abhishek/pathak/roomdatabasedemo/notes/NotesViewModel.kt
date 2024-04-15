package abhishek.pathak.roomdatabasedemo.notes

import abhishek.pathak.roomdatabasedemo.local.AppDatabase
import abhishek.pathak.roomdatabasedemo.local.Note
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class NotesViewModel(application: Application) : AndroidViewModel(application) {

    private val noteDao by lazy { AppDatabase.getInstance(application).getNoteDao() }
    val allData: LiveData<List<Note>> get() = noteDao.fetchNotes()

    fun insertIntoNotes(note: Note) {
        viewModelScope.launch(IO) {
            noteDao.insertNote(note)
        }
    }

    fun deleteIntoNotes(note: Note) {
        viewModelScope.launch(IO) {
            noteDao.deleteNote(note)
        }
    }

    // similarly for update and fetch methods
}