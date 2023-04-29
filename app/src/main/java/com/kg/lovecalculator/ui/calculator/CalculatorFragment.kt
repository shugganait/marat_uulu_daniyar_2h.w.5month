package com.kg.lovecalculator.ui.calculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.kg.lovecalculator.App
import com.kg.lovecalculator.CalcLoveViewModel
import com.kg.lovecalculator.R
import com.kg.lovecalculator.remote.RetrofitService
import com.kg.lovecalculator.databinding.FragmentCalculatorBinding
import com.kg.lovecalculator.remote.LoveModel
import com.kg.lovecalculator.simpleModels.Love
import retrofit2.Call
import retrofit2.Response

class CalculatorFragment : Fragment() {

    private lateinit var binding: FragmentCalculatorBinding
    val viewModel: CalcLoveViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCalculatorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initNavigations()
        initClickListeners()
    }

    private fun initClickListeners() {
        binding.apply {
            btnCalculate.setOnClickListener {
                if (etFirstName.text!!.isEmpty()) {
                    etFirstName.error = "field for first name is empty(("
                } else if (etSecondName.text!!.isEmpty()) {
                    etSecondName.error = "field for second name is empty(("
                } else {
                    viewModel.liveLove(etFirstName.text.toString(), etSecondName.text.toString()).observe(viewLifecycleOwner, Observer {
                        findNavController().navigate(
                            R.id.resultFragment,
                            bundleOf(
                                KEY_FOR_FNAME to it.firstName,
                                KEY_FOR_SNAME to it.secondName,
                                KEY_FOR_PERC to it.percentage,
                                KEY_FOR_RESULT to it.result
                            )
                        )
                    })


                    //Old Version
                    /*RetrofitService().api.percentageName(
                        etFirstName.text.toString(),
                        etSecondName.text.toString()
                    ).enqueue(object : retrofit2.Callback<LoveModel> {
                        override fun onResponse(
                            call: Call<LoveModel>,
                            response: Response<LoveModel>
                        ) {
                            findNavController().navigate(
                                R.id.resultFragment,
                                bundleOf(
                                    KEY_FOR_FNAME to response.body()?.firstName,
                                    KEY_FOR_SNAME to response.body()?.secondName,
                                    KEY_FOR_PERC to response.body()?.percentage,
                                    KEY_FOR_RESULT to response.body()?.result
                                )
                            )
                            etFirstName.text?.clear()
                            etSecondName.text?.clear()
                        }

                        override fun onFailure(call: Call<LoveModel>, t: Throwable) {
                            Toast.makeText(requireContext(), "FAIL: $t", Toast.LENGTH_LONG).show()
                        }
                    })*/
                }
            }
        }
    }

    private fun initNavigations() {
        binding.apply {
            btnNavigateHome.setOnClickListener {
                findNavController().navigate(R.id.calculatorFragment)
            }
            btnNavigateHistory.setOnClickListener {
                findNavController().navigate(R.id.historyFragment)
            }
        }
    }

    companion object {
        const val KEY_FOR_FNAME = "fname"
        const val KEY_FOR_SNAME = "sname"
        const val KEY_FOR_PERC = "1000%"
        const val KEY_FOR_RESULT = "result"
    }
}