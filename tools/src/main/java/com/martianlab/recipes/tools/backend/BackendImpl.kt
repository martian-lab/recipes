package com.martianlab.recipes.tools.backend

import com.google.gson.GsonBuilder
import com.martianlab.recipes.domain.BackendApi
import com.martianlab.recipes.entities.Recipe
import com.martianlab.recipes.tools.backend.mapper.toRecipeList
import com.utkonos.utkomobile.core_network_impl.data.util.CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.martianlab.recipes.entities.Result

object  BackendImpl : BackendApi {

    private const val URL = "http://www.utkonos.ru/api/rest/?"
    val httpLoggingInterceptor = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    val okHttpClient = OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build()


    private val caller = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .addCallAdapterFactory(CallAdapterFactory())
        //.client(okHttpClient)
        .build()
        .create(Caller::class.java)

    override suspend fun recipeSearch(
        categoryId: Long,
        recipeId: Long,
        count: Int,
        offset: Int
    ): Result<List<Recipe>> {
        val query = "{\"Head\":{\"Method\":\"RecipeSearch\",\"RequestId\":\"2f34f026c8c16373395610b5d36e92c7\",\"MarketingPartnerKey\":\"123123123\",\"DeviceId\":\"1572680090\",\"Created\":\"Fri, 15 Nov 2019 09:37:08 GMT\",\"Client\":\"\",\"AdvertisingId\":\"\",\"AppsFlyerId\":\"\"},\"Body\":{\"Count\":\"$count\",\"Id\":\"\",\"CategoryId\":\"\",\"Offset\":\"$offset\",\"Return\":{\"Cooking\":\"1\",\"Ingredient\":\"1\",\"Goods\":\"1\",\"BestRecipes\":\"\",\"Comments\":\"1\",\"Seo\":\"\"}}}"
        return caller.recipeSearch(
            request= query,
            method = "RecipeSearch",
            androidId = "000"
        )
        .map {
           it.body.toRecipeList()
        }
    }

}


private fun <From, To>  Result<From>.map(entityMapper: (From) -> To): Result<To> =
    when (this) {
        is Result.Success<From> -> Result.Success<To>(data?.let(entityMapper) )
        is Result.Failure -> Result.Failure(statusCode)
        is Result.NetworkError -> Result.NetworkError
    }