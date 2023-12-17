package com.testvass.rickandmorty.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.testvass.repository.models.CharacterModel
import com.testvass.repository.utils.OnResult
import com.testvass.usecase.characters.FetchCharacterByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val fetchCharacterByIdUseCase: FetchCharacterByIdUseCase
): ViewModel() {

    private val _characterLiveData: MutableLiveData<CharacterModel> = MutableLiveData()
    fun characterLiveData(): LiveData<CharacterModel> = _characterLiveData

    private val _errorLiveData: MutableLiveData<String> = MutableLiveData()
    fun errorLiveData(): LiveData<String> = _errorLiveData

    fun fetchCharacterById(characterId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                when(val result = fetchCharacterByIdUseCase.invoke(characterId)) {
                    is OnResult.Success -> {
                        _characterLiveData.postValue(result.data)
                    }
                    is OnResult.Error -> {
                        _errorLiveData.postValue(result.exception.message)
                    }
                }
            }
        }
    }
}