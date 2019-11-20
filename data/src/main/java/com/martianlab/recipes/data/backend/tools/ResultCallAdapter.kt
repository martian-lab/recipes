package com.martianlab.recipes.tools.backend.tools


import okhttp3.Request
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.lang.reflect.Type
import com.martianlab.recipes.entities.Result

abstract class CallDelegate<TIn, TOut>(
    protected val proxy: Call<TIn>
) : Call<TOut> {
    override fun execute(): Response<TOut> = throw NotImplementedError()
    override final fun enqueue(callback: Callback<TOut>) = enqueueImpl(callback)
    override final fun clone(): Call<TOut> = cloneImpl()

    override fun cancel() = proxy.cancel()
    override fun request(): Request = proxy.request()
    override fun isExecuted() = proxy.isExecuted
    override fun isCanceled() = proxy.isCanceled

    abstract fun enqueueImpl(callback: Callback<TOut>)
    abstract fun cloneImpl(): Call<TOut>
}

class ResultCall<T>(proxy: Call<T>) : CallDelegate<T, Result<T>>(proxy) {
    override fun enqueueImpl(callback: Callback<Result<T>>) = proxy.enqueue(object: Callback<T> {
        override fun onResponse(call: Call<T>, response: Response<T>) {

            //println("RECIPES: call response=" + response )
            val code = response.code()
            val result = if (code in 200 until 300) {
                val body = response.body()
                //println("RECIPES: call body=" + body )
                Result.Success(body)
            } else {
                Result.Failure(code)
            }

            callback.onResponse(this@ResultCall, Response.success(result))
        }

        override fun onFailure(call: Call<T>, t: Throwable) {
            println("RECIPES: call Throwable=" + t )
            val result = if (t is IOException) {
                Result.NetworkError
            } else {
                Result.Failure(null)
            }

            callback.onResponse(this@ResultCall, Response.success(result))
        }
    })

    override fun cloneImpl() = ResultCall(proxy.clone())
}

class ResultAdapter(
    private val type: Type
): CallAdapter<Type, Call<Result<Type>>> {
    override fun responseType() = type
    override fun adapt(call: Call<Type>): Call<Result<Type>> = ResultCall(call)
}