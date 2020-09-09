package com.natanao.core_domain.entity

import org.threeten.bp.LocalDate

data class Day(
    val date: LocalDate,
    val mood: Mood,
    val actions: List<Action>
)