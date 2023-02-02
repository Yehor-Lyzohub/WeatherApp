package com.example.myweatherapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myweatherapp.databinding.FragmentDetailInfoBinding
import com.example.myweatherapp.network.DetailData
import com.example.myweatherapp.network.Main
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DetailInfoFragment : Fragment() {
    private var _binding: FragmentDetailInfoBinding? = null
    private val binding get() = _binding!!

    val args: DetailInfoFragmentArgs by navArgs()

    private val viewModel: WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getDetailData(args.city.location)
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDetailInfoBinding.inflate(inflater, container, false)

        viewModel.data.observe(viewLifecycleOwner, {
                newData ->
            binding.degrees.text =  "${newData.main.temp.toInt()}°"
            binding.feelsLike.text = "Feels like ${newData.main.feelsLike.toInt()}\u00B0"
            binding.tempMinMax.text = "Min: ${newData.main.tempMin.toInt()}\u00B0/Max: ${newData.main.tempMax.toInt()}°"
            //binding.type.text = newData.main.
            //binding.yesterday.text =
            binding.precipitationPercents.text = "${newData.main.humidity}%"
            //binding.uvIndexLevel.text =
        })

        binding.location.text = args.city.location
        binding.date.text = viewModel.dateTime

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        LinearLayoutManager(requireContext())
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}