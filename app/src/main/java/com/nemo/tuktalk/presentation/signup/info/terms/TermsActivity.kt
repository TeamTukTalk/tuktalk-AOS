package com.nemo.tuktalk.presentation.signup.info.terms

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.nemo.tuktalk.R
import com.nemo.tuktalk.databinding.ActivityTermsBinding

class TermsActivity: AppCompatActivity() {

    private lateinit var binding : ActivityTermsBinding

    var isTerm1Open = false
    var isTerm2Open = false
    var isTerm3Open = false
    var isTerm4Open = false
    var isTerm5Open = false
    var isTerm6Open = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.e("AppTest","Terms activity onCreate")

        binding = DataBindingUtil.setContentView<ActivityTermsBinding>(this, R.layout.activity_terms)


        // toolbar 설정!!
        setSupportActionBar(binding.toolbar)  // 액션바로 xml에 만들어준 toolbar를 사용한다
        supportActionBar!!.setDisplayHomeAsUpEnabled(true) // 뒤로가기 버튼 활성화
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_icon_back)
        supportActionBar!!.setDisplayShowTitleEnabled(false) // 기본제목 없애기
        /////////////////////

        // term1
        binding.llCh1.setOnClickListener {
            if(isTerm1Open){ // 열려있는 상태에서 클릭 시
                binding.clContent1.visibility = View.GONE
                binding.ivCh1Arrow.setImageResource(R.drawable.ic_terms_arrow_down)
                isTerm1Open = !isTerm1Open
            }
            else{ // 닫혀있는 상태에서 클릭 시
                binding.clContent1.visibility = View.VISIBLE
                binding.ivCh1Arrow.setImageResource(R.drawable.ic_terms_arrow_up)
                isTerm1Open = !isTerm1Open
            }
        }
        /////////////////////////////////

        // term2
        binding.llCh2.setOnClickListener {
            if(isTerm2Open){ // 열려있는 상태에서 클릭 시
                binding.clContent2.visibility = View.GONE
                binding.ivCh2Arrow.setImageResource(R.drawable.ic_terms_arrow_down)
                isTerm2Open = !isTerm2Open
            }
            else{ // 닫혀있는 상태에서 클릭 시
                binding.clContent2.visibility = View.VISIBLE
                binding.ivCh2Arrow.setImageResource(R.drawable.ic_terms_arrow_up)
                isTerm2Open = !isTerm2Open
            }
        }
        /////////////////////////////////

        // term3
        binding.llCh3.setOnClickListener {
            if(isTerm3Open){ // 열려있는 상태에서 클릭 시
                binding.clContent3.visibility = View.GONE
                binding.ivCh3Arrow.setImageResource(R.drawable.ic_terms_arrow_down)
                isTerm3Open = !isTerm3Open
            }
            else{ // 닫혀있는 상태에서 클릭 시
                binding.clContent3.visibility = View.VISIBLE
                binding.ivCh3Arrow.setImageResource(R.drawable.ic_terms_arrow_up)
                isTerm3Open = !isTerm3Open
            }
        }
        /////////////////////////////////

        // term4
        binding.llCh4.setOnClickListener {
            if(isTerm4Open){ // 열려있는 상태에서 클릭 시
                binding.clContent4.visibility = View.GONE
                binding.ivCh4Arrow.setImageResource(R.drawable.ic_terms_arrow_down)
                isTerm4Open = !isTerm4Open
            }
            else{ // 닫혀있는 상태에서 클릭 시
                binding.clContent4.visibility = View.VISIBLE
                binding.ivCh4Arrow.setImageResource(R.drawable.ic_terms_arrow_up)
                isTerm4Open = !isTerm4Open
            }
        }
        /////////////////////////////////

        // term5
        binding.llCh5.setOnClickListener {
            if(isTerm5Open){ // 열려있는 상태에서 클릭 시
                binding.clContent5.visibility = View.GONE
                binding.ivCh5Arrow.setImageResource(R.drawable.ic_terms_arrow_down)
                isTerm5Open = !isTerm5Open
            }
            else{ // 닫혀있는 상태에서 클릭 시
                binding.clContent5.visibility = View.VISIBLE
                binding.ivCh5Arrow.setImageResource(R.drawable.ic_terms_arrow_up)
                isTerm5Open = !isTerm5Open
            }
        }
        /////////////////////////////////

        // term1
        binding.llCh6.setOnClickListener {
            if(isTerm6Open){ // 열려있는 상태에서 클릭 시
                binding.clContent6.visibility = View.GONE
                binding.ivCh6Arrow.setImageResource(R.drawable.ic_terms_arrow_down)
                isTerm6Open = !isTerm6Open
            }
            else{ // 닫혀있는 상태에서 클릭 시
                binding.clContent6.visibility = View.VISIBLE
                binding.ivCh6Arrow.setImageResource(R.drawable.ic_terms_arrow_up)
                isTerm6Open = !isTerm6Open
            }
        }
        /////////////////////////////////


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