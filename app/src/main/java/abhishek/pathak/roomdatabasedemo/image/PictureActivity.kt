package abhishek.pathak.roomdatabasedemo.image

import abhishek.pathak.roomdatabasedemo.databinding.ActivityPictureBinding
import abhishek.pathak.roomdatabasedemo.local.AppDatabase
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlin.random.Random

class PictureActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPictureBinding
    private lateinit var appDatabase: AppDatabase
    private lateinit var pictureDao: PictureDao
    private lateinit var picture: Picture

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPictureBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initDatabase()
        initViews()
    }

    private fun initDatabase() {
        appDatabase = AppDatabase.getInstance(this.applicationContext)
        pictureDao = appDatabase.getPictureDao()
    }

    private fun initViews() {
        binding.selectImage.setOnClickListener {
            selectImage()
        }
        binding.saveImage.setOnClickListener {
            saveImage()
        }
        binding.fetchImage.setOnClickListener {
            val firstPicture = pictureDao.getPictures()[1].path
            Glide.with(this).load(firstPicture).into(binding.fetchedImageView)
        }
    }

    private fun saveImage() {
        if (::picture.isInitialized) {
            pictureDao.insert(picture)
        }
    }

    private fun selectImage() {
        val intent = Intent(Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, 100)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == Activity.RESULT_OK && data != null) {
            val imageUri: Uri = data.data!!
            picture = Picture(path = imageUri.toString(), name = "Image${Random(3)}")
            Glide.with(this).load(imageUri).into(binding.imageView)
        }
    }
}