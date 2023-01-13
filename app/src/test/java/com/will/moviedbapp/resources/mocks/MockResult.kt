package com.will.moviedbapp.resources.mocks

object MockResult {
    val movieListResponseSuccess = Result.success(MockApiData.movieListResponse)
    val genreListResponseSuccess = Result.success(MockApiData.genreListResponse)
    val movieDetailResponseSuccess = Result.success(MockApiData.movieDetailResponse)
}
