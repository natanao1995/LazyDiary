package com.natanao.lazydiary.feature.listing.epoxy

import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.natanao.lazydiary.R
import com.natanao.lazydiary.general.KotlinEpoxyHolder

@EpoxyModelClass(layout = R.layout.listing_first_record_item)
abstract class FirstRecordModel : EpoxyModelWithHolder<FirstRecordModel.Holder>() {

    override fun bind(holder: Holder) {

    }

    class Holder : KotlinEpoxyHolder()
}