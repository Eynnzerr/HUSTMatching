package com.example.hustmatching.retrofit

class DataResponse<T>(code: Int, msg: String, data: Data<T>) {
    class Data<T>(data: T)
}