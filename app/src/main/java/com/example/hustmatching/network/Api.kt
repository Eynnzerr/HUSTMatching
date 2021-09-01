package com.example.hustmatching.network

object Api {
    const val BASE_URL:String = "http://143.198.144.245:3001/HustMatcher_war/"
    //const val BASE_URL:String = "http://192.168.2.161:8080/HustMatcher_war_exploded/"
    const val LOGIN_PWD_URL:String = "user/login/pwd"
    const val LOGIN_EMAIL_URL:String = "user/login/email"
    const val REGISTER_URL:String = "user/register/info"
    const val SEND_VERIFY_URL:String = "user/auth/send"
    const val VERIFY_URL:String = "user/register/auth"
    const val SEARCH_ITEM_URL:String = "seek/object"//发送一篇发布
    const val SEARCH_PERSON_URL:String ="seek/person"
    const val PUBLISH_URL:String ="seek/publish"
    const val MATCH_URL:String ="seek/match"
    const val GET_POSTS_URL:String = "seek/myposts"
    const val TEST_URL :String = "/test/token"
}