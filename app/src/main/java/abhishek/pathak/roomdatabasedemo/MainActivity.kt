package abhishek.pathak.roomdatabasedemo

import abhishek.pathak.roomdatabasedemo.databinding.ActivityMainBinding
import abhishek.pathak.roomdatabasedemo.local.Note
import abhishek.pathak.roomdatabasedemo.notes.NotesViewModel
import abhishek.pathak.roomdatabasedemo.notes.NotesViewModelFactory
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: NotesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        //initDatabase()
        initViewModel()
        initViews()
    }

    private fun initViewModel() {
        val factory = NotesViewModelFactory(this.application)
        viewModel = ViewModelProvider(this, factory)[NotesViewModel::class.java]
    }

    /*  private fun initDatabase() {

      }*/

    private fun initViews() {
        binding.btnCreate.setOnClickListener {
         /*   noteDao.insertNote(
                Note(
                    title = "Going to Gym",
                    desc = "${Calendar.getInstance().timeInMillis}"
                )
            )*/
        }

/*        binding.btnRead.setOnClickListener {
            val list = noteDao.fetchNotes()
            for (item in list) {
                print(item)
            }

            binding.listNote.adapter = ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1, list
            )
        }

        binding.btnDelete.setOnClickListener {
            noteDao.deleteNote(Note(id = 1, title = "Going fsdswd to Gym", "descsss"))
        }

        binding.btnUpdate.setOnClickListener {
            noteDao.updateNote(Note(title = "Going to market", "descc"))
        }*/
    }
}