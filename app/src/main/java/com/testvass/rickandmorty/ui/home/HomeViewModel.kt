package com.testvass.rickandmorty.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.testvass.repository.models.CharacterModel
import com.testvass.repository.utils.OnResult
import com.testvass.usecase.characters.FetchCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchCharactersUseCase: FetchCharactersUseCase
): ViewModel() {

    private val _charactersLiveData: MutableLiveData<List<CharacterModel>> = MutableLiveData()
    fun charactersLiveData(): LiveData<List<CharacterModel>> = _charactersLiveData

    private val _errorLiveData: MutableLiveData<String> = MutableLiveData()
    fun errorLiveData(): LiveData<String> = _errorLiveData

    fun fetchCharacters() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                when(val result = fetchCharactersUseCase.invoke()) {
                    is OnResult.Success -> {
                        _charactersLiveData.postValue(result.data)
                    }
                    is OnResult.Error -> {
                        _errorLiveData.postValue(result.exception.message)
                    }
                }
            }
        }
    }
}