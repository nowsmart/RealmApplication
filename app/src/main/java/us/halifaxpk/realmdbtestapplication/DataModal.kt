package us.halifaxpk.realmdbtestapplication

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import java.io.Serializable

@RealmClass
open class DataModal(
    @PrimaryKey
    var Id: Long = 0,
    var courseName: String = "",
    var courseDescription: String = "",
    var courseDuration: String = "",
    var courseTracks: String = "",
) : RealmObject()