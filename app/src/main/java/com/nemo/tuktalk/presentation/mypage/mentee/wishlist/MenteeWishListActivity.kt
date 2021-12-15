package com.nemo.tuktalk.presentation.mypage.mentee.wishlist

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.nemo.tuktalk.R
import com.nemo.tuktalk.common.utils.VerticalItemDecorator
import com.nemo.tuktalk.databinding.ActivityMenteeRecentPortfolioBinding
import com.nemo.tuktalk.databinding.ActivityMenteeWishlistBinding
import com.nemo.tuktalk.domain.model.mypage.mentee.wishlist.WishListItem
import com.nemo.tuktalk.domain.model.search.SearchDesignMentorList
import com.nemo.tuktalk.presentation.mypage.mentee.recentPortfolio.MenteeRecentPortfolioViewModel
import com.nemo.tuktalk.presentation.mypage.mentee.wishlist.adapter.MenteeWishListRVadapter
import com.nemo.tuktalk.presentation.mypage.mentor.mentorInfo.MentorInfoActivity
import com.nemo.tuktalk.presentation.search.adpater.SearchDesignRVadapter
import org.koin.android.viewmodel.ext.android.viewModel

class MenteeWishListActivity: AppCompatActivity() {

    private lateinit var binding : ActivityMenteeWishlistBinding
    private val viewModel : MenteeWishListViewModel by viewModel()

    lateinit var rvAdapter: MenteeWishListRVadapter
    private var testDataSet = mutableListOf<WishListItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.e("AppTest","MenteeWishListActivity/ onCreate")

        binding = DataBindingUtil.setContentView<ActivityMenteeWishlistBinding>(this, R.layout.activity_mentee_wishlist)


        // toolbar 설정!!
        setSupportActionBar(binding.toolbar)  // 액션바로 xml에 만들어준 toolbar를 사용한다
        supportActionBar!!.setDisplayHomeAsUpEnabled(true) // 뒤로가기 버튼 활성화 -> HomeAsUp item id = R.id.home
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_icon_back)
        supportActionBar!!.setDisplayShowTitleEnabled(true) // 기본제목 없애기
        supportActionBar!!.setTitle("찜목록")
        /////////////////////

        // RV 설정
        rvAdapter = MenteeWishListRVadapter(testDataSet,
        deleteWish = {
            // viewModel의 찜 취소 연동하기
            viewModel.deleteWishMentor(it)
        })
        binding.rvMenteeWishList.layoutManager = LinearLayoutManager(this)
        binding.rvMenteeWishList.adapter = rvAdapter
        binding.rvMenteeWishList.addItemDecoration(VerticalItemDecorator(15))


        // 찜목록 있는지 바로 살펴보기
        viewModel.getMenteeWishList()

        viewModel.Is_Get_Wish_List_Success.observe(this, {
            if(it){
                Log.e("AppTest", "MenteeWishListActivity/ 찜 멘토 리스트 검색 통신 성공")
                rvAdapter.updateList(viewModel.MenteeWishList)

                if(viewModel.IsResultEmpty)
                    binding.llNoWishlist.visibility = View.VISIBLE
                else
                    binding.llNoWishlist.visibility = View.INVISIBLE
            }
            else{
                Log.e("AppTest", "MenteeWishListActivity/ 찜 멘토 리스트 검색 통신 실패")
                Toast.makeText(this, "찜목록 가져오기에 실패했습니다.", Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.ProgressBarVisibility.observe(this, {
            if(it)
                binding.loadingProgressBar.visibility = View.VISIBLE
            else
                binding.loadingProgressBar.visibility = View.INVISIBLE
        })

        ///////////////////////////////////////////////////////////////////

        viewModel.Is_Delete_Wish_Mentor_Success.observe(this, {
            if(it){
                Log.e("AppTest", "MenteeWishListActivity/ 찜 취소 성공 -> 찜 목록 업데이트")

                viewModel.getMenteeWishList()
            }
            else{
                Log.e("AppTest", "MenteeWishListActivity/ 찜 취소에 실패했습니다")
                Toast.makeText(this, "찜 취소에 실패했습니다.", Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.ProgressBarVisibility_delete.observe(this, {
            if(it)
                binding.loadingProgressBarDelete.visibility = View.VISIBLE
            else
                binding.loadingProgressBarDelete.visibility = View.INVISIBLE
        })


    }



    // toolbar -> 현재 뒤로가기만 활성화
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