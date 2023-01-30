package com.example.cwiczenie5

import android.os.Bundle
import android.util.SparseBooleanArray
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.example.cwiczenie5.databinding.FragmentList1Binding
import kotlin.random.Random


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [List1Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class List1Fragment : Fragment(), AdapterView.OnItemClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    var data = DataRepo.getinstance().item_text_list
    lateinit var list1 : ListView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list1 = requireActivity().findViewById(R.id.listview1)
        val adapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_multiple_choice, data)
        list1.adapter = adapter
        list1.onItemClickListener = this
    }
    override fun onItemClick(parentview: AdapterView<*>?, itemView: View?, position: Int, id: Long) {
        var txt = "Clicked " + (position) + " : Checked"
        val check_list : SparseBooleanArray = list1.checkedItemPositions
        for (i in 0 until check_list.size() ) {
            if (check_list.valueAt(i)) {
                val indeks = check_list.keyAt(i)
                txt += " " + (indeks).toString()
            }
        }
        Toast.makeText(requireContext(), txt, Toast.LENGTH_SHORT).show()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list1, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment List1Fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            List1Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


}
class DataRepo
{
    val LIST_SIZE = 25
    val item_text_list = Array(LIST_SIZE) { i -> "Item name " +i}
    private lateinit var dataList: MutableList<DataItem>
    companion object{
        private var INSTANCE: DataRepo? = null
        fun getinstance(): DataRepo {
            if (INSTANCE == null){
                INSTANCE = DataRepo()
            }
            return INSTANCE as DataRepo
        }
    }
    fun getData() : MutableList<DataItem> {
        return dataList
    }
    init {
        dataList = mutableListOf(DataItem(1), DataItem(2), DataItem(3), DataItem(4), DataItem(5))
    }
    fun deleteItem(pos : Int) : Boolean {
        if (dataList.size > pos) {
            dataList.removeAt(pos)
            return true
        }
        return false
    }
}
class DataItem {
    var text_main : String = "Default text"
    var text_2 : String = "Default text"
    var item_value : Int = Random.nextInt(0, 5)
    var item_value2: Int = 0
    var item_type : Boolean = Random.nextBoolean()
    var item_checked : Boolean = Random.nextBoolean()
    constructor()
    constructor(num: Int) : this() {
        text_main = "Item name " + num
        text_2 = "Item value "
    }
}