package com.example.api_service.pagging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.api_service.service.DiscoverMovieService
import com.example.common.entity.discover_movie.Result as MoviesResult

class DiscoverMovieDataSource(
    private val discoverMovieService: DiscoverMovieService,
    private val genreIds: Array<String>
) : PagingSource<Int, MoviesResult>() {
    override fun getRefreshKey(state: PagingState<Int, MoviesResult>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MoviesResult> {
        val page = params.key ?: 1
        val prevPage = if (page == 1) null else page - 1
        try {
            val data = discoverMovieService.getMovieDiscover(genreIds.joinToString(separator = ","), page)
            if(data.isSuccessful){
                data.body()?.let {
                    val nextPage = if (it.results.isEmpty()) null else page + 1
                    return LoadResult.Page(it.results, prevPage, nextPage)
                } ?: kotlin.run{
                    return LoadResult.Page(arrayListOf(),prevPage,null)
                }
            } else{
               return LoadResult.Error(Exception("Error Backed : ${data.code()}"))
            }
        } catch (e : Exception){
            return LoadResult.Error(e)
        }
    }

    companion object{
        fun createPager(
            pageSize: Int,
            discoverMovieService: DiscoverMovieService,
            genreIds: Array<String>
        ) = Pager(
            PagingConfig(pageSize), null
        ){
            DiscoverMovieDataSource(discoverMovieService,genreIds)
        }
    }

}