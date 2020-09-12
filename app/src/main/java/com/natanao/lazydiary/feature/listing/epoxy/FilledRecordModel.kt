package com.natanao.lazydiary.feature.listing.epoxy

import androidx.core.content.ContextCompat
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.natanao.lazydiary.R
import com.natanao.lazydiary.feature.listing.model.RecordItemUiModel.FilledRecordUiModel
import com.natanao.lazydiary.general.KotlinEpoxyHolder
import kotlinx.android.synthetic.main.listing_filled_record_item.view.*

@EpoxyModelClass(layout = R.layout.listing_filled_record_item)
abstract class FilledRecordModel : EpoxyModelWithHolder<FilledRecordModel.Holder>() {

    @EpoxyAttribute
    lateinit var filledRecord: FilledRecordUiModel

    override fun bind(holder: Holder) {
        with(holder.view) {
            val moodColor = ContextCompat.getColor(context, filledRecord.moodColorRes)

            moodIcon.setColorFilter(moodColor)
            moodIcon.setImageResource(filledRecord.moodIconRes)

            dateText.text = filledRecord.formattedDate

            moodText.setText(filledRecord.moodNameRes)
            moodText.setTextColor(moodColor)

            val layoutManager = FlexboxLayoutManager(context).apply {
                flexDirection = FlexDirection.ROW
                justifyContent = JustifyContent.FLEX_START
            }

            actionsList.itemAnimator = null
            actionsList.layoutManager = layoutManager
            actionsList.withModels {
                filledRecord.uniqueActions.forEachIndexed { i, action ->
                    uniqueAction {
                        id(i)
                        uniqueAction(action)
                    }
                }
            }
        }
    }

    class Holder : KotlinEpoxyHolder()
}