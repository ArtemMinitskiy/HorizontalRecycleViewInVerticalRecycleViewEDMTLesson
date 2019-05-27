package com.example.artem.horizontalrecycleviewinverticalrecycleview.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.artem.horizontalrecycleviewinverticalrecycleview.Interface.IItemClickListener
import com.example.artem.horizontalrecycleviewinverticalrecycleview.Model.ItemData
import com.example.artem.horizontalrecycleviewinverticalrecycleview.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_item.view.*

class MyItemAdapter(private val context: Context, private val itemList: List<ItemData>?): RecyclerView.Adapter<MyItemAdapter.MyViewHolder>()  {
    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_item, viewGroup, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList?.size ?: 0
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.txt_title.setText(itemList!![position].name!!)
        Picasso.get().load(itemList[position].image).into(holder.txt_image)

        holder.setClick(object : IItemClickListener {
            override fun onItemClickListener(view: View, position: Int) {
                Toast.makeText(context, "" + itemList[position].name, Toast.LENGTH_SHORT).show()
            }

        })
    }

    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {

        var txt_title: TextView
        var txt_image: ImageView

        lateinit var iItemClickListener: IItemClickListener

        fun setClick(iItemClickListener: IItemClickListener){
            this.iItemClickListener = iItemClickListener
        }

        init {
            txt_title = view.findViewById(R.id.tvTitle) as TextView
            txt_image = view.findViewById(R.id.itemImage) as ImageView

            view.setOnClickListener(this)

        }

        override fun onClick(view: View?) {
            iItemClickListener.onItemClickListener(view!!, adapterPosition)

        }

    }
}