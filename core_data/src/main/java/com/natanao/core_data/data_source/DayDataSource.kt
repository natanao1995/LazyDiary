package com.natanao.core_data.data_source

import com.natanao.core_domain.entity.Action.UniqueAction
import com.natanao.core_domain.entity.Day
import com.natanao.core_domain.entity.Mood
import com.natanao.core_domain.repository.DayRepository
import org.threeten.bp.LocalDate
import org.threeten.bp.ZoneId
import kotlin.random.Random
import kotlin.random.nextInt

class DayDataSource : DayRepository {

    private val days: MutableList<Day> = mutableListOf<Day>().apply {
        val emojisList = listOf(
            "\uD83D\uDCAF",
            "\uD83D\uDC95",
            "\uD83D\uDE1D",
            "\uD83C\uDF1A",
            "\uD83D\uDE0E",
            "☺",
            "\uD83D\uDE48",
            "\uD83D\uDE0D"
        )
        val currentDate = LocalDate.now(ZoneId.systemDefault())
        val random = Random

        var uniqueActionId = 0

        fun getRandomString(length: Int) : String {
            val upperCaseLetters = ('A'..'Z')
            val lowerCaseLetters = ('a'..'z')
            val stringBuilder = StringBuilder().append(upperCaseLetters.random())
            repeat(length-1) {
                stringBuilder.append(lowerCaseLetters.random())
            }

            return stringBuilder.toString()
        }

        repeat(50) { i ->
            add(Day(
                currentDate.minusDays(i.toLong()),
                Mood.values()[random.nextInt(Mood.values().size)],
                mutableListOf<UniqueAction>().apply {
                    repeat(random.nextInt(1..5)) {
                        add(UniqueAction(
                            uniqueActionId++,
                            getRandomString(random.nextInt(3..10)),
                            emojisList.random()))
                    }
                }
            ))
        }

        repeat(20) {
            removeAt(random.nextInt(0 until this.size))
        }
    }

    override suspend fun getAllDays(): List<Day> {
        return days
    }
}