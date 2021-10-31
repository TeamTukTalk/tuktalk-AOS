package com.example.tuktalk.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.tuktalk.R
import com.example.tuktalk.common.Constants
import com.example.tuktalk.databinding.ActivityMainBinding
import com.example.tuktalk.presentation.chat.ChatFragment
import com.example.tuktalk.presentation.community.CommunityFragment
import com.example.tuktalk.presentation.home.HomeFragment
import com.example.tuktalk.presentation.mypage.MyPageFragment
import com.example.tuktalk.presentation.search.SearchFragment


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    val homeFragment = HomeFragment()
    val searchFragment = SearchFragment()
    val communityFragment = CommunityFragment()
    val chatFragment = ChatFragment()
    val myPageFragment = MyPageFragment()

    var first_home = true
    var first_search = true
    var first_community = true
    var first_chat = true
    var first_mypage = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)




        // 하단 bottom navigation 아이콘 설정하기!!
        initBottomNavigation()

        replaceFragment(homeFragment)  // 시작은 홈
        binding.bottomNavi.selectedItemId = R.id.tuktalk_home

    }

    fun replaceFragment(fragment: Fragment){

       /* var transaction = supportFragmentManager.beginTransaction()
                .apply {
                    replace(R.id.framelayout, fragment)
                }

        transaction.addToBackStack(null)
        transaction.commit() */

        supportFragmentManager.beginTransaction()
                .apply {
                    replace(R.id.framelayout, fragment)
                    commit()
                }

        // 하단바 이동 로직에 따라 구현하기!! ex> 요기요는 홈 제외 다른 탭에서 뒤로가기하면 스택 상관없이 홈 탭으로 무조건 이동!!
    }


    fun initBottomNavigation(){

        var menu = binding.bottomNavi.menu

        // 아이콘 변경 정상적으로 되게 하기 위함
        binding.bottomNavi.itemIconTintList = null

        binding.bottomNavi.setOnItemSelectedListener { item ->
            when(item.itemId){
                R.id.tuktalk_home -> {
                    item.setIcon(R.drawable.ic_home_on)
                    menu.findItem(R.id.tuktalk_community).setIcon(R.drawable.ic_community_off)
                    menu.findItem(R.id.tuktalk_search).setIcon(R.drawable.ic_search_off)
                    menu.findItem(R.id.tuktalk_chat).setIcon(R.drawable.ic_talk_off)
                    menu.findItem(R.id.tuktalk_mypage).setIcon(R.drawable.ic_my_off)
                    //replaceFragment(homeFragment)
                    supportFragmentManager.beginTransaction().show(homeFragment).commit()
                    supportFragmentManager.beginTransaction().hide(searchFragment).commit()
                    supportFragmentManager.beginTransaction().hide(communityFragment).commit()
                    supportFragmentManager.beginTransaction().hide(chatFragment).commit()
                    supportFragmentManager.beginTransaction().hide(myPageFragment).commit()
                }
                R.id.tuktalk_search -> {
                    item.setIcon(R.drawable.ic_search_on)
                    menu.findItem(R.id.tuktalk_community).setIcon(R.drawable.ic_community_off)
                    menu.findItem(R.id.tuktalk_home).setIcon(R.drawable.ic_home_off)
                    menu.findItem(R.id.tuktalk_chat).setIcon(R.drawable.ic_talk_off)
                    menu.findItem(R.id.tuktalk_mypage).setIcon(R.drawable.ic_my_off)
                    //replaceFragment(searchFragment)
                    if(first_search == true){
                        first_search = false
                        supportFragmentManager.beginTransaction().add(R.id.framelayout, searchFragment).commit()
                    }
                    supportFragmentManager.beginTransaction().show(searchFragment).commit()
                    supportFragmentManager.beginTransaction().hide(homeFragment).commit()
                    supportFragmentManager.beginTransaction().hide(communityFragment).commit()
                    supportFragmentManager.beginTransaction().hide(chatFragment).commit()
                    supportFragmentManager.beginTransaction().hide(myPageFragment).commit()
                }
                R.id.tuktalk_community -> {
                    item.setIcon(R.drawable.ic_community_on)
                    menu.findItem(R.id.tuktalk_home).setIcon(R.drawable.ic_home_off)
                    menu.findItem(R.id.tuktalk_search).setIcon(R.drawable.ic_search_off)
                    menu.findItem(R.id.tuktalk_chat).setIcon(R.drawable.ic_talk_off)
                    menu.findItem(R.id.tuktalk_mypage).setIcon(R.drawable.ic_my_off)
                    //replaceFragment(communityFragment)
                    if(first_community == true){
                        first_community = false
                        supportFragmentManager.beginTransaction().add(R.id.framelayout, communityFragment).commit()
                    }
                    supportFragmentManager.beginTransaction().show(communityFragment).commit()
                    supportFragmentManager.beginTransaction().hide(searchFragment).commit()
                    supportFragmentManager.beginTransaction().hide(homeFragment).commit()
                    supportFragmentManager.beginTransaction().hide(chatFragment).commit()
                    supportFragmentManager.beginTransaction().hide(myPageFragment).commit()
                }
                R.id.tuktalk_chat -> {
                    item.setIcon(R.drawable.ic_talk_on)
                    menu.findItem(R.id.tuktalk_community).setIcon(R.drawable.ic_community_off)
                    menu.findItem(R.id.tuktalk_search).setIcon(R.drawable.ic_search_off)
                    menu.findItem(R.id.tuktalk_home).setIcon(R.drawable.ic_home_off)
                    menu.findItem(R.id.tuktalk_mypage).setIcon(R.drawable.ic_my_off)
                   // replaceFragment(chatFragment)
                    if(first_chat == true){
                        first_chat = false
                        supportFragmentManager.beginTransaction().add(R.id.framelayout, chatFragment).commit()
                    }
                    supportFragmentManager.beginTransaction().show(chatFragment).commit()
                    supportFragmentManager.beginTransaction().hide(searchFragment).commit()
                    supportFragmentManager.beginTransaction().hide(communityFragment).commit()
                    supportFragmentManager.beginTransaction().hide(myPageFragment).commit()
                    supportFragmentManager.beginTransaction().hide(homeFragment).commit()
                }
                R.id.tuktalk_mypage -> {
                    item.setIcon(R.drawable.ic_my_on)
                    menu.findItem(R.id.tuktalk_community).setIcon(R.drawable.ic_community_off)
                    menu.findItem(R.id.tuktalk_search).setIcon(R.drawable.ic_search_off)
                    menu.findItem(R.id.tuktalk_chat).setIcon(R.drawable.ic_talk_off)
                    menu.findItem(R.id.tuktalk_home).setIcon(R.drawable.ic_home_off)
                   // replaceFragment(myPageFragment)
                    if(first_mypage == true){
                        first_mypage = false
                        supportFragmentManager.beginTransaction().add(R.id.framelayout, myPageFragment).commit()
                    }
                    supportFragmentManager.beginTransaction().show(myPageFragment).commit()
                    supportFragmentManager.beginTransaction().hide(searchFragment).commit()
                    supportFragmentManager.beginTransaction().hide(communityFragment).commit()
                    supportFragmentManager.beginTransaction().hide(chatFragment).commit()
                    supportFragmentManager.beginTransaction().hide(homeFragment).commit()

                }
        }
            true

        }

    }

    // 홈 탭 외에 다른 탭에서 뒤로가기 누를 시 -> 각 프래그먼트에서 onbackpressed callback 구현으로 수정
    override fun onBackPressed() {
        /*if(Constants.BOTTOM_NAVI_NUM == 0)
            super.onBackPressed()
        else if(Constants.BOTTOM_NAVI_NUM == 1){  // 탐색탭에서 뒤로가기 처리
            if(Constants.SEARCH_FRAGMENT == 0){
                binding.bottomNavi.selectedItemId = R.id.tuktalk_home
            }
            else{

            }
        }
        else if(Constants.BOTTOM_NAVI_NUM == 2){
            binding.bottomNavi.selectedItemId = R.id.tuktalk_home
        }
        else if(Constants.BOTTOM_NAVI_NUM == 3){
            binding.bottomNavi.selectedItemId = R.id.tuktalk_home
        }
        else{
            binding.bottomNavi.selectedItemId = R.id.tuktalk_home

        }*/
        super.onBackPressed()
    }

    fun backToHome(){
        binding.bottomNavi.selectedItemId = R.id.tuktalk_home
    }
}