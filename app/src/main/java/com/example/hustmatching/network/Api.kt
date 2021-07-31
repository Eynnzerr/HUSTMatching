package com.example.hustmatching.network

object Api {
    const val BASE_URL:String = "http://123.56.0.167:8080/"
    const val LOGIN_PWD_URL:String = "api/user/login/pwd"
    const val LOGIN_EMAIL_URL:String = "api/user/login/email"
    const val REGISTER_URL:String = "api/user/register/info"
    const val SEND_VERIFY_URL:String = "api/user/auth/send"
    const val VERIFY_URL:String = "api/user/register/auth"
    const val SEARCH_ITEM_URL:String = "api/seek/object"//发送一篇发布
    const val SEARCH_PERSON_URL:String ="/api/seek/person"
    const val MATCH_URL:String ="/api/seek/match"
    const val TEST_URL :String = "/test/token"
}