package com.example.cwiczenie5

import android.app.AlertDialog
import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cwiczenie5.databinding.FragmentList2Binding
import com.example.cwiczenie5.databinding.ListRowBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlin.random.Random


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [List2Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class List2Fragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var recView : RecyclerView
    private lateinit var l2vbinding: FragmentList2Binding
    lateinit var dataRepo: MyRepository
    lateinit var adapter : MyAdapter
    inner class MyAdapter(var data: MutableList<DBItem>) :
        RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
        inner class MyViewHolder(viewBinding : ListRowBinding) :
            RecyclerView.ViewHolder(viewBinding.root) {
            val tv1: TextView = viewBinding.lrowTv1
            val tv2: TextView = viewBinding.lrowTv2
            val img: ImageView = viewBinding.lrowImage

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val viewBinding = ListRowBinding.inflate(LayoutInflater.from(parent.context),
                parent, false)
            return MyViewHolder(viewBinding)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.tv1.text = data[position].text_main
            holder.tv2.text = data[position].text_2
            holder.itemView.setOnClickListener {
                setFragmentResult("itemNumFromList", bundleOf("num" to position.toString()))
                it.findNavController().navigate(R.id.action_to_detailsFragment)
            }
            holder.itemView.setOnLongClickListener {
                val builder = AlertDialog.Builder(it.context)
                builder.setMessage("Delete item nr $position ?")
                    .setCancelable(false)
                    .setPositiveButton("Yes") { dialog, id ->
                        if (dataRepo.deleteItem(data[position])) {
                            data = dataRepo.getData()!!
                            notifyDataSetChanged()
                        }
                    }
                    .setNegativeButton("No") { dialog, id ->
                        // Dismiss the dialog
                        dialog.dismiss()
                    }
                val alert = builder.create()
                alert.show()
                true
            }
            when (data[position].item_type) {
                false -> holder.img.setImageResource(R.drawable.ic_female)
                true -> holder.img.setImageResource(R.drawable.ic_male)
            }

        }
        override fun getItemCount(): Int {
            return data.size
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recView = requireActivity().findViewById(R.id.myRecView)
        recView.layoutManager = LinearLayoutManager(requireContext())
        recView.adapter = adapter

        val fab = requireActivity().findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener(View.OnClickListener {
            findNavController().navigate(R.id.action_to_dataAddingFragment)
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        dataRepo = MyRepository.getinstance(requireContext())
        adapter = MyAdapter(dataRepo.getData()!!)
        parentFragmentManager.setFragmentResultListener("item_added", this) {
                requestKey, _ ->
            adapter.data = dataRepo.getData()!!
            adapter.notifyDataSetChanged()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list2, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment List2Fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            List2Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}

