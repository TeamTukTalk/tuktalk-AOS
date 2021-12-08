package com.example.tuktalk.presentation.signup.info

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.tuktalk.R
import com.example.tuktalk.common.Constants
import com.example.tuktalk.data.remote.dto.request.user.UserSignUpRequestDto
import com.example.tuktalk.databinding.ActivityInfoRegistBinding
import com.example.tuktalk.presentation.signup.complete.SignUpCompleteActivity
import com.example.tuktalk.presentation.signup.info.breakaway.BreakAwayDialogFragment
import com.example.tuktalk.presentation.signup.info.terms.TermsActivity
import org.koin.android.viewmodel.ext.android.viewModel

class InfoRegistActivity: AppCompatActivity() {
    private val viewModel : InfoRegistViewModel by viewModel()

    private var IsMentee = true
    private var IsIdMatchEmail = false // 아이디가 이메일 형식이 맞는지

    private var NICKNAME = ""
    private var ID = ""
    private var PASSWORD = ""
    private var ROLE = ""
    private var PROFILE_IMAGE_COLOR = ""
    private var FIRST_LETTER = ""
    private var PROFILE_URL = "https://avatars.githubusercontent.com/u/92679463?s=200&v=4"  // 회의 후 수정하기

    private var profileImgColorList = arrayOf("profileBlue","profileRed", "profileYellow", "profileGray", "profileGreen")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityInfoRegistBinding>(this, R.layout.activity_info_regist)
        binding.viewModel = viewModel

