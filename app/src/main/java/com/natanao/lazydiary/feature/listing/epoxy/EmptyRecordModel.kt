package com.natanao.lazydiary.feature.listing.epoxy

import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.natanao.core_domain.entity.RecordItem.EmptyRecord
import com.natanao.lazydiary.R
import com.natanao.lazydiary.general.KotlinEpoxyHolder
import kotlinx.android.synthetic.main.listing_empty_record_item.view.*

@EpoxyModelClass(layout = R.layout.listing_empty_record_item)
abstract class EmptyRecordModel : EpoxyModelWithHolder<EmptyRecordModel.Holder>() {

    @EpoxyAttribute
    lateinit var emptyRecord: EmptyRecord

    override fun bind(holder: Holder) {
        holder.view.skippedDaysText.text =
            holder.context.getString(R.string.daysSkipped, emptyRecord.skippedDaysAmount)
    }

    class Holder : KotlinEpoxyHolder()
}

