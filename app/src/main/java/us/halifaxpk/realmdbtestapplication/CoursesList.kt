package us.halifaxpk.realmdbtestapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmResults
import io.realm.kotlin.where
import us.halifaxpk.realmdbtestapplication.databinding.ActivityCoursesListBinding


class CoursesList : AppCompatActivity() {
    private lateinit var binding: ActivityCoursesListBinding
    private lateinit var realm: Realm
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoursesListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnAddNew.setOnClickListener {
            startActivity(Intent(this, CreateActivity::class.java))
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        Realm.init(this)
        val config = RealmConfiguration.Builder()
            .name("myrealm.realm")
            .initialData( RealmInitialData())
            .build()
        Realm.setDefaultConfiguration(config)
        realm = Realm.getDefaultInstance()
        getAllRecord()
    }

    fun getAllRecord() {
        val result = realm.where<DataModal>().findAll()
        val list = result.toCollection(ArrayList<DataModal>())
        val myAdapter = RecordAdapter(this, list)
        binding.recyclerView.adapter = myAdapter
    }

    override fun onResume() {
        super.onResume()
        getAllRecord()
    }
}