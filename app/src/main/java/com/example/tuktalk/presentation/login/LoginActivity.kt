package com.example.tuktalk.presentation.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.tuktalk.R
import com.example.tuktalk.databinding.ActivityLoginBinding
import com.example.tuktalk.presentation.signup.SelectRoleActivity

class LoginActivity: AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // 로그인 activity 오른쪽에서 왼쪽으로 나타나는 애니메이션 적용
        overridePendingTransition(R.anim.slide_right_enter, R.anim.none)

        // 아이디 입력 시 edittext 색상 변경
       binding.etId.setOnFocusChangeListener(View.OnFocusChangeListener { view, focused ->
            if(focused){
                Log.e("AppTest","et id focused")
                binding.etId.backgroundTintList = ContextCompat.getColorStateList(applicationContext, R.color.tuktalk_primary)
                //binding.etId.error = "error test"
            }
            else{
                Log.e("AppTest","et id focus x")
                binding.etId.backgroundTintList = ContextCompat.getColorStateList(applicationContext, R.color.tuktalk_gray1)
            }
        })
        //

        // 아이디 - 이메일 형식 체크
        binding.etId.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                var idText = binding.etId.text
                var emailPattern = android.util.Patterns.EMAIL_ADDRESS

                if(emailPattern.matcher(idText).matches()){
                    binding.llErrorId.visibility = View.GONE
                    // 모두 0 값을 주면 아이콘 사라진다!!
                    binding.etId.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, 0,0)
                }
                else{
                    binding.llErrorId.visibility = View.VISIBLE

                    // 동적으로 edittext에 아이콘 생성!!
                    binding.etId.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_icon_error,0)
                }

                // 빈 칸 되었을 때
                if(idText.length == 0){
                    binding.llErrorId.visibility = View.GONE
                    binding.etId.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, 0,0)
                }
            }
            override fun afterTextChanged(p0: Editable?) {

            }
        })
        //


        // 비밀번호 입력 시 edittext 색상 변경
        binding.etPw.setOnFocusChangeListener(View.OnFocusChangeListener { view, focused ->
            if(focused){
                Log.e("AppTest","et pw focused")
                binding.etPw.backgroundTintList = ContextCompat.getColorStateList(applicationContext, R.color.tuktalk_primary)
                //binding.etId.error = "error test"
            }
            else{
                Log.e("AppTest","et pw focus x")
                binding.etPw.backgroundTintList = ContextCompat.getColorStateList(applicationContext, R.color.tuktalk_gray1)
            }
        })
        //

        // 비밀번호 틀릴 시  오류 아이콘 및 오류 문구 나타낸는 것 추가해주기!!
        //
        //
        //
        //


        // 회원가입 클릭 시 -> 회원가입 화면으로 이동하기
        binding.tvSignUp.setOnClickListener {
            Log.e("AppTest","go to select role activity")
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
}