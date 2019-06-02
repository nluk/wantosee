package pl.nluk.wantosee.database

import com.raizlabs.android.dbflow.annotation.Database


@Database(name = AppDatabase.DB_NAME,version = AppDatabase.VERSION)
class AppDatabase {
    companion object{
        const val DB_NAME = "WANTOSEE"
        const val VERSION = 1
    }
}