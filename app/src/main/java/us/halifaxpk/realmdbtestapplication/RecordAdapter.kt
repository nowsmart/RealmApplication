package us.halifaxpk.realmdbtestapplication

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import io.realm.Realm
import io.realm.kotlin.delete

class RecordAdapter(private val context: Context, private val list: ArrayList<DataModal>) :
    RecyclerView.Adapter<MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.recycle_rview_item, parent, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val course = list[position]
        val tvBtnEdit = TextView(context)
        val tvBtnDelete = TextView(context)
        holder.tvCourseId.text = course.Id.toString()
        holder.tvCourseName.text = course.courseName
        holder.tvCourseDescription.text = course.courseDescription
        holder.tvCourseDuration.text = course.courseDuration
        holder.tvCourseTracks.text = course.courseTracks

        tvBtnEdit.text = "Edit |"
        tvBtnDelete.text = " Delete"
        tvBtnEdit.setOnClickListener {
            val intent = Intent(context, EditActivity::class.java)
            val course1 = DataModal(
                course.Id,
                course.courseName,
                course.courseDescription,
                course.courseDuration,
                course.courseTracks
            )
            intent.putExtra("course", Gson().toJson(course1))
            context.startActivity(intent)
        }
        holder.actionLL.addView(tvBtnEdit)
        tvBtnDelete.setOnClickListener {
            Realm.init(context)
            val realm = Realm.getDefaultInstance()
            val courseId = course.Id
            realm.executeTransactionAsync(Realm.Transaction {
                val result = it.where(DataModal::class.java).equalTo("Id", courseId).findFirst()
                result?.deleteFromRealm()
            }, Realm.Transaction.OnSuccess {
                Toast.makeText(context, "Course deleted successfully", Toast.LENGTH_LONG).show()
                (context as CoursesList).getAllRecord()
            })

        }
        holder.actionLL.addView(tvBtnDelete)
    }

    override fun getItemCount(): Int {
        return list.size
    }

}