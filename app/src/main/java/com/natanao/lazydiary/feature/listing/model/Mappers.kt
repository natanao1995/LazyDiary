package com.natanao.lazydiary.feature.listing.model

import com.natanao.core_domain.entity.Action
import com.natanao.core_domain.entity.Mood
import com.natanao.core_domain.entity.RecordItem
import com.natanao.lazydiary.R
import org.threeten.bp.format.DateTimeFormatter

fun RecordItem.toUiModel(
    dateTimeFormatter: DateTimeFormatter
) = when (this) {
    is RecordItem.FilledRecord -> this.toUiModel(dateTimeFormatter)
    is RecordItem.EmptyRecord -> this.toUiModel()
    RecordItem.FirstRecord -> RecordItemUiModel.FirstRecordUiModel
}

private fun RecordItem.FilledRecord.toUiModel(
    dateTimeFormatter: DateTimeFormatter
) = RecordItemUiModel.FilledRecordUiModel(
    dateTimeFormatter.format(this.day.date),
    when (this.day.mood) {
        Mood.AWFUL -> R.color.moodAwful
        Mood.BAD -> R.color.moodBad
        Mood.MODERATE -> R.color.moodModerate
        Mood.GOOD -> R.color.moodGood
        Mood.EXCELLENT -> R.color.moodExcellent
    },
    when (this.day.mood) {
        Mood.AWFUL -> R.drawable.ic_mood_awful
        Mood.BAD -> R.drawable.ic_mood_bad
        Mood.MODERATE -> R.drawable.ic_mood_moderate
        Mood.GOOD -> R.drawable.ic_mood_good
        Mood.EXCELLENT -> R.drawable.ic_mood_excellent
    },
    when (this.day.mood) {
        Mood.AWFUL -> R.string.moodAwful
        Mood.BAD -> R.string.moodBad
        Mood.MODERATE -> R.string.moodModerate
        Mood.GOOD -> R.string.moodGood
        Mood.EXCELLENT -> R.string.moodExcellent
    },
    this.day.actions
        .filterIsInstance<Action.UniqueAction>()
        .map { it.toUiModel() }
)

private fun RecordItem.EmptyRecord.toUiModel() =
    RecordItemUiModel.EmptyRecordUiModel(skippedDaysAmount)

private fun Action.UniqueAction.toUiModel() =
    RecordItemUiModel.FilledRecordUiModel.UniqueActionUiModel(
        this.id,
        this.title,
        this.textIcon
    )