package com.feeds.models

import java.io.Serializable

data class Songs(
    val feed: Feed
):Serializable

data class Feed(
    val author: Author,
    val copyright: String,
    val country: String,
    val icon: String,
    val id: String,
    val links: List<Link>,
    val results: List<Result>,
    val title: String,
    val updated: String
):Serializable

data class Author(
    val name: String,
    val uri: String
):Serializable

data class Link(
    val alternate: String,
    val self: String
):Serializable

data class Result(
    val artistId: String,
    val artistName: String,
    val artistUrl: String,
    val artworkUrl100: String,
    val contentAdvisoryRating: String,
    val copyright: String,
    val genres: List<Genre>,
    val id: String,
    val kind: String,
    val name: String,
    val releaseDate: String,
    val url: String
):Serializable

data class Genre(
    val genreId: String,
    val name: String,
    val url: String
):Serializable