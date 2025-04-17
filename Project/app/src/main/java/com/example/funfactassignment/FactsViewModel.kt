package com.example.funfactassignment

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory

class FactsViewModel(private val repository: FactsRepository) : ViewModel() {
    // expose the facts as a StateFlow
    val facts: StateFlow<List<FunFact>> = repository.allFacts.stateIn(
        scope = repository.scope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = listOf()
    )

    // fetch a fact from the repository
    fun fetchFact() {
        Log.d("FactsViewModel", "Fetching new fact")
        repository.fetchAndSaveFact()
    }
}

// factory for our view model
object FactsViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            FactsViewModel(
                (this[AndroidViewModelFactory.APPLICATION_KEY] as FunFactApplication).funFactRepository
            )
        }
    }
}