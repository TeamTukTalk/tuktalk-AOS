package com.example.tuktalk.presentation.signup.info.breakaway

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.example.tuktalk.common.Constants
import com.example.tuktalk.databinding.DialogRegistinfoBreakawayBinding
import com.example.tuktalk.presentation.login.LoginActivity
import com.example.tuktalk.presentation.signup.SelectRoleActivity

class BreakAwayDialogFragment: DialogFragment() {
        private lateinit var binding: DialogRegistinfoBreakawayBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //isCancelable = false // 다이얼로그창 밖 또는 뒤로가기 버튼 클릭 시 다이얼로그 꺼지지 않음
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DialogRegistinfoBreakawayBinding.inflate(inflater, container, false)

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)

        // 다이얼로그 외부 터치 시 사라짐 방지 / '계속 작성' 을 통해 닫을 수 있음
        dialog!!.setOnShowListener(object : DialogInterface.OnShowListener{
            override fun onShow(p0: DialogInterface?) {
                dialog!!.setCancelable(false)
            }
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //다이얼로그 크기 설정
        // 스크린 크기
        var wm : WindowManager = getContext()?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        var screen : Display = wm.getDefaultDisplay()

        var size = Point()
        screen.getRealSize(size)
        var height = size.y
        var width = size.x

        //다이얼로그 크기
        binding.clDialog.layoutParams.width = width * 308 / 360
        binding.clDialog.layoutParams.height = width * 171 / 360

        //////////////////

        // 계속 작성 클릭 시 다이얼로그 닫히고 작성과정 유지
        binding.clContinue.setOnClickListener {
            val fragmentManager = (activity as AppCompatActivity).supportFragmentManager
            fragmentManager.beginTransaction().remove(this).commit()
            fragmentManager.popBackStack()
        }

        // 나가기 버튼 클릭 시
        binding.clExit.setOnClickListener {
            //activity?.finish()
            Log.e("AppTest","return to login activity")
            val intent = Intent(activity, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP   // 정보입력 화면에서 그전 액티비티 스택들 모두 제거 후 바로 로그인 액티비티로 이동!

            // 카테고리 선택 초기화
            Constants.SELECT_CATEGORY_LIST.clear()

            startActivity(intent)
        }
    }
}