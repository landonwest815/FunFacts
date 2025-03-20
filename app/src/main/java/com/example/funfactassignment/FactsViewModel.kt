package com.example.funfactassignment

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class FactsViewModel(private val repository: FactsRepository) : ViewModel() {
    // list of facts
    private val _facts = mutableStateListOf<FunFact>()
    val facts: List<FunFact> = _facts

    // fetch a fact from the repository
    fun fetchFact() {
        viewModelScope.launch {
            try {
                // fetch a fact from the repository
                val fact = repository.getFunFact()
                _facts.add(fact)
            } catch (e: Exception) {
                Log.e("FactsViewModel", "Error fetching fact", e)
            }
        }
    }

    // factory class to create the view model
    class Factory(private val repository: FactsRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(FactsViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return FactsViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}