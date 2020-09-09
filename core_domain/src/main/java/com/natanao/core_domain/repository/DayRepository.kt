package com.natanao.core_domain.repository

import com.natanao.core_domain.entity.Day

interface DayRepository {

    suspend fun getAllDays(): List<Day>
}