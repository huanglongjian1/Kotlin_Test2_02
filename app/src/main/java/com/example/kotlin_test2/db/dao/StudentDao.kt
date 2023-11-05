package com.example.kotlin_test2.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kotlin_test2.db.bean.Student

@Dao
interface StudentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStudent(student: Student)

    @Query("select * from Student")
    fun getStudentAll(): List<Student>

    @Query("select * from Student")
    fun getStudentsPagingSource(): PagingSource<Int, Student>

    @Query("delete from Student")
    fun deleteStudentsAll()
}