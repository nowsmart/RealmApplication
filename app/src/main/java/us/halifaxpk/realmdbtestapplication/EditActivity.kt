package us.halifaxpk.realmdbtestapplication

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import io.realm.Realm
import us.halifaxpk.realmdbtestapplication.databinding.ActivityEditBinding

class EditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val courseJson = this.intent.extras?.getString("course")
        val course = Gson().fromJson(courseJson, DataModal::class.java)
        binding.tvId.text = course.Id.toString()
        binding.idEdtCourseName.setText(course.courseName)
        binding.idEdtCourseDescription.setText(course.courseDescription)
        binding.idEdtCourseDuration.setText(course.courseDuration)
        binding.idEdtCourseTracks.setText(course.courseTracks)
        binding.idBtnUpdateCourse.setOnClickListener {
            Realm.init(this)
            val realm = Realm.getDefaultInstance()
            val updatedCourse = DataModal(
                binding.tvId.text.toString().toLong(),
                binding.idEdtCourseName.text.toString(),
                binding.idEdtCourseDescription.text.toString(),
                binding.idEdtCourseDuration.text.toString(),
                binding.idEdtCourseTracks.text.toString()
            )
            realm.executeTransactionAsync(Realm.Transaction{
                it.copyToRealmOrUpdate(updatedCourse)
            }, Realm.Transaction.OnSuccess {
                Toast.makeText(this, "Data updated successfully in database", Toast.LENGTH_LONG).show()

                finish()
            })
        }
    }
}