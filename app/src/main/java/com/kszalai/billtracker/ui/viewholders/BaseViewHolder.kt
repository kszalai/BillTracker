package com.kszalai.billtracker.ui.viewholders

import android.view.View
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<in T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: T)
}