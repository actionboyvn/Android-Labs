package com.example.cwiczenie4

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import android.widget.Button
import androidx.fragment.app.Fragment
import android.widget.TextView
import android.widget.ToggleButton

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RightFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RightFragment : Fragment(){
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var fontSize: Float = 20F
    private var myAM: ActionMode? =  null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_right, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val textView = requireActivity().findViewById<TextView>(R.id.textView)
        val textView6 = requireActivity().findViewById<TextView>(R.id.textView6)
        val button2 = requireActivity().findViewById<Button>(R.id.button2)
        val toggleButton2 = requireActivity().findViewById<ToggleButton>(R.id.toggleButton2)
        val data: SharedPreferences = requireActivity().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        var editor = data.edit()
        if (data.getInt("theme_num", 0) == 3) {
            fontSize = data.getFloat("font_size", 20F)
            textView.setTextSize(fontSize)
            button2.setTextSize(fontSize)
            toggleButton2.setTextSize(fontSize)
        }
        val myAMCallBack: ActionMode.Callback = object : ActionMode.Callback {
            override fun onCreateActionMode(p0: ActionMode?, p1: Menu?): Boolean {
                p0?.menuInflater?.inflate(R.menu.cam_menu, p1)
                return true
            }
            override fun onPrepareActionMode(p0: ActionMode?, p1: Menu?): Boolean {
                return false
            }
            override fun onActionItemClicked(p0: ActionMode?, p1: MenuItem?): Boolean {
                when (p1?.itemId) {
                    R.id.item_1 -> {
                        editor.putFloat("font_size", 20F)
                        editor.commit()
                        p0?.finish()
                    }
                    R.id.item_2 -> {
                        editor.putFloat("font_size", 30F)
                        editor.commit()
                        p0?.finish()
                    }
                    R.id.item_3 -> {
                        editor.putFloat("font_size", 40F)
                        editor.commit()
                        p0?.finish()
                    }
                    else -> {
                    }
                }
                fontSize = data.getFloat("font_size", 20F)
                textView.setTextSize(fontSize)
                button2.setTextSize(fontSize)
                toggleButton2.setTextSize(fontSize)
                return false
            }
            override fun onDestroyActionMode(p0: ActionMode?) {
                myAM = null
            }
        }
        textView6.setOnLongClickListener(View.OnLongClickListener { view ->
            if (myAM != null) {
                return@OnLongClickListener false
            }
            myAM = requireActivity().startActionMode(myAMCallBack)
            view.isSelected = true
            true
        })
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RightFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RightFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


}