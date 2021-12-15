package com.nemo.tuktalk.presentation.mypage.mentee.managereview.tab.writereview

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.nemo.tuktalk.R
import com.nemo.tuktalk.databinding.ActivityPortfolioOpenBinding
import com.nemo.tuktalk.databinding.ActivityWriteReviewBinding
import com.nemo.tuktalk.presentation.mypage.mentee.managereview.MenteeManageReviewViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class WriteReviewActivity: AppCompatActivity() {

    private lateinit var binding : ActivityWriteReviewBinding
    private val viewModel : WriteReviewViewModel by viewModel()

    private var MENTOR_ID = -1

    private var RATING = 0

    var ivStarList = arrayOfNulls<ImageView>(5)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("AppTest", "WriteReviewActivity/ onCreate")

        binding = DataBindingUtil.setContentView<ActivityWriteReviewBinding>(this, R.layout.activity_write_review)

        // toolbar 설정!!
        setSupportActionBar(binding.toolbar)  // 액션바로 xml에 만들어준 toolbar를 사용한다
        supportActionBar!!.setDisplayHomeAsUpEnabled(true) // 뒤로가기 버튼 활성화
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_icon_back)
        supportActionBar!!.setDisplayShowTitleEnabled(true) // 제목 보이게하기
        supportActionBar!!.setTitle("리뷰작성")
        /////////////////////

        // 멘토 아이디 받아오기 ->  리뷰 등록시 필요!
        MENTOR_ID = intent.getIntExtra("mentorId", -1)
        Log.e("AppTest", "WriteReviewActivity/ mentorId : ${MENTOR_ID}")

        ///////////////////////////////////////////


        // 리뷰 작성 cardview 테두리 효과
        binding.etWriteReview.setOnFocusChangeListener(View.OnFocusChangeListener { view, focused ->
            if(focused){
                Log.e("AppTest","et one introduce focused")
                binding.cvWriteReview.strokeColor = resources.getColor(R.color.tuktalk_primary)
                binding.cvWriteReview.invalidate()
            }
            else{
                Log.e("AppTest","et one introduce  focus x")
                binding.cvWriteReview.strokeColor = resources.getColor(R.color.tuktalk_gray_1)
                binding.cvWriteReview.invalidate()
            }
        })

        // 내용 입력 감지
        binding.etWriteReview.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                var userinput = binding.etWriteReview.text.toString()
                if(userinput.length > 0)
                    viewModel.inputReview(true)
                else
                    viewModel.inputReview(false)

                viewModel.REVIEW_CONTENT = userinput
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })

        //////////////////////////////////////////

        // Rating
        ivStarList[0] = binding.ivStar1
        ivStarList[1] = binding.ivStar2
        ivStarList[2] = binding.ivStar3
        ivStarList[3] = binding.ivStar4
        ivStarList[4] = binding.ivStar5

        for(index in 0..4){
            ivStarList[index]!!.setOnClickListener {
                rating(index)
                RATING = index+1
                viewModel.REVIEW_RATING = RATING
                Log.e("AppTest", "WriteReviewActivity/ current rating : ${viewModel.REVIEW_RATING}")

                viewModel.selectRating(true)
            }
        }

        /////////////////

        viewModel.btnActivate.observe(this, {
            if(it){
                binding.btnWriteReviewActive.visibility = View.VISIBLE
                binding.btnWriteReviewUnactive.visibility = View.INVISIBLE
            }
            else{
                binding.btnWriteReviewActive.visibility = View.INVISIBLE
                binding.btnWriteReviewUnactive.visibility = View.VISIBLE
            }
        })


        // 리뷰 등록 api 연동하기
        binding.btnWriteReviewActive.setOnClickListener {
            Log.e("AppTest", "리뷰등록 버튼 clicked, rating : ${viewModel.REVIEW_RATING}, review : ${viewModel.REVIEW_CONTENT}")

            viewModel.writeReview(MENTOR_ID)

        }

        viewModel.IsWriteReviewSuccess.observe(this, {
            if(it){
                Log.e("AppTest", "WriteReviewActivity/ 후기 작성 결과 : 성공")
                Toast.makeText(this, "리뷰 등록을 완료했습니다.", Toast.LENGTH_SHORT).show()
                finish()  // 리뷰 작성 액티비티 종료
            }
            else{
                Log.e("AppTest", "WriteReviewActivity/ 후기 작성 결과 : 실패")
                Toast.makeText(this, "리뷰 등록에 실패했습니다.", Toast.LENGTH_SHORT).show()
            }
        })



    }


    // toolbar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                // 좌측 상단 뒤로가기 버튼 누를 시
                Log.e("AppTest", "WriteReviewActivity/ toolbar back btn clicked")
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


    // index까지 별표 활성화하기
    fun rating(index: Int){
        for(i in 0..index){
            ivStarList[i]!!.setImageResource(R.drawable.ic_star_active)
        }

        if(index < 4){
            for(i in index+1..4){
                ivStarList[i]!!.setImageResource(R.drawable.ic_star_unactive)
            }
        }
    }


    override fun onResume() {
        super.onResume()
        Log.e("AppTest", "WriteReviewActivity/ onResume")
    }
}