package abhishek.pathak.roomdatabasedemo.notes

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class NotesViewModelFactory constructor(private val application: Application) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(NotesViewModel::class.java)) {
            NotesViewModel(this.application) as T
        } else {
            throw IllegalArgumentException("Viewmodel not found")
        }
    }
}