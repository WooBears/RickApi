package com.example.rickapi.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rickapi.databinding.ItemCartoonBinding
import com.example.rickapi.model.Character
import com.squareup.picasso.Picasso
import com.example.rickapi.model.Result

class CartoonAdapter : ListAdapter<Result, CartoonViewHolder>(
    CharacterItemCallBack()
) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CartoonViewHolder {
        return CartoonViewHolder(
            ItemCartoonBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CartoonViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}
class CartoonViewHolder(private val binding: ItemCartoonBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(context: Result){

            binding.ciAliveStatus.visibility = View.GONE
            binding.ciDeadStatus.visibility = View.GONE
            binding.ciUnknownStatus.visibility = View.GONE

            when (context.status){
                "Alive" -> binding.ciAliveStatus.visibility = View.VISIBLE
                "Dead" -> binding.ciDeadStatus.visibility = View.VISIBLE
                "unknown" -> binding.ciUnknownStatus.visibility = View.VISIBLE
            }

            binding.tvName.text = context.name
            binding.tvStatus.text = context.status
            binding.tvSpecies.text = context.species
            binding.tvLocation.text = context.location.name
            binding.tvLocationSecond.text = context.origin.name
            Picasso.get().load(context.image).into(binding.ivImage)
        }
}

class CharacterItemCallBack : DiffUtil.ItemCallback<Result>(){
    override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem == newItem
    }

}
