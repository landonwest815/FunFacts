package com.example.funfactassignment

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class FactsRepository {
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

    // gets a random fact from the API asychronously
    suspend fun getFunFact(): FunFact {
        return client.get("https://uselessfacts.jsph.pl/random.json?language=en").body()
    }
}