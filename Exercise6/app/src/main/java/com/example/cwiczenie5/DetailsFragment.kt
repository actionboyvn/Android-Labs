package com.example.cwiczenie5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RatingBar
import android.widget.SeekBar
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var position = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        setFragmentResultListener("itemNumFromList") { requestKey, bundle ->
            position = bundle.getString("num")?.toInt()!!
            val data = MyRepository.getinstance(requireContext()).getData()
            val name =view?.findViewById<TextView>(R.id.name)
            val address =view?.findViewById<TextView>(R.id.address)
            val fame = view?.findViewById<RatingBar>(R.id.fame)
            val skills = view?.findViewById<SeekBar>(R.id.skills)
            val gender = view?.findViewById<TextView>(R.id.gender)

            name?.text = data?.get(position!!)?.text_main
            address?.text = data?.get(position!!)?.text_2
            fame?.rating = data?.get(position!!)?.item_value?.toFloat()!!

            skills?.progress = data?.get(position!!)?.item_value2!!
            if (data?.get(position!!)?.item_type == true)
                gender?.text = "Male"
            else
                gender?.text = "Female"
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val buttonBack = view.findViewById<Button>(R.id.buttonBack)
        val buttonModify = view.findViewById<Button>(R.id.buttonModify)
        buttonBack.setOnClickListener(View.OnClickListener {
            requireActivity().onBackPressed()
        })
        buttonModify.setOnClickListener(View.OnClickListener {
            setFragmentResult("itemNumToModify", bundleOf("numToModify" to position.toString()))
            findNavController().navigate(R.id.action_to_dataAddingFragment)
        })
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DetailsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}