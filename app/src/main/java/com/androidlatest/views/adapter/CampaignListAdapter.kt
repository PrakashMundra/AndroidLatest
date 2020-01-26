package com.androidlatest.views.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.androidlatest.data.entity.Campaign
import com.androidlatest.databinding.ListItemBinding
import com.androidlatest.views.fragment.MainListFragmentDirections

class CampaignListAdapter :
    ListAdapter<Campaign, CampaignListAdapter.ViewHolder>(CampaignDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val campaign = getItem(position)
        holder.bind(campaign)
    }

    class ViewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                binding.campaign?.let { campaign ->
                    navigateToDetails(campaign, it)
                }
            }
        }

        private fun navigateToDetails(campaign: Campaign, view: View) {
            val direction = MainListFragmentDirections.mainListToDetailFragment(campaign.name!!)
            view.findNavController().navigate(direction)
        }

        fun bind(item: Campaign) {
            binding.apply {
                campaign = item
                executePendingBindings()
            }
        }
    }
}

private class CampaignDiffCallback : DiffUtil.ItemCallback<Campaign>() {
    override fun areItemsTheSame(oldItem: Campaign, newItem: Campaign): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Campaign, newItem: Campaign): Boolean {
        return oldItem == newItem
    }
}