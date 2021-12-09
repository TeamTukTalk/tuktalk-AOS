package com.example.tuktalk.presentation.mypage.mentor.mentorInfo.tab.portfolio

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.WindowManager
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.tuktalk.R
import com.example.tuktalk.databinding.ActivityMentorRegistPortfolioBinding
import com.example.tuktalk.databinding.ActivityPortfolioOpenBinding

class PortfolioOpenActivity: AppCompatActivity() {

    private lateinit var binding : ActivityPortfolioOpenBinding

    private var PortfolioPdfUrl = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("AppTest", "PortfolioOpenActivity/ onCreate")

        binding = DataBindingUtil.setContentView<ActivityPortfolioOpenBinding>(this, R.layout.activity_portfolio_open)

        // 포트폴리오 pdf 캡쳐방지 -> 현재 액티비티에만 적용
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE)

        // toolbar 설정!!
        setSupportActionBar(binding.toolbar)  // 액션바로 xml에 만들어준 toolbar를 사용한다
        supportActionBar!!.setDisplayHomeAsUpEnabled(true) // 뒤로가기 버튼 활성화
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_icon_back)
        supportActionBar!!.setDisplayShowTitleEnabled(true) // 제목 보이게하기
        supportActionBar!!.setTitle("포트폴리오 열람")
        /////////////////////


        // 포트폴리오 pdf url 전달받기
        PortfolioPdfUrl = intent.getStringExtra("portfolioPdfUrl").toString()
        Log.e("AppTest", "PortfolioOpenActivity/  pdf url : ${PortfolioPdfUrl}")

        // WebView 설정
        binding.webView.webViewClient = WebViewClientCustom()
        binding.webView.settings.setSupportZoom(true) // 확대 기능 허용
        binding.webView.settings.javaScriptEnabled = true

        binding.webView.loadUrl("https://docs.google.com/gview?embedded=true&url=$PortfolioPdfUrl")

    }

    inner class WebViewClientCustom : WebViewClient(){

        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
            // 다른 url 요청 시 아무 일어나지 않도록 true 리턴
            // view!!.loadUrl("https://docs.google.com/gview?embedded=true&url=$PortfolioPdfUrl")
            // 위 주석 해제 시 어떤 url 와도 위 주석 부분만 실행
            return true
        }

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {  //웹뷰 페이지 로딩 시작
            super.onPageStarted(view, url, favicon)
            Toast.makeText(this@PortfolioOpenActivity, "포트폴리오를 불러오고 있습니다." , Toast.LENGTH_SHORT).show()
        }

        override fun onPageFinished(view: WebView?, url: String?) { // 웹뷰 페이지 로딩 완료
            super.onPageFinished(view, url)
            Toast.makeText(this@PortfolioOpenActivity, "포트폴리오 가져오기 완료." , Toast.LENGTH_SHORT).show()
        }
    }


    // toolbar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                // 좌측 상단 뒤로가기 버튼 누를 시
                Log.e("AppTest", "toolbar back btn clicked")
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        Log.e("AppTest", "PortfolioOpenActivity/ onResume")
    }
}