package com.korrecksoftware.comic_collector

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Book::class), version = 1, exportSchema = false)
public abstract class ComicBookRoomDatabase: RoomDatabase() {
    abstract fun bookDao(): BookDAO

    companion object {
        @Volatile
        private var INSTANCE: ComicBookRoomDatabase? = null

        fun getDatabase(context: Context): ComicBookRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ComicBookRoomDatabase::class.java,
                    "book"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}