package com.directdev.portal.network

import com.directdev.portal.model.*
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*
import rx.Single

interface DataService {
    
    @FormUrlEncoded
    @Headers("Referer: https://binusmaya.binus.ac.id/login/", "Origin: https://binusmaya.binus.ac.id")
    @POST("https://binusmaya.binus.ac.id/login/sys_login.php")
    fun signIn(@Field("uid") uid: String,
               @Field("pass") pass: String,
               @Header("Cookie") cookie: String,
               @Field("ctl00\$ContentPlaceHolder1\$SubmitButtonBM") button: String = "Login")
            : Single<Response<String>>

    @Headers("Referer: https://binusmaya.binus.ac.id/newStudent/")
    @GET("student/profile/profileStudent")
    fun getProfile(@Header("Cookie") cookie: String): Single<ResponseBody>

    @Headers("Referer: https://binusmaya.binus.ac.id/newStudent/")
    @GET("financial/getFinancialSummary")
    fun getFinances(@Header("Cookie") cookie: String): Single<List<FinanceModel>>

    @Headers("Referer: https://binusmaya.binus.ac.id/newStudent/index.html")
    @GET("student/class_schedule/classScheduleGetStudentClassSchedule")
    fun getSessions(@Header("Cookie") cookie: String): Single<List<SessionModel>>

    @Headers("Referer: https://binusmaya.binus.ac.id/newstudent/")
    @POST("newExam/Schedule/getOwnScheduleStudent")
    fun getExams(@Body data: ExamRequestBody, @Header("Cookie") cookie: String): Single<List<ExamModel>>

    @Headers("Referer: https://binusmaya.binus.ac.id/newstudent/")
    @POST("scoring/ViewGrade/getStudentScore/{term}")
    fun getGrades(@Path("term") term: String, @Header("Cookie") cookie: String): Single<GradeModel>

    @Headers("Referer: https://binusmaya.binus.ac.id/newstudent/")
    @POST("https://binusmaya.binus.ac.id/services/ci/index.php/scoring/ViewGrade/getPeriodByBinusianId")
    fun getTerms(@Header("Cookie") cookie: String): Single<List<TermModel>>
}
