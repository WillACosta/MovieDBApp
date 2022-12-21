package com.will.moviedbapp.resources.utils

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer

fun MockWebServer.enqueueResponse(body: String, code: Int) {
    enqueue(
        MockResponse()
            .setResponseCode(code)
            .setBody(body)
    )
}
