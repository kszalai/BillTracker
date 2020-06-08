package com.kszalai.billtracker.ui.bills

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kszalai.billtracker.R
import com.kszalai.billtracker.models.BillObject
import com.kszalai.billtracker.ui.viewholders.BaseViewHolder
import com.kszalai.billtracker.ui.viewholders.BillViewHolder

class BillListRecyclerViewAdapter(private var data: ArrayList<Any> = arrayListOf<Any>()) : RecyclerView.Adapter<BaseViewHolder<BillObject>>() {

    companion object {
        private val overViewItem = 0
        private val billItem = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BillViewHolder {
        val context = parent.context

        val view = LayoutInflater.from(context).inflate(R.layout.bill_item_layout, parent, false)
        return BillViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<BillObject>, position: Int) {
        val element = data[position]
        holder.bind(element as BillObject)
    }

    fun setData(bills: ArrayList<Any>) {
        data.clear()
        notifyDataSetChanged()
        data.addAll(bills)
        notifyDataSetChanged()
    }

}