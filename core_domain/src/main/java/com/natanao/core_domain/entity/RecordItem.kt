package com.natanao.core_domain.entity

sealed class RecordItem {
    data class FilledRecord(val day: Day) : RecordItem()
    data class EmptyRecord(val skippedDaysAmount: Int) : RecordItem()
    object FirstRecord : RecordItem()
}