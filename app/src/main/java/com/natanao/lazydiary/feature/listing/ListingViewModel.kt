package com.natanao.lazydiary.feature.listing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.natanao.core_domain.entity.RecordItem
import com.natanao.core_domain.interactor.ListingInteractor
import kotlinx.coroutines.launch

class ListingViewModel(
    private val listingInteractor: ListingInteractor
) : ViewModel() {

    private val _recordsLiveData = MutableLiveData<List<RecordItem>>()

    val recordsLiveData: LiveData<List<RecordItem>>
        get() = _recordsLiveData

    init {
        updateRecordsList()
    }

    private fun updateRecordsList() {
        viewModelScope.launch {
            _recordsLiveData.postValue(listingInteractor.getRecords())
        }
    }
}