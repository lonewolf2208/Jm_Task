package com.example.jumpingmindstask.utils

sealed class Resource <T> (
    val data : T ? =  null,
    val error : Throwable ?= null
){
    class Success<T>(data: T?=null) : Resource<T>(data=data)
    class Loading<T> : Resource<T>()
    class Error<T>(throwable: Throwable, data: T? = null) : Resource<T>(data, throwable)
}
