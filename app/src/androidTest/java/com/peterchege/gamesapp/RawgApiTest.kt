package com.peterchege.gamesapp

import android.content.Context
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.platform.app.InstrumentationRegistry
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.peterchege.gamesapp.data.api.RawgApi
import com.peterchege.gamesapp.util.Constants
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.CoreMatchers
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.InputStream
import java.net.HttpURLConnection

class RawgApiTest {
    private var context: Context? = null
    private var mockWebServer = MockWebServer()
    private lateinit var rawgApi: RawgApi

    @OptIn(ExperimentalSerializationApi::class)
    @Before
    fun setup() {
        mockWebServer.start()

        val loggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        context = InstrumentationRegistry.getInstrumentation().targetContext


        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        val contentType = "application/json".toMediaType()
        val converterFactory = Json.asConverterFactory(contentType)

        rawgApi = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(mockWebServer.url("/"))
            .build()
            .create(RawgApi::class.java)

        val jsonStream: InputStream = context!!.assets.open("games_platform.json")
        val jsonBytes: ByteArray = jsonStream.readBytes()


        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(String(jsonBytes))
        mockWebServer.enqueue(response)

    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun test_api_response(): Unit = runBlocking {

        val data = rawgApi.getPlatforms(page = 1, pageSize = 10)
        Assert.assertEquals(data.results.size, 10)


    }

    
}