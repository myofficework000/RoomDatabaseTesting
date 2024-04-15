package abhishek.pathak.roomdatabasedemo

import abhishek.pathak.roomdatabasedemo.local.AppDatabase
import abhishek.pathak.roomdatabasedemo.local.Note
import abhishek.pathak.roomdatabasedemo.local.NotesDao
import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class NotesDaoTest {
    @get:Rule
    val instantTaskExecutor = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val coroutineRule = MainCoroutineRule()

    private lateinit var appDatabase: AppDatabase
    private lateinit var notesDao: NotesDao

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        notesDao = appDatabase.getNoteDao()
    }

    private fun createNote() = Note(id = 1, title = TEST_TITLE, desc = TEST_DESC)
    private fun createUpdateNote() =
        Note(id = 1, title = TEST_TITLE_UPDATE, desc = TEST_DESC_UPDATE)

    @Test
    fun inThisTest_GIVEN_Note_WHEN_INSERT_invoked_THEN_VERIFY() = runTest {
        val note = createNote()
        notesDao.insertNote(note)
        val retrievedAllNotes = notesDao.fetchNotes()

        retrievedAllNotes.observeForever {
            val firstNote = it[0]
            assertEquals(firstNote.id, 1)
            assertEquals(firstNote.title, TEST_TITLE)
            assertEquals(firstNote.desc, TEST_DESC)
        }
    }

    @Test
    fun inThisTest_GIVEN_Note_WHEN_DELETE_invoked_THEN_VERIFY() = runTest {
        val note = createNote()
        notesDao.insertNote(note)

        val observer = Observer<List<Note>> { notes ->
            if (notes.isNotEmpty()) {
                runBlocking {
                    notesDao.updateNote(createUpdateNote())
                }
            }
        }

        notesDao.fetchNotes().observeForever(observer)

        runBlocking {
            val noteUpdated = notesDao.fetchNotes().value?.get(0)

            assertNotEquals(TEST_TITLE, noteUpdated?.title)
            assertNotEquals(TEST_DESC, noteUpdated?.desc)
            assertEquals(TEST_TITLE_UPDATE, noteUpdated?.title)
            assertEquals(TEST_DESC_UPDATE, noteUpdated?.desc)
        }

        notesDao.fetchNotes().removeObserver(observer)
    }

    @Test
    fun inThisTest_GIVEN_Note_WHEN_Update_invoked_THEN_VERIFY() = runTest {
        val note = createNote()
        notesDao.insertNote(note)


        val observer = Observer<List<Note>> { notes ->
            if (notes.isNotEmpty()) {
                runBlocking {
                    notesDao.deleteNote(notes[0])
                }
            }
        }
        notesDao.fetchNotes().observeForever(observer)

        runBlocking {
            assertEquals(null, notesDao.fetchNotes().value?.size)
        }

        notesDao.fetchNotes().removeObserver(observer)
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        appDatabase.close()
    }

    private companion object {
        const val TEST_TITLE = "Testing with Room database"
        const val TEST_DESC = "TESTING In Details With Room"
        const val TEST_TITLE_UPDATE = "Testing with Room database update"
        const val TEST_DESC_UPDATE = "TESTING In Details With Room update"
    }
}