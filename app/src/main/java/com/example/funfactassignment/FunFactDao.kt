package com.example.funfactassignment

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FunFactDao {
    @Insert
    suspend fun insertFact(fact: FunFact)

    // get all facts from the database
    @Query("SELECT * FROM fun_facts")
    fun getAllFacts(): Flow<List<FunFact>>
}
