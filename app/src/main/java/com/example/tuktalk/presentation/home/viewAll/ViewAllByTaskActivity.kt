package com.example.tuktalk.presentation.home.viewAll

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.tuktalk.R
import com.example.tuktalk.databinding.ActivityLoginBinding
import com.example.tuktalk.databinding.ActivityViewallBytaskBinding
import com.example.tuktalk.domain.model.home.HomeTop5MentorRVitem
import com.example.tuktalk.domain.model.home.MentorListRVitem
import com.example.tuktalk.presentation.home.adapter.Top5MentorRVAdpater
import com.example.tuktalk.presentation.home.viewAll.adapter.ViewAllByTaskRVAdapter

class ViewAllByTaskActivity: AppCompatActivity() {

    private lateinit var binding : ActivityViewallBytaskBinding

    lateinit var rvAdapter: ViewAllByTaskRVAdapter
    private var testDataList = mutableListOf<MentorListRVitem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityViewallBytaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // toolbar 설정!!
        setSupportActionBar(binding.toolbar)  // 액션바로 xml에 만들어준 toolbar를 사용한다
        supportActionBar!!.setDisplayHomeAsUpEnabled(true) // 뒤로가기 버튼 활성화
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_icon_back)
        supportActionBar!!.setDisplayShowTitleEnabled(false) // 제목 안보임
        ///////////

        // 리사이클러뷰 설정


    }

    // toolbar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                // 뒤로가기 버튼 누를 시
                Log.e("AppTest", "toolbar back btn clicked")
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}