package com.example.funfactassignment

import kotlinx.serialization.Serializable

@Serializable
data class FunFact(var text: String, var source_url: String)