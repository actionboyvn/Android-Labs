package com.example.cwiczenie4

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.core.view.iterator
import androidx.fragment.app.Fragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LeftFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LeftFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var fontSize: Float = 20F
    private var buttonColor: String = "default"
    private var fontStyle: Int = 0


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
        return inflater.inflate(R.layout.fragment_left, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val textView3 = requireActivity().findViewById<TextView>(R.id.textView3)
        val textView4 = requireActivity().findViewById<TextView>(R.id.textView4)
        val textView5 = requireActivity().findViewById<TextView>(R.id.textView5)
        registerForContextMenu(textView3)
        registerForContextMenu(textView4)
        registerForContextMenu(textView5)

        val textView2 = requireActivity().findViewById<TextView>(R.id.textView2)
        val button = requireActivity().findViewById<Button>(R.id.button)
        val toggleButton = requireActivity().findViewById<ToggleButton>(R.id.toggleButton)
        val data: SharedPreferences = requireActivity().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        if (data.getInt("theme_num", 0) == 3) {
            fontSize = data.getFloat("font_size", 20F)
            textView2.setTextSize(fontSize)
            button.setTextSize(fontSize)
            toggleButton.setTextSize(fontSize)
            buttonColor = data.getString("button_color", "default").toString()
            when (buttonColor) {
                "orange" -> {
                    button.setBackgroundColor(Color.parseColor("#FFA500"))
                }
                "blue" -> {
                    button.setBackgroundColor(Color.parseColor("#00FFFF"))
                }
                "green" -> {
                    button.setBackgroundColor(Color.parseColor("#008000"))
                }
                else -> {
                    button.setBackgroundColor(Color.parseColor("#7B1FA2"))
                }
            }
            fontStyle = data.getInt("font_style", 0)
            when (fontStyle) {
                0 -> {
                    button.setTypeface(null, Typeface.NORMAL)
                    textView2.setTypeface(null, Typeface.NORMAL)
                    toggleButton.setTypeface(null, Typeface.NORMAL)
                }
                1 -> {
                    button.setTypeface(null, Typeface.BOLD)
                    textView2.setTypeface(null, Typeface.BOLD)
                    toggleButton.setTypeface(null, Typeface.BOLD)
                }
                2 -> {
                    button.setTypeface(null, Typeface.ITALIC)
                    textView2.setTypeface(null, Typeface.ITALIC)
                    toggleButton.setTypeface(null, Typeface.ITALIC)
                }
                3 -> {
                    button.setTypeface(null, Typeface.BOLD_ITALIC)
                    textView2.setTypeface(null, Typeface.BOLD_ITALIC)
                    toggleButton.setTypeface(null, Typeface.BOLD_ITALIC)
                }
            }
        }

    }
    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        if (v.getId() == R.id.textView3) {
            menu.add(0, 0, 0, "Normal")
            menu.add(0, 1, 1, "Large")
            menu.add(0, 2, 2, "Larger")
        }
        if (v.getId() == R.id.textView4) {
            menu.add(0, 3, 0, "Orange")
            menu.add(0, 4, 1, "Sky blue")
            menu.add(0, 5, 2, "Grass green")
            menu.add(0, 6, 2, "Default")
        }
        if (v.getId() == R.id.textView5) {
            requireActivity().menuInflater.inflate(R.menu.multi_choice_menu, menu)
            val data: SharedPreferences = requireActivity().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
            fontStyle = data.getInt("font_style", 0)
            for (item in menu) {
                if (item.itemId == R.id.item_bold)
                    if (fontStyle == 1 || fontStyle == 3)
                        item.setChecked(true)
                    else
                        item.setChecked(false)
                if (item.itemId == R.id.item_italic)
                    if (fontStyle == 2 || fontStyle == 3)
                        item.setChecked(true)
                    else
                        item.setChecked(false)
            }
        }
    }
    override fun onContextItemSelected(item: MenuItem): Boolean {
        val data = requireActivity().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        var editor = data.edit()
        val textView2 = requireActivity().findViewById<TextView>(R.id.textView2)
        val button = requireActivity().findViewById<Button>(R.id.button)
        val toggleButton = requireActivity().findViewById<ToggleButton>(R.id.toggleButton)
        when (item.itemId) {
            0 -> {
                textView2.setTextSize(20F)
                button.setTextSize(20F)
                toggleButton.setTextSize(20F)
                editor.putFloat("font_size", 20F)
                true
            }
            1 -> {
                textView2.setTextSize(30F)
                button.setTextSize(30F)
                toggleButton.setTextSize(30F)
                editor.putFloat("font_size", 30F)
                true
            }
            2 -> {
                textView2.setTextSize(40F)
                button.setTextSize(40F)
                toggleButton.setTextSize(40F)
                editor.putFloat("font_size", 40F)
                true
            }
            3 -> {
                button.setBackgroundColor(Color.parseColor("#FFA500"))
                editor.putString("button_color", "orange")
                true
            }
            4 -> {
                button.setBackgroundColor(Color.parseColor("#00FFFF"))
                editor.putString("button_color", "blue")
                true
            }
            5 -> {
                button.setBackgroundColor(Color.parseColor("#008000"))
                editor.putString("button_color", "green")
                true
            }
            6 -> {
                button.setBackgroundColor(Color.parseColor("#7B1FA2"))
                editor.putString("button_color", "default")
                true
            }
            else -> {
                super.onContextItemSelected(item)
            }
        }
        editor.commit()
        when (item.itemId) {
            R.id.item_bold -> {
                if (item.isChecked()) {
                    item.setChecked(false)
                    editor.putInt("font_style", data.getInt("font_style", 0) - 1)
                }
                else {
                    editor.putInt("font_style", data.getInt("font_style", 0) + 1)
                    item.setChecked(true)
                }
                true
            }
            R.id.item_italic -> {
                if (item.isChecked()) {
                    editor.putInt("font_style", data.getInt("font_style", 0) - 2)
                    item.setChecked(false)
                }
                else {
                    editor.putInt("font_style", data.getInt("font_style", 0) + 2)
                    item.setChecked(true)
                }
                true
            }
            else -> {
                super.onContextItemSelected(item)
            }
        }
        editor.commit()
        when (data.getInt("font_style", 0)) {
            0 -> {
                button.setTypeface(null, Typeface.NORMAL)
                textView2.setTypeface(null, Typeface.NORMAL)
                toggleButton.setTypeface(null, Typeface.NORMAL)
            }
            1 -> {
                button.setTypeface(null, Typeface.BOLD)
                textView2.setTypeface(null, Typeface.BOLD)
                toggleButton.setTypeface(null, Typeface.BOLD)
            }
            2 -> {
                button.setTypeface(null, Typeface.ITALIC)
                textView2.setTypeface(null, Typeface.ITALIC)
                toggleButton.setTypeface(null, Typeface.ITALIC)
            }
            3 -> {
                button.setTypeface(null, Typeface.BOLD_ITALIC)
                textView2.setTypeface(null, Typeface.BOLD_ITALIC)
                toggleButton.setTypeface(null, Typeface.BOLD_ITALIC)
            }
        }
        return false
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LeftFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LeftFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}