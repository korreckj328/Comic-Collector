package com.korrecksoftware.comic_collector

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface BookDAO {
    @Query("SELECT * from Book ORDER BY title ASC")
    fun getTitles(): LiveData<List<Book>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(book: Book)

    @Query("DELETE FROM book")
    suspend fun deleteAll()
}