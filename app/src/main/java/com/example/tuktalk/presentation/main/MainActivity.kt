package com.example.tuktalk.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.tuktalk.R
import com.example.tuktalk.databinding.ActivityMainBinding
import com.example.tuktalk.presentation.chat.ChatFragment
import com.example.tuktalk.presentation.community.CommunityFragment
import com.example.tuktalk.presentation.home.HomeFragment
import com.example.tuktalk.presentation.mypage.MyPageFragment
import com.example.tuktalk.presentation.search.SearchFragment
import org.koin.android.ext.android.bind


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    val homeFragment = HomeFragment()
    val searchFragment = SearchFragment()
    val communityFragment = CommunityFragment()
    val chatFragment = ChatFragment()
    val myPageFragment = MyPageFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)




        // 하단 bottom navigation 아이콘 설정하기!!
        initBottomNavigation()

        replaceFragment(homeFragment)  // 시작은 홈
        binding.bottomNavi.selectedItemId = R.id.tutalk_home

    }

    fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
                .apply {
                    replace(R.id.framelayout, fragment)
                    commit()
                }
    }


    fun initBottomNavigation(){

        var menu = binding.bottomNavi.menu

        // 아이콘 변경 정상적으로 되게 하기 위함
        binding.bottomNavi.itemIconTintList = null

        binding.bottomNavi.setOnItemSelectedListener { item ->
            when(item.itemId){
                R.id.tutalk_home -> {
                    item.setIcon(R.drawable.ic_home_on)
                    menu.findItem(R.id.tutalk_community).setIcon(R.drawable.ic_community_off)
                    menu.findItem(R.id.tutalk_search).setIcon(R.drawable.ic_search_off)
                    menu.findItem(R.id.tutalk_chat).setIcon(R.drawable.ic_talk_off)
                    menu.findItem(R.id.tutalk_mypage).setIcon(R.drawable.ic_my_off)
                    replaceFragment(homeFragment)
                }
                R.id.tutalk_search -> {
                    item.setIcon(R.drawable.ic_search_on)
                    menu.findItem(R.id.tutalk_community).setIcon(R.drawable.ic_community_off)
                    menu.findItem(R.id.tutalk_home).setIcon(R.drawable.ic_home_off)
                    menu.findItem(R.id.tutalk_chat).setIcon(R.drawable.ic_talk_off)
                    menu.findItem(R.id.tutalk_mypage).setIcon(R.drawable.ic_my_off)
                    replaceFragment(searchFragment)
                }
                R.id.tutalk_community -> {
                    item.setIcon(R.drawable.ic_community_on)
                    menu.findItem(R.id.tutalk_home).setIcon(R.drawable.ic_home_off)
                    menu.findItem(R.id.tutalk_search).setIcon(R.drawable.ic_search_off)
                    menu.findItem(R.id.tutalk_chat).setIcon(R.drawable.ic_talk_off)
                    menu.findItem(R.id.tutalk_mypage).setIcon(R.drawable.ic_my_off)
                    replaceFragment(communityFragment)
                }
                R.id.tutalk_chat -> {
                    item.setIcon(R.drawable.ic_talk_on)
                    menu.findItem(R.id.tutalk_community).setIcon(R.drawable.ic_community_off)
                    menu.findItem(R.id.tutalk_search).setIcon(R.drawable.ic_search_off)
                    menu.findItem(R.id.tutalk_home).setIcon(R.drawable.ic_home_off)
                    menu.findItem(R.id.tutalk_mypage).setIcon(R.drawable.ic_my_off)
                    replaceFragment(chatFragment)
                }
                R.id.tutalk_mypage -> {
                    item.setIcon(R.drawable.ic_my_on)
                    menu.findItem(R.id.tutalk_community).setIcon(R.drawable.ic_community_off)
                    menu.findItem(R.id.tutalk_search).setIcon(R.drawable.ic_search_off)
                    menu.findItem(R.id.tutalk_chat).setIcon(R.drawable.ic_talk_off)
                    menu.findItem(R.id.tutalk_home).setIcon(R.drawable.ic_home_off)
                    replaceFragment(myPageFragment)

                }
        }
            true

        }

    }
}