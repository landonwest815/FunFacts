package com.example.funfactassignment

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

class FactsRepository(val scope: CoroutineScope, private val dao: FunFactDao) {
    // setup the http client
    // this code is taken from the CodeExamples repo
    private val client = HttpClient(Android) {
        install(ContentNegotiation) {
            json(Json {
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    // Fetch all facts stored in Room as a Flow
    val allFacts: Flow<List<FunFact>> = dao.getAllFacts()

    // Fetches a new fun fact from the API and saves it to Room
    fun fetchAndSaveFact() {
        scope.launch {
            try {
                Log.d("FactsRepository", "Fetching new fun fact...")
                val fact = client.get("https://uselessfacts.jsph.pl/random.json?language=en").body<FunFact>()

                withContext(Dispatchers.IO) {
                    dao.insertFact(fact)
                }
                Log.d("FactsRepository", "Fact saved: ${fact.text}")
            } catch (e: Exception) {
                Log.e("FactsRepository", "Error fetching fact", e)
            }
        }
    }
}