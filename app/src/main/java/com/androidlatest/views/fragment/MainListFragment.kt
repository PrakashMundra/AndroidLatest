package com.androidlatest.views.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.androidlatest.databinding.FragmentMainListBinding
import com.androidlatest.utils.InjectorUtils
import com.androidlatest.viewmodels.MainListViewModel
import com.androidlatest.views.adapter.CampaignListAdapter

class MainListFragment : Fragment() {
    private val viewModel: MainListViewModel by viewModels {
        InjectorUtils.provideMainListViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMainListBinding.inflate(inflater, container, false)
        val adapter = CampaignListAdapter()
        binding.mainList.adapter = adapter
        binding.viewModel = viewModel
        subscribeUi(adapter)
        return binding.root
    }

    private fun subscribeUi(adapter: CampaignListAdapter) {
        viewModel.campaignData.observe(viewLifecycleOwner) { campaigns ->
            adapter.submitList(campaigns)
            viewModel.loading.set(false)
        }
    }
}