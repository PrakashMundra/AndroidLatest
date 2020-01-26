package com.androidlatest.views.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.androidlatest.databinding.FragmentMainDetailsBinding


class MainDetailsFragment : Fragment() {
    private val args: MainDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMainDetailsBinding.inflate(inflater, container, false)
        binding.detailsName.text = args.name
        binding.detailsToolbar.setNavigationOnClickListener { view ->
            view.findNavController().navigateUp()
        }
        return binding.root
    }
}