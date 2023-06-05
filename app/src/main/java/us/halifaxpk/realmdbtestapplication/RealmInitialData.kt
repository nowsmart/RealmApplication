package us.halifaxpk.realmdbtestapplication

import io.realm.Realm

class RealmInitialData : Realm.Transaction {
    override fun execute(realm: Realm) {
        realm.insertOrUpdate(DataModal(1, "Horticulture", "science and art","2 Years","Reg"))
        realm.insertOrUpdate(DataModal(2, "Civil", "Building","2 Years","Normal"))
        realm.insertOrUpdate(DataModal(3, "Electrical", "Generator","3 Years","Reg"))
        realm.insertOrUpdate(DataModal(4, "IT", "Computer","4 Years","Fast"))
    }
}