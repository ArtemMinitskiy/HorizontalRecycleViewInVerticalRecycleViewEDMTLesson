package com.example.artem.horizontalrecycleviewinverticalrecycleview.Adapter

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.artem.horizontalrecycleviewinverticalrecycleview.Model.ItemGroup
import com.example.artem.horizontalrecycleviewinverticalrecycleview.R

class MyGroupAdapter(private val context: Context, private val dataList: List<ItemGroup>?): RecyclerView.Adapter<MyGroupAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_group, viewGroup, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList?.size ?: 0
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemTitle.text = dataList!![position].headerTitle

        var items = dataList[position].listItem

        val itemListAdapter = MyItemAdapter(context, items)

        holder.recycler_view_list.setHasFixedSize(true)
        holder.recycler_view_list.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        holder.recycler_view_list.adapter = itemListAdapter
        holder.recycler_view_list.isNestedScrollingEnabled = false
        holder.btnMore.setOnClickListener { Toast.makeText(context, dataList[position].headerTitle, Toast.LENGTH_SHORT).show() }

    }


    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var itemTitle: TextView
        var recycler_view_list: RecyclerView
        var btnMore: Button


        init {
            itemTitle = view.findViewById(R.id.itemTitle) as TextView
            recycler_view_list = view.findViewById(R.id.recycler_view_list) as RecyclerView
            btnMore = view.findViewById(R.id.btnMore) as Button

        }


    }
}