package com.example.tuktalk.presentation.signup

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.tuktalk.R
import com.example.tuktalk.common.Constants
import com.example.tuktalk.databinding.ActivitySelectCategoryBinding
import com.example.tuktalk.presentation.signup.info.InfoRegistActivity
import com.example.tuktalk.presentation.signup.info.breakaway.BreakAwayDialogFragment
import com.google.android.material.card.MaterialCardView


class SelectCategoryActivity: AppCompatActivity() {
        private lateinit var binding : ActivitySelectCategoryBinding
        private var IsMentee = true
        private var categoryCvList = arrayOfNulls<MaterialCardView>(12)
        private var categoryTvList = arrayOfNulls<TextView>(12)
        private var isCategorySelected = Array<Boolean>(12) { false }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySelectCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        IsMentee = intent.getBooleanExtra("isMentee", true)
        Log.e("AppTest", "mentee sign up? : ${IsMentee}")

        // 멘티로 가입
        if(IsMentee){
            binding.tvMenteeQuestion.visibility = View.VISIBLE
        }
        else{ // 멘토로 가입
            binding.tvMentorQuestion.visibility = View.VISIBLE
        }

        // 좌측 상단 뒤로가기 아이콘 클릭 시
        /*binding.clBackBtn.setOnClickListener {
            onBackPressed()
        }*/


        // toolbar 설정!!
        setSupportActionBar(binding.toolbar)  // 액션바로 xml에 만들어준 toolbar를 사용한다
        supportActionBar!!.setDisplayHomeAsUpEnabled(true) // 뒤로가기 버튼 활성화
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_icon_back)
        supportActionBar!!.setDisplayShowTitleEnabled(false) // 기본제목 없애기

        ////////////////////////////////////////////////////////////////////////

        // category list - cardview
        categoryCvList[0] = binding.cardviewCategory1
        categoryCvList[1] = binding.cardviewCategory2
        categoryCvList[2] = binding.cardviewCategory3
        categoryCvList[3] = binding.cardviewCategory4
        categoryCvList[4] = binding.cardviewCategory5
        categoryCvList[5] = binding.cardviewCategory6
        categoryCvList[6] = binding.cardviewCategory7
        categoryCvList[7] = binding.cardviewCategory8
        categoryCvList[8] = binding.cardviewCategory9
        categoryCvList[9] = binding.cardviewCategory10
        categoryCvList[10] = binding.cardviewCategory11
        categoryCvList[11] = binding.cardviewCategory12

        // category list - textview
        categoryTvList[0] = binding.tvCategory1
        categoryTvList[1] = binding.tvCategory2
        categoryTvList[2] = binding.tvCategory3
        categoryTvList[3] = binding.tvCategory4
        categoryTvList[4] = binding.tvCategory5
        categoryTvList[5] = binding.tvCategory6
        categoryTvList[6] = binding.tvCategory7
        categoryTvList[7] = binding.tvCategory8
        categoryTvList[8] = binding.tvCategory9
        categoryTvList[9] = binding.tvCategory10
        categoryTvList[10] = binding.tvCategory11
        categoryTvList[11] = binding.tvCategory12

        // 클릭 이벤트 설정해주기
        for(index in 0..11){
            categoryCvList[index]!!.setOnClickListener {
                isCategorySelected[index] = !isCategorySelected[index]  // 토글 구현

                if(isCategorySelected[index]){ // 선택 되었다면
                    categoryCvList[index]!!.strokeColor = resources.getColor(R.color.tuktalk_primary)
                    categoryTvList[index]!!.setTextColor(resources.getColor(R.color.tuktalk_primary))
                }
                else{
                    categoryCvList[index]!!.strokeColor = resources.getColor(R.color.tuktalk_gray4)
                    categoryTvList[index]!!.setTextColor(resources.getColor(R.color.tuktalk_gray2))
                }

                if(selectedCheck()){
                    binding.btnNext.visibility = View.VISIBLE
                }else{
                    binding.btnNext.visibility = View.INVISIBLE
                }
            }
        }


        // 다음 버튼 클릭 시
        binding.btnNext.setOnClickListener {

            checkCategory()  // 선택된 카테고리 추가

            Log.e("AppTest", "go to info regist activity")
            val intent = Intent(this, InfoRegistActivity::class.java)
            intent.putExtra("isMentee", IsMentee)
            startActivity(intent)

            // 선택 한 카테고리 정보 넘겨줘야한다!!!


        }

        // 해야 할 것
        // 1. 카테고리 데이터 넘기는 방식?? -> string list 형태


    }

    // 카테고리가 하나라도 선택 되었는지 보기 위해
    fun selectedCheck() : Boolean{
        var selected = false
        for(index in 0..11){
            if(isCategorySelected[index]){
                Log.e("AppTest", "selected!")
                selected = true
                break
            }
        }
        return selected
    }


    // toolbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // toolbar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_close ->{
                // 닫기 버튼 누를 시 다이얼로그 나타남
                Log.e("AppTest", "toolbar close btn clicked")
                var dialogVeiw = BreakAwayDialogFragment()
                dialogVeiw.show(supportFragmentManager, "AppTest")
            }
            android.R.id.home -> {
                // 뒤로가기 버튼 누를 시
                Log.e("AppTest", "toolbar back btn clicked")
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    // 선택한 카테고리 체크 후 추가
    fun checkCategory(){
        for(index in 0..11){
            if(isCategorySelected[index]){
                Constants.SELECT_CATEGORY_LIST.add(categoryTvList[index]?.text.toString())
            }
        }
        Log.e("AppTest", "선택된 카테고리 ${Constants.SELECT_CATEGORY_LIST}")
    }

}