package com.example.audioplayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.audioplayer.databinding.RadioItemBinding

class RadioStationsAdapter : RecyclerView.Adapter<RadioStationsAdapter.RadioHolder>() {
    private val radioList = mutableListOf<Radio>()
    private var listener: OnRadioClickListener? = null

    fun addRadio(radio: Radio) {
        radioList.add(radio)
        notifyDataSetChanged()
    }

    fun setOnRadioClickListener(listener: OnRadioClickListener) {
        this.listener = listener
    }

    interface OnRadioClickListener {
        fun onRadioClick(position: Int)
    }

    class RadioHolder(item: View) : RecyclerView.ViewHolder(item) {
        val binding = RadioItemBinding.bind(item)

        fun bind(radio: Radio) = with(binding) {
            imageRadiostation.setImageResource(radio.imageId)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RadioHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.radio_item, parent, false)
        return RadioHolder(view)
    }

    override fun onBindViewHolder(holder: RadioHolder, position: Int) {
        val radio = radioList[position]
        holder.bind(radio)

        holder.itemView.setOnClickListener {
            listener?.onRadioClick(position)
        }
    }

    override fun getItemCount(): Int {
        return radioList.size
    }


}