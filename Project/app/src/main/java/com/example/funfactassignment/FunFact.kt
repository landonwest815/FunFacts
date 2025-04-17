package com.example.funfactassignment

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

// data class for a fun fact
@Serializable
@Entity(tableName="fun_facts")
data class FunFact(
    var text: String,
    var source_url: String
){
    @PrimaryKey(autoGenerate = true)
    @kotlinx.serialization.Transient // weird fix from ChatGPT
    var id: Int = 0
}