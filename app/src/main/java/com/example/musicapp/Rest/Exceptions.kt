package com.example.musicapp.Rest

class NullPopResponse(message: String = "Pop response is null") : Exception(message)
class NullClassicResponse(message: String = "Classic response is null") : Exception(message)
class NullRockResponse(message: String = "Rock response is null") : Exception(message)
class FailureResponse(message: String?) : Exception(message)