package com.example.cwiczenie5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DataAddingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DataAddingFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var position = -1
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val cancelButton = requireActivity().findViewById<Button>(R.id.cancel)
        val addButton = requireActivity().findViewById<Button>(R.id.add)
        cancelButton.setOnClickListener(View.OnClickListener {
            if (position == -1)
                requireActivity().onBackPressed()
            else {
                setFragmentResult("itemNumFromList", bundleOf("num" to position.toString()))
                it.findNavController().navigate(R.id.action_to_detailsFragment)
            }
        }
        )
        addButton.setOnClickListener(View.OnClickListener {
            val data = MyRepository.getinstance(requireContext()).getData()
            val item = DBItem()
            if (position != -1) {
                item.id = data?.get(position)?.id!!
                MyRepository.getinstance(requireContext()).deleteItem(data[position])
            }
            val editText1 = requireActivity().findViewById<EditText>(R.id.editText1)
            val editText2 = requireActivity().findViewById<EditText>(R.id.editText2)
            val seekBar = requireActivity().findViewById<SeekBar>(R.id.seekBar)
            val ratingBar = requireActivity().findViewById<RatingBar>(R.id.ratingBar)
            val radioButton1 = requireActivity().findViewById<RadioButton>(R.id.radioButton1)
            item.text_main = editText1.text.toString()
            item.text_2 = editText2.text.toString()
            item.item_value = ratingBar.rating.toInt()
            item.item_value2 = seekBar.progress
            item.item_type = radioButton1.isChecked
            if (MyRepository.getinstance(requireContext()).addItem(item))
                parentFragmentManager.setFragmentResult("item_added", Bundle.EMPTY)
            if (position == -1)
                requireActivity().onBackPressed()
            else {
                setFragmentResult("itemNumFromList", bundleOf("num" to position.toString()))
                it.findNavController().navigate(R.id.action_to_detailsFragment)
            }
        }
        )

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        setFragmentResultListener("itemNumToModify") { requestKey, bundle ->
            position = bundle.getString("numToModify")?.toInt()!!
            val data = MyRepository.getinstance(requireContext()).getData()
            if (position != -1) {
                val editText1 = requireActivity().findViewById<EditText>(R.id.editText1)
                val editText2 = requireActivity().findViewById<EditText>(R.id.editText2)
                val seekBar = requireActivity().findViewById<SeekBar>(R.id.seekBar)
                val ratingBar = requireActivity().findViewById<RatingBar>(R.id.ratingBar)
                val radioButton1 = requireActivity().findViewById<RadioButton>(R.id.radioButton1)
                val radioButton2 = requireActivity().findViewById<RadioButton>(R.id.radioButton2)
                editText1.setText(data?.get(position)?.text_main)
                editText2.setText(data?.get(position)?.text_2)
                ratingBar.rating = data?.get(position)?.item_value!!.toFloat()
                seekBar.progress = data?.get(position)?.item_value2!!
                if (data?.get(position)?.item_type == true)
                    radioButton1.isChecked = true
                else
                    radioButton2.isChecked = true
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_data_adding, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DataAddingFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DataAddingFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}