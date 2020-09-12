package com.natanao.lazydiary.feature.listing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.natanao.core_domain.interactor.ListingInteractor
import com.natanao.lazydiary.feature.listing.model.RecordItemUiModel
import com.natanao.lazydiary.feature.listing.model.toUiModel
import kotlinx.coroutines.launch
import org.threeten.bp.format.DateTimeFormatter

class ListingViewModel(
    private val listingInteractor: ListingInteractor
) : ViewModel() {

    private val _recordsLiveData = MutableLiveData<List<RecordItemUiModel>>()

    val recordsLiveData: LiveData<List<RecordItemUiModel>>
        get() = _recordsLiveData

    init {
        updateRecordsList()
    }

    private fun updateRecordsList() {
        viewModelScope.launch {
            val dateTimeFormatter = DateTimeFormatter.ofPattern("EEEE, d MMMM")
            _recordsLiveData.postValue(
                listingInteractor.getRecords().map { it.toUiModel(dateTimeFormatter) }
            )
        }
    }
}