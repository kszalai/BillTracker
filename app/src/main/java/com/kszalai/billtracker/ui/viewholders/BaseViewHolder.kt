package com.kszalai.billtracker.ui.viewholders

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseViewHolder<in T>(open val viewBinding: ViewBinding) : RecyclerView.ViewHolder(viewBinding.root) {
    abstract fun bind(item: T)
}