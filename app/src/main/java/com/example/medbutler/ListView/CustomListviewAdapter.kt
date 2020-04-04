package com.example.medbutler.ListView

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.medbutler.R
import android.animation.ValueAnimator

class CustomListviewAdapter (var mCtx: Context, var resources:Int, var items:List<ModelTreatment>):ArrayAdapter<ModelTreatment>(mCtx, resources, items) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater:LayoutInflater = LayoutInflater.from(mCtx)
        val view:View = layoutInflater.inflate(resources, null)

        val imageView:ImageView = view.findViewById(R.id.imageView)
        val imageView2:ImageView = view.findViewById(R.id.imageView2)
        val titleTextView:TextView = view.findViewById(R.id.textName)
        val descriptionTextView:TextView = view.findViewById(R.id.textDescription)

        var mItem: ModelTreatment = items[position]
        imageView.setImageDrawable(mCtx.resources.getDrawable(mItem.img))
        imageView2.setImageDrawable(mCtx.resources.getDrawable(mItem.img2))
        titleTextView.text = mItem.title
        descriptionTextView.text = mItem.description
        animacio(imageView,imageView2)
        return view
    }

    fun animacio(imageFluix:ImageView,imageFort:ImageView){

        val animator = ValueAnimator.ofFloat(0f, 1f)
        animator.addUpdateListener(object: ValueAnimator.AnimatorUpdateListener{
            override fun onAnimationUpdate(animation: ValueAnimator?) {
                val animatedValue =animation?.animatedValue as Float
                imageFort.setAlpha(animation.animatedValue as Float)
            }
        })

        animator.duration = 1500
        animator.repeatMode = ValueAnimator.REVERSE
        animator.repeatCount = -1
        animator.start()
    }
}