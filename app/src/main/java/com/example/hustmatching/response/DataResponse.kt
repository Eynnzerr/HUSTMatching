package com.example.hustmatching.response

class DataResponse<T>(val data: T, code: Int, msg: String) : Response(code, msg)