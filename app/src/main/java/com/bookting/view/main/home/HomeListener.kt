package com.bookting.view.main.home

interface HomeListener {
    fun onBest()
    fun onNew()
    fun onRecommend(bookId : Int)
}