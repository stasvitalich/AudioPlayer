package com.example.audioplayer

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.audioplayer.databinding.CarouselItemBinding


class RadioAdapter(private var radioList: List<RadioModel>): RecyclerView.Adapter<RadioAdapter.RadioViewHolder>() {
    class RadioViewHolder(val binding: CarouselItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RadioViewHolder {
        val binding = CarouselItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RadioViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RadioViewHolder, position: Int) {
        val radio = radioList[position]

        holder.binding.apply {
            Glide.with(radioImage).load(radio.image).into(radioImage)
        }

        holder.itemView.setOnClickListener {
            onItemSelectedListener?.invoke(holder.adapterPosition)
            val clickAnim = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.click_anim)
            holder.itemView.startAnimation(clickAnim)
        }
    }

    override fun getItemCount(): Int = radioList.size

    fun updateData(list: ArrayList<RadioModel>) {
        this.radioList = list
        notifyDataSetChanged()
    }

    private var onItemSelectedListener: ((position: Int) -> Unit)? = null

    fun setOnItemSelectedListener(listener: (position: Int) -> Unit) {
        onItemSelectedListener = listener
    }

}