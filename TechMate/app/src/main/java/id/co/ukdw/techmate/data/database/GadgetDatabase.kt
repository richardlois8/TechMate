package id.co.ukdw.techmate.data.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

@androidx.room.Database(entities = [GadgetCase::class], version = 2)
abstract class GadgetDatabase : RoomDatabase() {
    abstract fun gadgetDao(): GadgetDAO

    companion object{
        @Volatile
        private var INSTANCE: GadgetDatabase? = null
        @JvmStatic
        fun getDatabase(context: Context): GadgetDatabase {
            if (INSTANCE == null) {
                synchronized(GadgetDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            GadgetDatabase::class.java, "techmate.db"
                        )
                            .fallbackToDestructiveMigration()
                            .build()
                    }
                }
            }
            return INSTANCE as GadgetDatabase
        }
    }
}