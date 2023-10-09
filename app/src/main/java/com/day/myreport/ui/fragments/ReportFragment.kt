package com.day.myreport.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.day.myreport.R
import com.day.myreport.databinding.FragmentHomeBinding
import com.day.myreport.databinding.FragmentReportBinding
import com.day.myreport.ui.adapters.TextAdapter
import com.day.myreport.ui.viewmodels.MainViewModel

class ReportFragment : Fragment() {

    private lateinit var textAdapter: TextAdapter
    private var _binding: FragmentReportBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentReportBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textAdapter = TextAdapter()

        binding.rvList.layoutManager = LinearLayoutManager(requireContext())
        binding.rvList.adapter = textAdapter

        viewModel.textList.observe(viewLifecycleOwner, Observer { newTextList ->
            // Update the RecyclerView adapter with the new data
            Log.v("LIST",newTextList.toString())
            textAdapter.submitList(newTextList)
        })
    }
}