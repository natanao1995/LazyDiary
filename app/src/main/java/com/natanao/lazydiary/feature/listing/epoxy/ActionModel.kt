package com.natanao.lazydiary.feature.listing.epoxy

import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.natanao.core_domain.entity.Action
import com.natanao.lazydiary.R
import com.natanao.lazydiary.general.KotlinEpoxyHolder
import kotlinx.android.synthetic.main.listing_action_item.view.*

@EpoxyModelClass(layout = R.layout.listing_action_item)
abstract class ActionModel : EpoxyModelWithHolder<ActionModel.Holder>() {

    @EpoxyAttribute
    lateinit var action: Action

    override fun bind(holder: Holder) {
        holder.view.iconText.text = action.textIcon
        holder.view.actionText.text = action.title
    }

    class Holder : KotlinEpoxyHolder()
}

