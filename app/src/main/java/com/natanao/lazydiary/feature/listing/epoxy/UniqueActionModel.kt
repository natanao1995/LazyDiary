package com.natanao.lazydiary.feature.listing.epoxy

import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.natanao.lazydiary.R
import com.natanao.lazydiary.feature.listing.model.RecordItemUiModel.FilledRecordUiModel.UniqueActionUiModel
import com.natanao.lazydiary.general.KotlinEpoxyHolder
import kotlinx.android.synthetic.main.listing_action_item.view.*

@EpoxyModelClass(layout = R.layout.listing_action_item)
abstract class UniqueActionModel : EpoxyModelWithHolder<UniqueActionModel.Holder>() {

    @EpoxyAttribute
    lateinit var uniqueAction: UniqueActionUiModel

    override fun bind(holder: Holder) {
        holder.view.iconText.text = uniqueAction.textIcon
        holder.view.actionText.text = uniqueAction.title
    }

    class Holder : KotlinEpoxyHolder()
}