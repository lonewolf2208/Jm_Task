package com.example.jumpingmindstask.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.jumpingmindstask.databinding.HomeCardBinding
import com.example.jumpingmindstask.model.DogsDataItem


class DogsAdapter : ListAdapter<DogsDataItem, DogsAdapter.ViewHolder>(DogsComparator()) {

    var clickListener: ClickListener? = null

    fun onClickListener(clickListener: ClickListener) {
        this.clickListener = clickListener
    }

    interface ClickListener {
        fun OnClick(position: Int)
    }

    inner class ViewHolder(private val binding: HomeCardBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                clickListener?.OnClick(adapterPosition)
            }
        }
        fun bind(data : DogsDataItem)
        {
            binding.apply {
                this.dogImage.load(data.img)
                this.breedName.text= data.breed.toString()
                this.origin.text = data.origin.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = HomeCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }

    }
    class DogsComparator : DiffUtil.ItemCallback<DogsDataItem>() {
        override fun areItemsTheSame(oldItem: DogsDataItem, newItem: DogsDataItem) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: DogsDataItem, newItem: DogsDataItem) =
            oldItem == newItem
    }
}

