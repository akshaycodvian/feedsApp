package com.feeds.rest.exceptions

import java.io.IOException

class NoNetworkException: IOException() {

    override val message: String?
        get() = "No internet connection"
}