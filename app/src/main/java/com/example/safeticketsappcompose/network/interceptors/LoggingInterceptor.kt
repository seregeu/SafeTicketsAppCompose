package com.example.safeticketsappcompose.network.interceptors

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody
import okio.Buffer
import java.io.IOException
import java.nio.charset.Charset

class LoggingInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestBody = request.body()

        val requestBuffer = Buffer()
        requestBody?.writeTo(requestBuffer)

        val requestCharset = requestBody?.contentType()?.charset(Charset.forName("UTF-8"))
        val requestString = requestBuffer.readString(requestCharset)

        // Log request details
        Log.d("RequestIntercept","Sending request ${request.method()} ${request.url()} with body: $requestString")

        // Proceed with the request
        val response = chain.proceed(request)

        // Log response details
        val responseBody = response.body()
        val responseSource = responseBody?.source()
        responseSource?.request(Long.MAX_VALUE) // Buffer the entire body
        val responseBuffer = responseSource?.buffer
        val responseCharset = responseBody?.contentType()?.charset(Charset.forName("UTF-8"))
        val responseString = responseBuffer?.clone()?.readString(responseCharset)

        Log.d("RequestIntercept","Received response for ${response.request().url()} with body: $responseString")

        return response
    }
}
