package com.track.trackhabit.habit.presentation.ui.sleeptime

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.track.trackhabit.habit.databinding.FragmentSleepTimeBinding
import com.track.trackhabit.habit.presentation.ui.SleeptimeListAdapter
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import java.text.SimpleDateFormat as SimpleDateFormat1

@AndroidEntryPoint
class SleepTimeFragment : Fragment() {
    lateinit var binding: FragmentSleepTimeBinding
    val viewModel: SleepTimeViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSleepTimeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.sleeptimeViewModel = viewModel
        setTimePicker()
        binding.buttonSleeptimeConfirmwaketime.setOnClickListener {
            binding.buttonSleeptimeConfirmwaketime.visibility = View.GONE
            binding.textviewSleeptimeSleeptimetitle.visibility = View.VISIBLE
            showListSleepTime()
        }
    }

    private fun showListSleepTime() {
        val recyclerView = binding.recyclerviewSleeptimeSuggestsleeptime
        val sleeptimeListAdapter = SleeptimeListAdapter()
        recyclerView.adapter = sleeptimeListAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        viewModel.addListSleepTime()
        viewModel.sleepTimeList.observe(viewLifecycleOwner){
            sleeptimeListAdapter.submitList(it)
        }
    }

    private fun setTimePicker() {
        binding.textviewSleeptimeWaketime.setOnClickListener {
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                viewModel.setWakeTime(SimpleDateFormat1("HH:mm").format(cal.time))
            }
            TimePickerDialog(
                this.context,
                timeSetListener,
                cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE),
                true
            ).show()
        }
    }
}