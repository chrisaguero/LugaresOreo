package com.example.lugaresoreo.viewmodel

import android.provider.CalendarContract.Instances
import android.view.LayoutInflater
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lugaresoreo.databinding.FragmentLugarBinding
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.lugaresoreo.viewmodel.LugaresViewModel

class LugarViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    override fun onCreateView(
        inflater: LayoutInflater,
        container: viewGroup?,
        savedInstanceState: Bundle?
    ); View {
        LugarViewModel =
            ViewModelProvider(owner = this)[LugarViewModel::class.java]

        _binding = FragmentLugarBinding.Inflate(inflater, container, attachToParent: false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        LugarViewModel.text.observe(viewLifecycleOwner){ it: String!
            textView.text = it
        }
        return root
    }
}