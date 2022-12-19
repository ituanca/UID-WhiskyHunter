package com.example.whiskyhunter.models

data class DistilleriesInfo(
    var name: String,
    val slug: String,
    var country: String,
    var whiskybase_whiskies: String,
    var whiskybase_votes: String,
    var whiskybase_rating: String
):java.io.Serializable{}
