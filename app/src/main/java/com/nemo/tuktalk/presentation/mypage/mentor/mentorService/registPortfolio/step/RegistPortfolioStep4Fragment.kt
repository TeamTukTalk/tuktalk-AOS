package com.nemo.tuktalk.presentation.mypage.mentor.mentorService.registPortfolio.step

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.nemo.tuktalk.R
import com.nemo.tuktalk.common.utils.HorizontalItemDecorator
import com.nemo.tuktalk.databinding.FragmentMentorRegistPortfolioStep4Binding
import com.nemo.tuktalk.domain.model.mypage.mentor.portfolio.UploadPreviewImage
import com.nemo.tuktalk.presentation.mypage.mentor.mentorService.registPortfolio.RegistPortfolioActivity
import com.nemo.tuktalk.presentation.mypage.mentor.mentorService.registPortfolio.RegistPortfolioViewModel
import com.nemo.tuktalk.presentation.mypage.mentor.mentorService.registPortfolio.adapter.UploadPreviewImageRVadpater
import okio.IOException
import org.koin.android.viewmodel.ext.android.sharedViewModel
import java.io.File

class RegistPortfolioStep4Fragment: Fragment() {

    private lateinit var binding : FragmentMentorRegistPortfolioStep4Binding
    private val viewModel : RegistPortfolioViewModel by sharedViewModel()

    val READ_REQUEST_CODE_IAMGE: Int = 2
    val READ_REQUEST_CODE_PDF: Int = 3

    var encodedPdf = ""

    var PDF_FILE_NAME = ""

    lateinit var rvAdapter: UploadPreviewImageRVadpater
    private var testDataSet = mutableListOf<UploadPreviewImage>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.e("AppTest", "RegistPortfolioStep4Fragment/ mentor regist portfolio step4 fragment onCreateView")

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mentor_regist_portfolio_step4, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 미리보기 이미지 Recycler View 설정
        rvAdapter = UploadPreviewImageRVadpater(testDataSet)
        binding.rvPreviewImage.layoutManager = LinearLayoutManager(context).also {
            it.orientation = LinearLayoutManager.HORIZONTAL  // 가로 방향 recyclerview
        }
        binding.rvPreviewImage.adapter = rvAdapter
        binding.rvPreviewImage.addItemDecoration(HorizontalItemDecorator(12))

        testDataSet.apply {
            add(UploadPreviewImage("",1))  // empty 아이템
            add(UploadPreviewImage("",2))  // 사진 개수 표시 및 클릭 시 갤러리 접근 아이템
            add(UploadPreviewImage("", 3))  // 업로드 사진 미리보기 뷰 아이템

        }
        rvAdapter.updateList(testDataSet)


        // 포트폴리우 추가시 기기 저장소 접근
        val intent_pdf = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "application/pdf"   // pdf 선택하는 경우!!
        }

        binding.tvUploadPdf.setOnClickListener {  // '추가하기' 텍스트 터치 시 pdf 파일 고르는 화면으로 전환
            startActivityForResult(intent_pdf, READ_REQUEST_CODE_PDF)
        }


        ////////////////////////////////////////////////////////////////////////////////////////////

        // 이미지 추가시 기기 저장소 접근
        val intent_image = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "image/*"
        }




        /////////////////////////////////////////////////////////////////////////////
        // 다음 버튼 -> 현재는 그냥 넘어가게 설정
        binding.btnGotoStep5Active.setOnClickListener {

            Log.e("AppTest","RegistPortfolioStep4Fragment/ go to step5")

            (activity as RegistPortfolioActivity).goToStep5()
        }


    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, resultData: Intent?) {
        super.onActivityResult(requestCode, resultCode, resultData)

        if(requestCode == READ_REQUEST_CODE_PDF && resultCode == Activity.RESULT_OK){  // 포트폴리오 pdf 파일 선택 시

            resultData?.data?.also{ uri ->
                Log.e("AppTest", "Uri : ${uri}")

                Log.e("AppTest", "uri path : ${uri.path}")
                getFileMetaData(uri)

                // 파일 업로드 통신하기
                // uri를 파일형태로 만들기?!
                try{
                    var inputStream = requireActivity().contentResolver.openInputStream(uri)
                    var pdfInbyte = ByteArray(inputStream!!.available())
                    inputStream.read(pdfInbyte)

                    encodedPdf = Base64.encodeToString(pdfInbyte, Base64.DEFAULT)
                    Log.e("AppTest", "encodedpdf : ${encodedPdf}")



                    // 통신하기! -> 현재 상태는 오류 ㅜㅜ
                    //viewModel.uploadPdfFile(encodedPdf)


                }
                catch (e : IOException){
                    e.printStackTrace()
                }

            }



        }
    }


    @SuppressLint("Range")
    fun getFileMetaData(uri: Uri){
        val cursor: Cursor? = requireActivity().contentResolver.query( uri, null, null, null, null, null)
        cursor?.use {
            if (it.moveToFirst()) {
                val displayName: String =
                        it.getString(it.getColumnIndex(OpenableColumns.DISPLAY_NAME))  // 1
                Log.e("AppTest", "Display Name: $displayName")  // 파일명!!

                PDF_FILE_NAME = displayName  // pdf 파일 추가 후 해당 파일명 보이게 하기!!

                val sizeIndex: Int = it.getColumnIndex(OpenableColumns.SIZE)  // 2
                val size: String = if (!it.isNull(sizeIndex)) {
                    it.getString(sizeIndex)
                } else {
                    "Unknown"
                }
                Log.e("AppTest", "Size: $size")   // size = byte 단위!
            }
        }
    }



}