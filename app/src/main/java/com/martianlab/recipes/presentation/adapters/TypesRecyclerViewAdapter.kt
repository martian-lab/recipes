package com.martianlab.recipes.presentation.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.martianlab.recipes.R



import kotlinx.android.synthetic.main.point_item.view.*


class TypesRecyclerViewAdapter(
    private var mValues: List<String>
) : RecyclerView.Adapter<TypesRecyclerViewAdapter.ViewHolder>() {

    public fun setValues(values:List<String>){
        mValues = values
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.point_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mIdView.text = (position+1).toString()

        with(holder.mView) {
            tag = item
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mIdView: TextView = mView.item_number
        val mContentView: TextView = mView.text
        val mTimeView: TextView = mView.time
        val mImageView: ImageView = mView.imageView


    }
}
