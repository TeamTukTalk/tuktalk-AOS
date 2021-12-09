package com.nemo.tuktalk.presentation.mypage.mentor.mentorInfo.tab

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.nemo.tuktalk.R
import com.nemo.tuktalk.databinding.FragmentMentorinfoInfoTabBinding
import com.nemo.tuktalk.presentation.mypage.mentor.mentorInfo.MentorInfoViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel

class InfoTabFragment: Fragment() {

    private lateinit var binding : FragmentMentorinfoInfoTabBinding
    private val viewModel : MentorInfoViewModel by sharedViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        Log.e("AppTest", "InfoTabFragment/ onCreateView")

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mentorinfo_info_tab, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.e("AppTest", "InfoTabFragment/ onViewCreated")

        // 전문분야 관련
        val tvSubSpecialty1 : TextView = view.findViewById(R.id.tv_subspeciality_1)
        val tvSubSpecialty2 : TextView = view.findViewById(R.id.tv_subspeciality_2)
        val tvSubSpecialty3 : TextView = view.findViewById(R.id.tv_subspeciality_3)

        val subSpecialty1_space : View = view.findViewById(R.id.view_subspeciality_1_space)
        val subSpecialty2_space : View = view.findViewById(R.id.view_subspeciality_2_space)
        val subSpecialty3_space : View = view.findViewById(R.id.view_subspeciality_3_space)

        val pointSubSpecialty1 : View = view.findViewById(R.id.view_point_subspeciality_1)
        val pointSubSpecialty2 : View = view.findViewById(R.id.view_point_subspeciality_2)
        val pointSubSpecialty3 : View = view.findViewById(R.id.view_point_subspeciality_3)

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // ViewModel에서 정보 먼저 가져오기!!!
        viewModel.IsGetMentorDetialInfoSuccess.observe(viewLifecycleOwner, {
            if(it){
                var detailIntroduction = viewModel.DetailedIntroduction
                binding.tvDetailIntroduction.text = detailIntroduction.replace(" ", "\u00A0") // 리뷰 내용

                binding.tvCompanyName.text = viewModel.CompanyName  // 회사이름
                binding.tvTaskName.text = viewModel.Specialty // 역할?
                binding.tvPositionName.text = viewModel.Position

                var careerText = ""
                if(viewModel.Career.years > 0)
                    careerText += viewModel.Career.years.toString() + "년 "
                if(viewModel.Career.months > 0)
                    careerText += viewModel.Career.months.toString() + "개월"

                binding.tvCareer.text = careerText  // 경력(기간)

                val tvCareerDescription : TextView = view.findViewById(R.id.tv_careerDescription)
                tvCareerDescription.text = viewModel.CareerDescription  // 경력/경험 설명

                // 전문분야
                if(viewModel.SubSpecialties.size == 1){
                    subSpecialty1_space.visibility = View.VISIBLE
                    tvSubSpecialty1.visibility = View.VISIBLE
                    pointSubSpecialty1.visibility = View.VISIBLE
                    tvSubSpecialty1.text = viewModel.SubSpecialties[0].subSpecialtyValue
                }
                else if(viewModel.SubSpecialties.size == 2){
                    subSpecialty1_space.visibility = View.VISIBLE
                    tvSubSpecialty1.visibility = View.VISIBLE
                    pointSubSpecialty1.visibility = View.VISIBLE
                    tvSubSpecialty1.text = viewModel.SubSpecialties[0].subSpecialtyValue

                    subSpecialty2_space.visibility = View.VISIBLE
                    tvSubSpecialty2.visibility = View.VISIBLE
                    pointSubSpecialty2.visibility = View.VISIBLE
                    tvSubSpecialty2.text = viewModel.SubSpecialties[1].subSpecialtyValue
                }
                else if(viewModel.SubSpecialties.size == 3){
                    subSpecialty1_space.visibility = View.VISIBLE
                    tvSubSpecialty1.visibility = View.VISIBLE
                    pointSubSpecialty1.visibility = View.VISIBLE
                    tvSubSpecialty1.text = viewModel.SubSpecialties[0].subSpecialtyValue

                    subSpecialty2_space.visibility = View.VISIBLE
                    tvSubSpecialty2.visibility = View.VISIBLE
                    pointSubSpecialty2.visibility = View.VISIBLE
                    tvSubSpecialty2.text = viewModel.SubSpecialties[1].subSpecialtyValue

                    subSpecialty3_space.visibility = View.VISIBLE
                    tvSubSpecialty3.visibility = View.VISIBLE
                    pointSubSpecialty3.visibility = View.VISIBLE
                    tvSubSpecialty3.text = viewModel.SubSpecialties[2].subSpecialtyValue

                }

                val tvTag : TextView = view.findViewById(R.id.tv_tag)
                var hashTagText = ""
                viewModel.HashTags.forEach{
                    hashTagText += "#" + it.hashTag + " "
                }
                tvTag.text = hashTagText

            }
            else{
                Log.e("AppTest", "InfoTabFragment/ 상세 정보 조회 실패")
            }
        })


        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    }
}