package com.example.unsplash.features.somefeature.presenter.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.unsplash.R
import com.example.unsplash.databinding.FragmentSomeBinding

class SomeFragment : Fragment() {

    private var _binding: FragmentSomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSomeBinding.inflate(inflater, container, false)

        context?.let {
            binding.root.setBackgroundColor(ContextCompat.getColor(it, R.color.purple))
        }


        val view = binding.root

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}