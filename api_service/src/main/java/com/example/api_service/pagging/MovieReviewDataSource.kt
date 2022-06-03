package com.example.api_service.pagging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.api_service.service.MovieReviewService
import com.example.common.entity.movie.movie_review.Result as ReviewResult

class MovieReviewDataSource(
    private val movieReviewService: MovieReviewService,
    private val movieId: Int
) : PagingSource<Int,ReviewResult>(){
    override fun getRefreshKey(state: PagingState<Int, ReviewResult>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ReviewResult> {
        val page = params.key ?: 1
        val prevPage = if (page == 1) null else page - 1
        try {
            val data = movieReviewService.getReviews(movieId,page)
            if(data.isSuccessful){
                data.body()?.let {
                    val nextPage = if (it.results.isEmpty()) null else page + 1
                    return LoadResult.Page(it.results, prevPage, nextPage)
                } ?: kotlin.run{
                    return LoadResult.Page(arrayListOf(),prevPage,null)
                }
            }else{
                return LoadResult.Error(Exception("Error Backed : ${data.code()}"))
            }
        }catch (e : Exception){
            return LoadResult.Error(e)
        }
    }

    companion object{
        fun createPager(
            pageSize: Int,
            movieReviewService: MovieReviewService,
            movieId: Int
        ) = Pager(
            PagingConfig(pageSize),null
        ){
            MovieReviewDataSource(movieReviewService,movieId)
        }
    }
}