        // toolbar 설정!!
        setSupportActionBar(binding.toolbar)  // 액션바로 xml에 만들어준 toolbar를 사용한다
        supportActionBar!!.setDisplayHomeAsUpEnabled(true) // 뒤로가기 버튼 활성화
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_icon_back)
        supportActionBar!!.setDisplayShowTitleEnabled(false) // 기본제목 없애기

        // 멘티, 멘토 중 어떤 역할인지
        IsMentee = intent.getBooleanExtra("isMentee", true)
        Log.e("AppTest", "InfoRegistActivity / mentee sign up? : ${IsMentee}")

        if(IsMentee)
            ROLE = "MENTEE"
        else
            ROLE = "MENTOR"


        /////////////

        // 이름 입력 시 edittext 색상 변경
        binding.etName.setOnFocusChangeListener(View.OnFocusChangeListener { view, focused ->
            if(focused){
                Log.e("AppTest","et name focused")
                binding.etName.backgroundTintList = ContextCompat.getColorStateList(applicationContext, R.color.tuktalk_primary)
                //binding.etId.error = "error test"
            }
            else{
                Log.e("AppTest","et name focus x")
                binding.etName.backgroundTintList = ContextCompat.getColorStateList(applicationContext, R.color.tuktalk_gray1)
            }
        })
        //

        // id 입력 시 edittext 색상 변경
        binding.etId.setOnFocusChangeListener(View.OnFocusChangeListener { view, focused ->
            if(focused){
                Log.e("AppTest","et name focused")
                binding.etId.backgroundTintList = ContextCompat.getColorStateList(applicationContext, R.color.tuktalk_primary)
                //binding.etId.error = "error test"
            }
            else{
                Log.e("AppTest","et name focus x")
                binding.etId.backgroundTintList = ContextCompat.getColorStateList(applicationContext, R.color.tuktalk_gray1)
            }
        })
        //

        // 비밀번호 입력 시 edittext 색상 변경
        binding.etPw.setOnFocusChangeListener(View.OnFocusChangeListener { view, focused ->
            if(focused){
                Log.e("AppTest","et name focused")
                binding.etPw.backgroundTintList = ContextCompat.getColorStateList(applicationContext, R.color.tuktalk_primary)
                //binding.etId.error = "error test"
            }
            else{
                Log.e("AppTest","et name focus x")
                binding.etPw.backgroundTintList = ContextCompat.getColorStateList(applicationContext, R.color.tuktalk_gray1)
            }
        })
        //

        // 비밀번호 확인 입력 시 edittext 색상 변경
        binding.etPwCheck.setOnFocusChangeListener(View.OnFocusChangeListener { view, focused ->
            if(focused){
                Log.e("AppTest","et name focused")
                binding.etPwCheck.backgroundTintList = ContextCompat.getColorStateList(applicationContext, R.color.tuktalk_primary)
                //binding.etId.error = "error test"
            }
            else{
                Log.e("AppTest","et name focus x")
                binding.etPwCheck.backgroundTintList = ContextCompat.getColorStateList(applicationContext, R.color.tuktalk_gray1)
            }
        })
        ////////////////////////////
        // 이름 - 글자 수 체크
        binding.etName.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                var nameText = binding.etName.text


                if(nameText.length >= 2){
                    binding.tvErrorName.visibility = View.GONE
                    // 모두 0 값을 주면 아이콘 사라진다!!
                    binding.etName.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_icon_correct,0)
                    viewModel.infoCorrectCheck(0,true)

                    NICKNAME = nameText.toString() // 이름(닉네임)
                    FIRST_LETTER = NICKNAME.slice(IntRange(0,0))
                }
                else{
                    binding.tvErrorName.visibility = View.VISIBLE
                    // 동적으로 edittext에 아이콘 생성!!
                    binding.etName.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_icon_error,0)
                    viewModel.infoCorrectCheck(0,false)
                }

                // 빈 칸 되었을 때
                if(nameText.length == 0){
                    binding.tvErrorName.visibility = View.GONE
                    binding.etName.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, 0,0)
                    viewModel.infoCorrectCheck(0,false)
                }
            }
            override fun afterTextChanged(p0: Editable?) {

            }
        })
        //

        // 아이디 - 이메일 형식 체크
        binding.etId.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                var idText = binding.etId.text
                var emailPattern = android.util.Patterns.EMAIL_ADDRESS

                if(viewModel.isIdAllCorrect.value == true){
                    viewModel.flag1 = true
                    viewModel.isIdAllCorrect.value = false // 중복확인 성공했어도 아이디 입력 칸 내용 변경되면 리셋 -> 다시 검사해야 함!
                    viewModel.infoCorrectCheck(4,false)
                }

                if(emailPattern.matcher(idText).matches()){
                    IsIdMatchEmail = true
                    binding.tvErrorId.visibility = View.GONE
                    binding.tvErrorId2.visibility = View.GONE // Id2는 중복 오류메세지!
                    // 모두 0 값을 주면 아이콘 사라진다!!
                    binding.etId.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, 0,0)
                    viewModel.infoCorrectCheck(1,true)
                }
                else{
                    IsIdMatchEmail = false
                    binding.tvErrorId.visibility = View.VISIBLE
                    binding.tvErrorId2.visibility = View.GONE // 형식 오류가 우선! / Id2는 중복 오류메세지!
                    // 동적으로 edittext에 아이콘 생성!!
                    binding.etId.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_icon_error,0)
                    viewModel.infoCorrectCheck(1,false)
                }

                // 빈 칸 되었을 때
                if(idText.length == 0){
                    IsIdMatchEmail = false
                    binding.tvErrorId.visibility = View.GONE
                    binding.etId.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, 0,0)
                    viewModel.infoCorrectCheck(1,false)
                }
            }
            override fun afterTextChanged(p0: Editable?) {

            }
        })
        //

        // 비밀번호 - 8자 이상인지 체크
        binding.etPw.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                var pwText = binding.etPw.text

                // 비밀번호 확인이랑 다르면 오류 메세지!!
                if(!pwText.toString().equals(binding.etPwCheck.text.toString()) && binding.etPwCheck.text.length != 0){
                    binding.tvErrorPwCheck.visibility = View.VISIBLE
                    // 동적으로 edittext에 아이콘 생성!!
                    binding.etPwCheck.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_icon_error,0)
                    viewModel.infoCorrectCheck(3,false)
                }

                if(pwText.length >= 8){
                    binding.tvErrorPw.visibility = View.GONE
                    // 모두 0 값을 주면 아이콘 사라진다!!
                    binding.etPw.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_icon_correct,0)
                    viewModel.infoCorrectCheck(2,true)

                    PASSWORD = pwText.toString()
                }
                else{
                    binding.tvErrorPw.visibility = View.VISIBLE
                    // 동적으로 edittext에 아이콘 생성!!
                    binding.etPw.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_icon_error,0)
                    viewModel.infoCorrectCheck(2,false)
                }

                // 빈 칸 되었을 때
                if(pwText.length == 0){
                    binding.tvErrorPw.visibility = View.GONE
                    binding.etPw.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, 0,0)
                    viewModel.infoCorrectCheck(2,false)
                }
            }
            override fun afterTextChanged(p0: Editable?) {

            }
        })
        //

        // 비밀번호 확인 - 위에서 입력한 비밀번호와 일치하는지 체크
        binding.etPwCheck.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                var pwCheckText = binding.etPwCheck.text

                if(pwCheckText.toString().equals(binding.etPw.text.toString())){
                    binding.tvErrorPwCheck.visibility = View.GONE
                    // 모두 0 값을 주면 아이콘 사라진다!!
                    binding.etPwCheck.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_icon_correct,0)
                    viewModel.infoCorrectCheck(3,true)
                }
                else{
                    binding.tvErrorPwCheck.visibility = View.VISIBLE
                    // 동적으로 edittext에 아이콘 생성!!
                    binding.etPwCheck.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_icon_error,0)
                    viewModel.infoCorrectCheck(3,false)
                }

                // 빈 칸 되었을 때
                if(pwCheckText.length == 0){
                    binding.tvErrorPwCheck.visibility = View.GONE
                    binding.etPwCheck.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, 0,0)
                    viewModel.infoCorrectCheck(3,false)
                }
            }
            override fun afterTextChanged(p0: Editable?) {

            }
        })
        /////////////////////////////////////////////////////////////////

        // id 중복 체크 - index 4
        binding.cardviewIdCheck.setOnClickListener {
            if(IsIdMatchEmail){
                viewModel.checkIdDuplicate(binding.etId.text.toString())
            }
            else{ // 이메일 형식 맞지 않는 상태에서 중복확인 눌렀을 때
                Toast.makeText(this,"아이디 형식이 올바르지 않습니다.", Toast.LENGTH_SHORT).show()
            }
        }

        // 약관 동의 체크 - index 5
        binding.ivCheckTerms.setOnClickListener {
            viewModel.termsCheck()
        }

        // 동의 영역
        viewModel.isTermsCheck.observe(this,{
            if(it){
                binding.ivCheckTerms.setImageResource(R.drawable.ic_checkbox_on)
            }
            else{
                binding.ivCheckTerms.setImageResource(R.drawable.ic_checkbox_off)
            }
        })


        // id 영역
        viewModel.isIdAllCorrect.observe(this,{
            if(it){ // 중복확인 까지 모두 성공
                binding.etId.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_icon_correct,0)
                ID = binding.etId.text.toString()

                Toast.makeText(this, "사용 가능한 아이디입니다.", Toast.LENGTH_SHORT).show()
            }
            else{
                if(viewModel.flag1){ // 중복확인 성공한 상태에서 내용 변경되는 경우
                    binding.etId.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, 0,0)
                    viewModel.flag1 = false
                }
                else{ // 중복확인 실패 경우
                    binding.etId.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_icon_error,0)
                    binding.tvErrorId2.visibility = View.VISIBLE // 중복 오류 문구 활성화
                }
            }
        })


        // 6가지 사항 입력 변화 체크
        viewModel.isAllInfoEnteredCorrectly.observe(this, Observer {
            if(it){ // 보라색 버튼 활성화
                binding.btnSignupCompleteInactive.visibility = View.INVISIBLE
                binding.btnSignupCompleteActive.visibility = View.VISIBLE // 활성화
            }
            else{
                binding.btnSignupCompleteInactive.visibility = View.VISIBLE
                binding.btnSignupCompleteActive.visibility = View.INVISIBLE
            }
        })

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // 가입완료 클릭 시
        binding.btnSignupCompleteActive.setOnClickListener {

            setRandomProfileImageColor()

            Log.e("AppTest", "회원가입 정보  ${Constants.SELECT_CATEGORY_LIST}, 이름:${NICKNAME} 아이디:${ID} 비밀번호:${PASSWORD}" +
                    " 역할:${ROLE} 닉네임 첫 글자:${FIRST_LETTER} 프로필이미지랜덤색상:${PROFILE_IMAGE_COLOR} 프로필url:${PROFILE_URL}")
            // viewmodel에 기입 정보 넘기기
            var userSignUpRequestDto = UserSignUpRequestDto(Constants.SELECT_CATEGORY_LIST, ID, NICKNAME,
                PASSWORD, ROLE, PROFILE_IMAGE_COLOR, FIRST_LETTER)
            viewModel.signUpClick(userSignUpRequestDto)
        }

        viewModel.isSignUpSuccess.observe(this,{
            if(it){ // 가입 완료 처리 시
                val intent = Intent(this, SignUpCompleteActivity::class.java)
                intent.putExtra("isMentee", IsMentee)
                startActivity(intent)
            }
        })

        ////////////////////////

        // 약관 확인하기
        binding.tvCheckTerms.setOnClickListener {
            val intent = Intent(this, TermsActivity::class.java)
            startActivity(intent)
        }

        /////////////////////////////
        // loading progressbar 처리
        viewModel.progressBarVisibility.observe(this,{
            if(it)
                binding.loadingProgressBar.visibility = View.VISIBLE
            else
                binding.loadingProgressBar.visibility = View.INVISIBLE
        })


        ////////////////////////////////////////////////////




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

    override fun onBackPressed() {
        //super.onBackPressed()
        // 입력 과정에서 뒤로 가기 누를 시 다이얼로그 나타남
        var dialogVeiw = BreakAwayDialogFragment()
        dialogVeiw.show(supportFragmentManager, "AppTest")
    }

    override fun onPause() {  // 앱 이탈 시 다이얼로그 나타남
        super.onPause()
        var dialogVeiw = BreakAwayDialogFragment()
        dialogVeiw.show(supportFragmentManager, "AppTest")
    }

    fun setRandomProfileImageColor(){
        var random = (0..4).random()
        PROFILE_IMAGE_COLOR = profileImgColorList[random]
    }
}