package com.example.projectacafateria20.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.projectacafateria20.model.Plat
import com.example.projectacafateria20.model.Usuari

@Database(
    entities = [Plat::class,Usuari::class],
    version = 1,
    exportSchema = true
)
abstract class CafeteriaDatabase : RoomDatabase() {
    abstract fun CafeteriaDAO() : CafeteriaDAO

    companion object {

        @Volatile
        private var INSTANCE: CafeteriaDatabase? = null

        fun getDatabase(context: Context): CafeteriaDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            if (INSTANCE == null) {
                synchronized(this) {
                    // Pass the database to the INSTANCE
                    INSTANCE = buildDatabase(context)
                }
            }
            // Return database.
            return INSTANCE!!
        }

        private fun buildDatabase(context: Context): CafeteriaDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                CafeteriaDatabase::class.java,
                "cafeteria_database"
            ).createFromAsset("databases/cafeteria.db")
                .build()

        }
    }
}