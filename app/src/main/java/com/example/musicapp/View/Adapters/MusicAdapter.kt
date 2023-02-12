package com.example.musicapp.View.Adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.musicapp.R
import com.example.musicapp.databinding.MusicItemBinding
import com.example.musicapp.model.Results

class MusicAdapter(
    private val itemSet: MutableList<com.example.musicapp.model.Results> = mutableListOf(),
    private val onItemClick: (itemId: String) -> Unit
) :  RecyclerView.Adapter<MusicAdapter.MusicViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(newItems: List<com.example.musicapp.model.Results>) {
        if (itemSet != newItems) {
            itemSet.clear()
            itemSet.addAll(newItems)

            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicViewHolder =
        MusicViewHolder(
            MusicItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) =
        holder.bind(itemSet[position], onItemClick)

    override fun getItemCount(): Int = itemSet.size


class MusicViewHolder(
    private val binding: MusicItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Results, onItemClick: (String) -> Unit) {


        binding.itemName.text = item.trackName?: "No Track Provided"
        binding.artistName.text = item.artistName?: "No Name Provided"
        binding.collectionName.text = item.collectionName?: "No Collection Provided"
        binding.trackPrice.text =  item.trackPrice.toString()

        Glide
            .with(binding.root)
            .load(item.artworkUrl60)
            .centerCrop()
            .placeholder(R.drawable.ic_dashboard_black_24dp)
            .error(R.drawable.ic_notifications_black_24dp)
            .into(binding.imgId)

        itemView.setOnClickListener {
            item.previewUrl?.let(onItemClick)
        }
    }
}
}
