package com.example.medbutler.ListView

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.medbutler.R

class CustomAdapterOther (var mCtx: Context, var resources:Int, var items:List<ModelOther>):
    ArrayAdapter<ModelOther>(mCtx, resources, items) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
        val view: View = layoutInflater.inflate(resources, null)

        val lay:RelativeLayout = view.findViewById(R.id.relatLayout)
        val imageView: ImageView = view.findViewById(R.id.imageView_option2)
        val titleTextView: TextView = view.findViewById(R.id.textName_option2)

        var mItem: ModelOther = items[position]
        imageView.setImageDrawable(mCtx.resources.getDrawable(mItem.img))
        titleTextView.text = mItem.title
        return view
    }
}