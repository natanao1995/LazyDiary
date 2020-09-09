package com.natanao.lazydiary.di

import com.natanao.core_data.data_source.DayDataSource
import com.natanao.core_domain.interactor.ListingInteractor
import com.natanao.core_domain.repository.DayRepository
import com.natanao.lazydiary.feature.listing.ListingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val simpleModule = module {
    single<DayRepository> { DayDataSource() }
    factory { ListingInteractor(get()) }
    viewModel { ListingViewModel(get()) }
}