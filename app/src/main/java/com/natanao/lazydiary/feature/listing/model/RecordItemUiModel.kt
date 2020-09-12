package com.natanao.lazydiary.feature.listing.model

sealed class RecordItemUiModel {
    data class FilledRecordUiModel(
        val formattedDate: String,
        val moodColorRes: Int,
        val moodIconRes: Int,
        val moodNameRes: Int,
        val uniqueActions: List<UniqueActionUiModel>
    ) : RecordItemUiModel() {

        data class UniqueActionUiModel(
            val id: Int,
            val title: String,
            val textIcon: String
        )
    }

    data class EmptyRecordUiModel(
        val skippedDaysAmount: Int
    ) : RecordItemUiModel()

    object FirstRecordUiModel : RecordItemUiModel()
}