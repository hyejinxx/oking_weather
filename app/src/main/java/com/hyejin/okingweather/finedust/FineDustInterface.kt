package com.hyejin.okingweather.finedust

import retrofit2.http.GET
import retrofit2.http.Query

interface FineDustInterface {

    @GET("getMsrstnAcctoRltmMesureDnsty?serviceKey=G77F3bZzXJWT%2B%2BAQafXcdQiaLmn2RlsZb9szmlWWCRJqPLN9O1U6w7tU4ywNe%2BxNBw59Jv3wOhKQh6hjZfr6yA%3D%3D")
    fun getFineDust(
        @Query("numOfRows") num_of_rows: Int,
        @Query("pageNo") page_no: Int,
        @Query("returnType") data_type: String,
        @Query("stationName") station_name : String,
        @Query("dataTerm") data_term : Int,
        @Query("ver") ver : Double
        ): retrofit2.Call<FineDust>
}