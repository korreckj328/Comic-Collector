package com.korrecksoftware.comic_collector

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Book::class), version = 1, exportSchema = false)
public abstract class ComicBookRoomDatabase: RoomDatabase() {
    abstract fun bookDao(): BookDAO

    companion object {
        @Volatile
        private var INSTANCE: ComicBookRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): ComicBookRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ComicBookRoomDatabase::class.java,
                    "book"
                )
                    .addCallback(BookDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

    private class BookDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            val books: BookDAO

            INSTANCE?.let { database ->
                scope.launch {
                    // TODO figure out if I want to do anything else here
                    // populateDatabase(database.bookDao())
                }
            }
        }

        suspend fun populateDatabase(bookDao: BookDAO) {
            // Delete all content here.
            bookDao.deleteAll()

            // Add sample book
            var book = Book(0,"Action Comics", "DC Comics",1,"1938-06-01")
            bookDao.insert(book)
        }
    }
}