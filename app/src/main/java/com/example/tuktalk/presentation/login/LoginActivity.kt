package com.example.tuktalk.presentation.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.tuktalk.R
import com.example.tuktalk.data.remote.dto.request.user.UserLoginRequestDto
import com.example.tuktalk.databinding.ActivityLoginBinding
import com.example.tuktalk.presentation.main.MainActivity
import com.example.tuktalk.presentation.signup.SelectRoleActivity
import org.koin.android.viewmodel.ext.android.viewModel

class LoginActivity: AppCompatActivity() {
    private val viewModel : LoginViewModel by viewModel()

    private lateinit var binding : ActivityLoginBinding

    private var ID = ""
    private var PASSWORD = ""

    private var IS_KEYBOARD_UP = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // 로그인 activity 오른쪽에서 왼쪽으로 나타나는 애니메이션 적용
        overridePendingTransition(R.anim.slide_right_enter, R.anim.none)

        // edit text 외부 터치 시 키보드 내려가게 하기 -> 특정 경우 null point error 발생 가능,  우선 막아두기
      /*  binding.clAll.setOnTouchListener(object: View.OnTouchListener{
            override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
                hideKeyboard()
                return false
            }
        })*/


        // 아이디 입력 시 edittext 색상 변경
       binding.etId.setOnFocusChangeListener(View.OnFocusChangeListener { view, focused ->
           if (focused) {
               Log.e("AppTest", "et id focused")
               binding.etId.backgroundTintList = ContextCompat.getColorStateList(applicationContext, R.color.tuktalk_primary)
               //binding.etId.error = "error test"

               IS_KEYBOARD_UP = true
           } else {
               Log.e("AppTest", "et id focus x")
               binding.etId.backgroundTintList = ContextCompat.getColorStateList(applicationContext, R.color.tuktalk_gray1)
           }
       })
        //

        // 아이디 - 이메일 형식 체크
        binding.etId.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                var idText = binding.etId.text
                var emailPattern = android.util.Patterns.EMAIL_ADDRESS

                if (emailPattern.matcher(idText).matches()) {
                    binding.llErrorId.visibility = View.GONE
                    // 모두 0 값을 주면 아이콘 사라진다!!
                    binding.etId.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, 0, 0)
                    ID = idText.toString()
                } else {
                    binding.llErrorId.visibility = View.VISIBLE

                    // 동적으로 edittext에 아이콘 생성!!
                    binding.etId.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_icon_error, 0)
                }

                // 빈 칸 되었을 때
                if (idText.length == 0) {
                    binding.llErrorId.visibility = View.GONE
                    binding.etId.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, 0, 0)
                    //binding.etId.clearFocus()
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })
        //


        // 비밀번호 입력 시 edittext 색상 변경
        binding.etPw.setOnFocusChangeListener(View.OnFocusChangeListener { view, focused ->
            if (focused) {
                Log.e("AppTest", "et pw focused")
                binding.etPw.backgroundTintList = ContextCompat.getColorStateList(applicationContext, R.color.tuktalk_primary)
                //binding.etId.error = "error test"

                IS_KEYBOARD_UP = true
            } else {
                Log.e("AppTest", "et pw focus x")
                binding.etPw.backgroundTintList = ContextCompat.getColorStateList(applicationContext, R.color.tuktalk_gray1)
            }
        })
        //

        // 비밀번호 틀릴 시  오류 아이콘 및 오류 문구 나타낸는 것 추가해주기!!
        //
        //
        //
        //
        binding.etPw.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                var pwText = binding.etPw.text
                PASSWORD = pwText.toString()

            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })


        ////////////////////////////////////////////////////
        
        // 로그인 버튼 클릭 시 -> 성공하면 홈 화면으로 이동
        binding.btnLogin.setOnClickListener {
            var userLoginRequestDto = UserLoginRequestDto(ID, PASSWORD)
            viewModel.logIn(userLoginRequestDto)
        }

        viewModel.isLoginSuccess.observe(this, {
            if (it) {
                val intent = Intent(this, MainActivity::class.java)  // 홈 화면 이동
                startActivity(intent)
            } else {
                Toast.makeText(this, "로그인에 실패하였습니다", Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.progressBarVisibility.observe(this, {
            if (it)
                binding.loadingProgressBar.visibility = View.VISIBLE
            else
                binding.loadingProgressBar.visibility = View.INVISIBLE
        })


        // 회원가입 클릭 시 -> 회원가입 화면으로 이동하기
        binding.tvSignUp.setOnClickListener {
            Log.e("AppTest", "go to select role activity")
            val intent = Intent(this, SelectRoleActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onResume() {
        super.onResume()

        binding.etId.text = null
        binding.etPw.text = null
        binding.etId.clearFocus()
        binding.etPw.clearFocus()
    }

    // edit text 외부 터치 시 키보드 내려가게
    fun hideKeyboard() {
        if(IS_KEYBOARD_UP){
            val inputManager: InputMethodManager = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(this.currentFocus!!.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            IS_KEYBOARD_UP = false
        }
    }
}