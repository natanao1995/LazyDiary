package com.natanao.lazydiary.feature.listing.epoxy

import androidx.core.content.ContextCompat
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.natanao.core_domain.entity.Mood
import com.natanao.core_domain.entity.RecordItem.FilledRecord
import com.natanao.lazydiary.R
import com.natanao.lazydiary.general.KotlinEpoxyHolder
import kotlinx.android.synthetic.main.listing_filled_record_item.view.*
import org.threeten.bp.format.DateTimeFormatter
import java.time.LocalDate
import java.util.*

@EpoxyModelClass(layout = R.layout.listing_filled_record_item)
abstract class FilledRecordModel : EpoxyModelWithHolder<FilledRecordModel.Holder>() {

    @EpoxyAttribute
    lateinit var filledRecord: FilledRecord

    override fun bind(holder: Holder) {
        with(holder) {
            val moodColorRes = when (filledRecord.day.mood) {
                Mood.AWFUL -> R.color.moodAwful
                Mood.BAD -> R.color.moodBad
                Mood.MODERATE -> R.color.moodModerate
                Mood.GOOD -> R.color.moodGood
                Mood.EXCELLENT -> R.color.moodExcellent
            }

            val moodColor = ContextCompat.getColor(context, moodColorRes)

            with(view.moodIcon) {
                setColorFilter(moodColor)
                setImageResource(
                    when (filledRecord.day.mood) {
                        Mood.AWFUL -> R.drawable.ic_mood_awful
                        Mood.BAD -> R.drawable.ic_mood_bad
                        Mood.MODERATE -> R.drawable.ic_mood_moderate
                        Mood.GOOD -> R.drawable.ic_mood_good
                        Mood.EXCELLENT -> R.drawable.ic_mood_excellent
                    }
                )
            }

            view.dateText.text = DateTimeFormatter.ofPattern("EEEE, d MMMM").format(filledRecord.day.date)

            with(view.moodText) {
                setText(
                    when (filledRecord.day.mood) {
                        Mood.AWFUL -> R.string.moodAwful
                        Mood.BAD -> R.string.moodBad
                        Mood.MODERATE -> R.string.moodModerate
                        Mood.GOOD -> R.string.moodGood
                        Mood.EXCELLENT -> R.string.moodExcellent
                    }
                )
                setTextColor(moodColor)
            }

            val layoutManager = FlexboxLayoutManager(context).apply {
                flexDirection = FlexDirection.ROW
                justifyContent = JustifyContent.FLEX_START
            }

            view.actionsList.itemAnimator = null
            view.actionsList.layoutManager = layoutManager
            view.actionsList.withModels {
                filledRecord.day.actions.forEachIndexed { i, action ->
                    action {
                        id(i)
                        action(action)
                    }
                }
            }
        }
    }

    class Holder : KotlinEpoxyHolder()
}

