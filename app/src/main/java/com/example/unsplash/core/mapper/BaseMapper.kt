package com.example.unsplash.core.mapper

interface BaseMapper<in A, out B> {

    fun map(type: A?): B
}