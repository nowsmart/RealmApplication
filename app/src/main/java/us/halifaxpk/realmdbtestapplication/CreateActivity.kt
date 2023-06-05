package us.halifaxpk.realmdbtestapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import io.realm.Realm
import io.realm.kotlin.where
import us.halifaxpk.realmdbtestapplication.databinding.ActivityCreateBinding

class CreateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateBinding
    private lateinit var realm: Realm
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Realm.init(this)
        realm = Realm.getDefaultInstance();

        binding.idBtnAddCourse.setOnClickListener {
            addDataToDatabase(
                binding.idEdtCourseName.text.toString(),
                binding.idEdtCourseDescription.text.toString(),
                binding.idEdtCourseDuration.text.toString(),
                binding.idEdtCourseTracks.text.toString()
            )
        }
        binding.idBtnGoBack.setOnClickListener {
            finish()
        }
    }

    private fun addDataToDatabase(
        courseName: String,
        courseDescription: String,
        courseDuration: String,
        courseTracks: String
    ) {
        val id = realm.where<DataModal>().max("Id")?.toLong()
        val newId = if (id == null) 1 else id + 1
        val modal = DataModal(newId, courseName, courseDescription, courseDuration, courseTracks)
        realm.executeTransactionAsync(Realm.Transaction {
            it.copyToRealm(modal)

        }, Realm.Transaction.OnSuccess {
            Toast.makeText(this, "Data added successfully in database", Toast.LENGTH_LONG).show()
            binding.idEdtCourseName.text.clear()
            binding.idEdtCourseDescription.text.clear()
            binding.idEdtCourseDuration.text.clear()
            binding.idEdtCourseTracks.text.clear()
        })
    }
}