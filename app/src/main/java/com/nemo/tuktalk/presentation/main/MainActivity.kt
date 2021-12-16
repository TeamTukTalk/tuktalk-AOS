package com.nemo.tuktalk.presentation.main

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.nemo.tuktalk.R
import com.nemo.tuktalk.common.Constants
import com.nemo.tuktalk.databinding.ActivityMainBinding
import com.nemo.tuktalk.presentation.chat.ChatFragment
import com.nemo.tuktalk.presentation.community.CommunityFragment
import com.nemo.tuktalk.presentation.home.HomeFragment
import com.nemo.tuktalk.presentation.mypage.MyPageFragment
import com.nemo.tuktalk.presentation.search.SearchFragment
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel : MainActivityViewModel by viewModel()

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

    private var lastTimeBackPressed : Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        var fragmentManager = supportFragmentManager

        // 하단 bottom navigation 아이콘 설정하기!!
        initBottomNavigation()

        //replaceFragment(homeFragment)  // 시작은 홈

        //fragmentManager.beginTransaction().replace(R.id.framelayout, homeFragment, "home").commitAllowingStateLoss()
        binding.bottomNavi.selectedItemId = R.id.tuktalk_home
        
        
        //// 멘토인 경우 기업 메일 인증 여부 확인하기 실행
        if(Constants.USER_MODE == 0){
            viewModel.checkMentorCertified()
        }

        // 로그인한 유저 아이디(Int)값 조회
        viewModel.getUserInfo()

        viewModel.isCertificationCheckSuccess.observe(this, {
            if(it){
                Log.e("AppTest", "MainActivity/ 인증 여부 확인 성공")

                if(Constants.IS_CERTIFIED_MENTOR)
                    Toast.makeText(this, "기업 이메일 인증이 확인되었습니다.", Toast.LENGTH_SHORT).show()
                else
                    Toast.makeText(this, "기업 이메일 인증이 필요합니다.", Toast.LENGTH_SHORT).show()
            }
            else{
                Log.e("AppTest", "MainActivity/ 인증 여부 확인 실패")
                Toast.makeText(this, "기업 이메일 인증 여부 확인에 실패했습니다.", Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.isGetUserInfoSuccess.observe(this, {
            if(it){
                Log.e("AppTest", "MainActivity/ 유저 정보 조회 성공")
            }
            else{
                Log.e("AppTest", "MainActivity/ 유저 정보 조회 실패")
            }
        })


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
                    add(R.id.framelayout, fragment)
                    commit()
                }

        // 하단바 이동 로직에 따라 구현하기!! ex> 요기요는 홈 제외 다른 탭에서 뒤로가기하면 스택 상관없이 홈 탭으로 무조건 이동!!
    }


    fun initBottomNavigation(){

        var menu = binding.bottomNavi.menu

        // 아이콘 변경 정상적으로 되게 하기 위함
        binding.bottomNavi.itemIconTintList = null

        binding.bottomNavi.setOnItemSelectedListener { item ->

            var fragmentManager = supportFragmentManager

            when(item.itemId){
                R.id.tuktalk_home -> {
                    item.setIcon(R.drawable.ic_home_on)
                    Constants.BOTTOM_NAVI_NUM = 0
                    //menu.findItem(R.id.tuktalk_community).setIcon(R.drawable.ic_community_off)
                    menu.findItem(R.id.tuktalk_search).setIcon(R.drawable.ic_search_off)
                   // menu.findItem(R.id.tuktalk_chat).setIcon(R.drawable.ic_talk_off)
                    menu.findItem(R.id.tuktalk_mypage).setIcon(R.drawable.ic_my_off)
                    //replaceFragment(homeFragment)
                    if (fragmentManager.findFragmentByTag("home") != null) {
                        fragmentManager.beginTransaction().show(fragmentManager.findFragmentByTag("home")!!).commit()
                    } else {
                        fragmentManager.beginTransaction().add(R.id.framelayout, homeFragment, "home").commit()
                    }

                    if (fragmentManager.findFragmentByTag("search") != null) {
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("search")!!).commit()
                    }
                  /*  if (fragmentManager.findFragmentByTag("community") != null) {
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("community")!!).commit()
                    }
                    if (fragmentManager.findFragmentByTag("chat") != null) {
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("chat")!!).commit()
                    }*/
                    if (fragmentManager.findFragmentByTag("mypage") != null) {
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("mypage")!!).commit()
                    }

                    /*supportFragmentManager.beginTransaction().show(homeFragment).commit()
                    supportFragmentManager.beginTransaction().hide(searchFragment).commit()
                    supportFragmentManager.beginTransaction().hide(communityFragment).commit()
                    supportFragmentManager.beginTransaction().hide(chatFragment).commit()
                    supportFragmentManager.beginTransaction().hide(myPageFragment).commit()*/
                }
                R.id.tuktalk_search -> {
                    item.setIcon(R.drawable.ic_search_on)
                    Constants.BOTTOM_NAVI_NUM = 1
                    //menu.findItem(R.id.tuktalk_community).setIcon(R.drawable.ic_community_off)
                    menu.findItem(R.id.tuktalk_home).setIcon(R.drawable.ic_home_off)
                   // menu.findItem(R.id.tuktalk_chat).setIcon(R.drawable.ic_talk_off)
                    menu.findItem(R.id.tuktalk_mypage).setIcon(R.drawable.ic_my_off)

                    if (fragmentManager.findFragmentByTag("search") != null) {
                        fragmentManager.beginTransaction().show(fragmentManager.findFragmentByTag("search")!!).commit()
                    } else {
                        fragmentManager.beginTransaction().add(R.id.framelayout, searchFragment, "search").commit()
                    }

                    if (fragmentManager.findFragmentByTag("home") != null) {
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("home")!!).commit()
                    }
                   /* if (fragmentManager.findFragmentByTag("community") != null) {
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("community")!!).commit()
                    }
                    if (fragmentManager.findFragmentByTag("chat") != null) {
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("chat")!!).commit()
                    }*/
                    if (fragmentManager.findFragmentByTag("mypage") != null) {
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("mypage")!!).commit()
                    }

                    //replaceFragment(searchFragment)
                    /* if(first_search == true){
                        first_search = false
                        supportFragmentManager.beginTransaction().add(R.id.framelayout, searchFragment).commit()
                    }
                    supportFragmentManager.beginTransaction().show(searchFragment).commit()
                    supportFragmentManager.beginTransaction().hide(homeFragment).commit()
                    supportFragmentManager.beginTransaction().hide(communityFragment).commit()
                    supportFragmentManager.beginTransaction().hide(chatFragment).commit()
                    supportFragmentManager.beginTransaction().hide(myPageFragment).commit()*/
                }
              /*  R.id.tuktalk_community -> {
                    item.setIcon(R.drawable.ic_community_on)
                    Constants.BOTTOM_NAVI_NUM = 2
                    menu.findItem(R.id.tuktalk_home).setIcon(R.drawable.ic_home_off)
                    menu.findItem(R.id.tuktalk_search).setIcon(R.drawable.ic_search_off)
                    menu.findItem(R.id.tuktalk_chat).setIcon(R.drawable.ic_talk_off)
                    menu.findItem(R.id.tuktalk_mypage).setIcon(R.drawable.ic_my_off)

                    if (fragmentManager.findFragmentByTag("community") != null) {
                        fragmentManager.beginTransaction().show(fragmentManager.findFragmentByTag("community")!!).commit()
                    } else {
                        fragmentManager.beginTransaction().add(R.id.framelayout, communityFragment, "community").commit()
                    }

                    if (fragmentManager.findFragmentByTag("home") != null) {
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("home")!!).commit()
                    }
                    if (fragmentManager.findFragmentByTag("search") != null) {
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("search")!!).commit()
                    }
                    if (fragmentManager.findFragmentByTag("chat") != null) {
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("chat")!!).commit()
                    }
                    if (fragmentManager.findFragmentByTag("mypage") != null) {
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("mypage")!!).commit()
                    }
                    //replaceFragment(communityFragment)
                    /*if(first_community == true){
                        first_community = false
                        supportFragmentManager.beginTransaction().add(R.id.framelayout, communityFragment).commit()
                    }
                    supportFragmentManager.beginTransaction().show(communityFragment).commit()
                    supportFragmentManager.beginTransaction().hide(searchFragment).commit()
                    supportFragmentManager.beginTransaction().hide(homeFragment).commit()
                    supportFragmentManager.beginTransaction().hide(chatFragment).commit()
                    supportFragmentManager.beginTransaction().hide(myPageFragment).commit()*/
                } */
             /*   R.id.tuktalk_chat -> {
                    item.setIcon(R.drawable.ic_talk_on)
                    Constants.BOTTOM_NAVI_NUM = 3
                    menu.findItem(R.id.tuktalk_community).setIcon(R.drawable.ic_community_off)
                    menu.findItem(R.id.tuktalk_search).setIcon(R.drawable.ic_search_off)
                    menu.findItem(R.id.tuktalk_home).setIcon(R.drawable.ic_home_off)
                    menu.findItem(R.id.tuktalk_mypage).setIcon(R.drawable.ic_my_off)

                    if (fragmentManager.findFragmentByTag("chat") != null) {
                        fragmentManager.beginTransaction().show(fragmentManager.findFragmentByTag("chat")!!).commit()
                    } else {
                        fragmentManager.beginTransaction().add(R.id.framelayout, chatFragment, "chat").commit()
                    }

                    if (fragmentManager.findFragmentByTag("home") != null) {
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("home")!!).commit()
                    }
                    if (fragmentManager.findFragmentByTag("search") != null) {
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("search")!!).commit()
                    }
                    if (fragmentManager.findFragmentByTag("community") != null) {
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("community")!!).commit()
                    }
                    if (fragmentManager.findFragmentByTag("mypage") != null) {
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("mypage")!!).commit()
                    }

                    // replaceFragment(chatFragment)
                    /*if(first_chat == true){
                        first_chat = false
                        supportFragmentManager.beginTransaction().add(R.id.framelayout, chatFragment).commit()
                    }
                    supportFragmentManager.beginTransaction().show(chatFragment).commit()
                    supportFragmentManager.beginTransaction().hide(searchFragment).commit()
                    supportFragmentManager.beginTransaction().hide(communityFragment).commit()
                    supportFragmentManager.beginTransaction().hide(myPageFragment).commit()
                    supportFragmentManager.beginTransaction().hide(homeFragment).commit()*/
                } */
                R.id.tuktalk_mypage -> {
                    item.setIcon(R.drawable.ic_my_on)
                    Constants.BOTTOM_NAVI_NUM = 4
                   // menu.findItem(R.id.tuktalk_community).setIcon(R.drawable.ic_community_off)
                    menu.findItem(R.id.tuktalk_search).setIcon(R.drawable.ic_search_off)
                   // menu.findItem(R.id.tuktalk_chat).setIcon(R.drawable.ic_talk_off)
                    menu.findItem(R.id.tuktalk_home).setIcon(R.drawable.ic_home_off)

                    if (fragmentManager.findFragmentByTag("mypage") != null) {
                        fragmentManager.beginTransaction().show(fragmentManager.findFragmentByTag("mypage")!!).commit()
                    } else {
                        fragmentManager.beginTransaction().add(R.id.framelayout, myPageFragment, "mypage").commit()
                    }

                    if (fragmentManager.findFragmentByTag("home") != null) {
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("home")!!).commit()
                    }
                    if (fragmentManager.findFragmentByTag("search") != null) {
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("search")!!).commit()
                    }
                   /* if (fragmentManager.findFragmentByTag("community") != null) {
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("community")!!).commit()
                    }
                    if (fragmentManager.findFragmentByTag("chat") != null) {
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("chat")!!).commit()
                    }*/
                    // community, chat 현재 기능 보류

                    // replaceFragment(myPageFragment)
                    /*if(first_mypage == true){
                        first_mypage = false
                        supportFragmentManager.beginTransaction().add(R.id.framelayout, myPageFragment).commit()
                    }
                    supportFragmentManager.beginTransaction().show(myPageFragment).commit()
                    supportFragmentManager.beginTransaction().hide(searchFragment).commit()
                    supportFragmentManager.beginTransaction().hide(communityFragment).commit()
                    supportFragmentManager.beginTransaction().hide(chatFragment).commit()
                    supportFragmentManager.beginTransaction().hide(homeFragment).commit()*/

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
        // 홈탭에서 뒤로가기 누를 시!
        if(Constants.BOTTOM_NAVI_NUM == 0){
            if(System.currentTimeMillis() - lastTimeBackPressed >= 2000){
                lastTimeBackPressed = System.currentTimeMillis()
                Toast.makeText(this,"'뒤로' 버튼을 한번 더 누르시면 종료됩니다.",Toast.LENGTH_SHORT).show() }
            else {
                ActivityCompat.finishAffinity(this)
                System.runFinalization()
                System.exit(0)
            }
        }
        else{
            backToHome(0)  // 홈을 제외한 탭에서 뒤로가기 하면 홈탭으로 이동!!

        }
        Log.e("AppTest", "MainActivity onBackPressed / bottom navi num : ${Constants.BOTTOM_NAVI_NUM}\"")
    }

    fun backToHome(previous: Int){   // 현재 홈 에서 뒤로가기하면 backToHome이 호출되고 있다
        Constants.BOTTOM_NAVI_NUM = 0
        binding.bottomNavi.selectedItemId = R.id.tuktalk_home
        Log.e("AppTest", "back to home, previous page num : ${previous}")
    }

    override fun onResume() {
        super.onResume()
        Log.e("AppTest", "Main Activity onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.e("AppTest", "Main Activity onPause")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("AppTest", "Main Activity onDestroy  bottom navi num : ${Constants.BOTTOM_NAVI_NUM}")
    }
}