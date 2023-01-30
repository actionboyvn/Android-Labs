package com.example.cwiczenie5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SwipeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MyPagerAdapter(fm: FragmentManager, behavior: Int) :
    FragmentPagerAdapter(fm, behavior) {
    override fun getItem(position: Int): Fragment {
        if (position == 0)
            return Fragment1.Companion.newInstance("f11", "Page # 1")
        else
            return Fragment2.Companion.newInstance("f12", "Page # 2")
    }
    override fun getCount(): Int {
        return 2
    }
    override fun getPageTitle(position: Int): CharSequence? {
        if (position == 0)
            return "Page 1"
        else
            return "Page 2"
    }
}
class MyPagerAdapter2(fa: FragmentActivity): FragmentStateAdapter(fa) {
    override fun createFragment(position: Int): Fragment {
        if (position == 0)
            return Fragment1.Companion.newInstance("f11", "Page # 1")
        else
            return Fragment2.Companion.newInstance("f12", "Page # 2")
    }
    override fun getItemCount(): Int {
        return 2
    }
}
class SwipeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*
        val vpAdapter = MyPagerAdapter(requireActivity().supportFragmentManager,
            FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
        val vPager = requireActivity().findViewById<View>(R.id.vpager) as ViewPager
        vPager.adapter = vpAdapter
        val tabLayout = requireActivity().findViewById<View>(R.id.tabs) as TabLayout
        tabLayout.setupWithViewPager(vPager)
        //tabLayout!!.getTabAt(i)!!.setIcon(tabIcons[i])
         */
        val vpAdapter = MyPagerAdapter2(requireActivity())
        val vPager = requireActivity().findViewById<View>(R.id.vpager) as ViewPager2
        vPager.adapter = vpAdapter
        val tabLayout = requireActivity().findViewById<View>(R.id.tabs) as TabLayout
        var tabIcons = arrayOf(R.drawable.ic_tab1, R.drawable.ic_tab2)

        TabLayoutMediator(tabLayout, vPager) { tab, position ->
            tab.text = "Tab ${(position + 1)}"
            tab.setIcon(tabIcons[position])
        }.attach()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_swipe, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SwipeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SwipeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}