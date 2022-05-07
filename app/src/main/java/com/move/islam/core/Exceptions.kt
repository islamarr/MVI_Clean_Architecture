package com.move.islam.core

import java.io.IOException

class ApiException(message: String) : IOException(message) //TODO do you need this?
class NoInternetException(message: String) : IOException(message)