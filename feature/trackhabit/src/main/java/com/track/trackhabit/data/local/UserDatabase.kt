package com.track.trackhabit.data.local

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.track.trackhabit.data.local.dao.HabitDao
import com.track.trackhabit.data.local.dao.InspectionDao
import com.track.trackhabit.data.local.dao.UserDao
import com.track.trackhabit.domain.entity.Habit
import com.track.trackhabit.domain.entity.Inspection
import com.track.trackhabit.domain.entity.User

@Database(
    entities = [User::class, Habit::class, Inspection::class],
    version = 1,
    exportSchema = false
)
abstract class UserDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun habitDao(): HabitDao
    abstract fun inspectionDao(): InspectionDao

    companion object{
        fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, UserDatabase::class.java, "track_habit_db")
                .build()
    }
}