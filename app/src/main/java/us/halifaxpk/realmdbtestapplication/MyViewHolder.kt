package us.halifaxpk.realmdbtestapplication

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var tvCourseId: TextView = itemView.findViewById(R.id.tvCourseId)
    var tvCourseName: TextView= itemView.findViewById(R.id.tvCourseName)
    var tvCourseDescription: TextView= itemView.findViewById(R.id.tvCourseDescription)
    var tvCourseDuration: TextView= itemView.findViewById(R.id.tvCourseDuration)
    var tvCourseTracks: TextView= itemView.findViewById(R.id.tvCourseTracks)
    var actionLL : LinearLayout = itemView.findViewById(R.id.actionLL)
}