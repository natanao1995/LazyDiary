package com.natanao.core_domain.interactor

import com.natanao.core_domain.entity.RecordItem
import com.natanao.core_domain.entity.RecordItem.*
import com.natanao.core_domain.repository.DayRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.threeten.bp.LocalDate
import org.threeten.bp.ZoneId
import org.threeten.bp.temporal.ChronoUnit

class ListingInteractor(
    private val dayRepository: DayRepository
) {
    suspend fun getRecords(): List<RecordItem> = withContext(Dispatchers.Default) {
        val days = dayRepository.getAllDays().sortedByDescending { it.date }

        val records = mutableListOf<RecordItem>()

        days.forEachIndexed { i, day ->
            val laterDate = days.getOrNull(i - 1)?.date ?: LocalDate.now(ZoneId.systemDefault())
            val difference = ChronoUnit.DAYS.between(day.date, laterDate).toInt()
            if (difference > 1) {
                records.add(EmptyRecord(difference - 1))
            }

            records.add(FilledRecord(day))
        }

        records.add(FirstRecord)

        return@withContext records
    }
}