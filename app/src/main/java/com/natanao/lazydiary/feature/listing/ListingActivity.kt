package com.natanao.lazydiary.feature.listing

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.natanao.lazydiary.R
import com.natanao.lazydiary.feature.listing.epoxy.emptyRecord
import com.natanao.lazydiary.feature.listing.epoxy.filledRecord
import com.natanao.lazydiary.feature.listing.epoxy.firstRecord
import com.natanao.lazydiary.feature.listing.model.RecordItemUiModel
import com.natanao.lazydiary.feature.listing.model.RecordItemUiModel.EmptyRecordUiModel
import com.natanao.lazydiary.feature.listing.model.RecordItemUiModel.FilledRecordUiModel
import kotlinx.android.synthetic.main.listing_activity.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListingActivity : AppCompatActivity(R.layout.listing_activity) {

    private val viewModel by viewModel<ListingViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.recordsLiveData.observe(this) { records ->
            recycler.withModels {
                records.forEachIndexed { i, record ->
                    when (record) {
                        is FilledRecordUiModel -> filledRecord {
                            id(i)
                            filledRecord(record)
                        }
                        is EmptyRecordUiModel -> emptyRecord {
                            id(i)
                            emptyRecord(record)
                        }
                        RecordItemUiModel.FirstRecordUiModel -> firstRecord {
                            id(i)
                        }
                    }
                }
            }
        }
    }
}