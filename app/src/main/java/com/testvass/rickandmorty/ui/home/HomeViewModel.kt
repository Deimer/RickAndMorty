package com.testvass.rickandmorty.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.testvass.repository.models.CharacterModel
import com.testvass.repository.utils.OnResult
import com.testvass.usecase.characters.FetchCharacterByIdUseCase
import com.testvass.usecase.characters.FetchCharacterByNameUseCase
import com.testvass.usecase.characters.FetchCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchCharactersUseCase: FetchCharactersUseCase,
    private val fetchCharacterByNameUseCase: FetchCharacterByNameUseCase
): ViewModel() {

    private val _charactersLiveData: MutableLiveData<List<CharacterModel>> = MutableLiveData()
    fun charactersLiveData(): LiveData<List<CharacterModel>> = _charactersLiveData

    private val _errorLiveData: MutableLiveData<String> = MutableLiveData()
    fun errorLiveData(): LiveData<String> = _errorLiveData

    private val _loadingLiveData: MutableLiveData<Boolean> = MutableLiveData()
    fun loadingLiveData(): LiveData<Boolean> = _loadingLiveData

    fun fetchCharacters() {
        _loadingLiveData.postValue(true)
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                when(val result = fetchCharactersUseCase.invoke()) {
                    is OnResult.Success -> {
                        _charactersLiveData.postValue(result.data)
                        _loadingLiveData.postValue(false)
                    }
                    is OnResult.Error -> {
                        _errorLiveData.postValue(result.exception.message)
                        _loadingLiveData.postValue(false)
                    }
                }
            }
        }
    }

    fun fetchCharactersByName(name: String) {
        _loadingLiveData.postValue(true)
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                when(val result = fetchCharacterByNameUseCase.invoke(name)) {
                    is OnResult.Success -> {
                        _charactersLiveData.postValue(result.data)
                        _loadingLiveData.postValue(false)
                    }
                    is OnResult.Error -> {
                        _errorLiveData.postValue(result.exception.message)
                        _loadingLiveData.postValue(false)
                    }
                }
            }
        }
    }
}