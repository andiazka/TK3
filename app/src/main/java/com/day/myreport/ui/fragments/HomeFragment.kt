package com.day.myreport.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.day.myreport.R
import com.day.myreport.database.DbHelper
import com.day.myreport.databinding.FragmentHomeBinding
import com.day.myreport.ui.viewmodels.MainViewModel

class HomeFragment : Fragment(), DbHelper.DatabaseCallback {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            btn0.setOnClickListener{
                binding.textNum.text = setText("0");
            }
            btn1.setOnClickListener{
                binding.textNum.text = setText("1");
            }
            btn2.setOnClickListener{
                binding.textNum.text = setText("2");
            }
            btn3.setOnClickListener{
                binding.textNum.text = setText("3");
            }
            btn4.setOnClickListener{
                binding.textNum.text = setText("4");
            }
            btn5.setOnClickListener{
                binding.textNum.text = setText("5");
            }
            btn6.setOnClickListener{
                binding.textNum.text = setText("6");
            }
            btn7.setOnClickListener{
                binding.textNum.text = setText("7");
            }
            btn8.setOnClickListener{
                binding.textNum.text = setText("8");
            }
            btn9.setOnClickListener{
                binding.textNum.text = setText("9");
            }
            btnSimpan.setOnClickListener{
                viewModel.insertText(binding.textNum.text.toString())
            }
        }
    }

    fun setText(num:String) : String{
        var now = binding.textNum.text.toString()

        return now + num;
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onTextInserted() {
        viewModel.refreshData()
        Toast.makeText(this@HomeFragment.requireActivity().applicationContext,"Inserted!",Toast.LENGTH_SHORT).show()
    }
}