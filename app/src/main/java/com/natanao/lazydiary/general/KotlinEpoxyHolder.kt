package com.natanao.lazydiary.general

import android.content.Context
import android.view.View
import com.airbnb.epoxy.EpoxyHolder

open class KotlinEpoxyHolder : EpoxyHolder() {
    lateinit var view: View

    val context: Context
        get() = view.context

    override fun bindView(view: View) {
        this.view = view
    }
}