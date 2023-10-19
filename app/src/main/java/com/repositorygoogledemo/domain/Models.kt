package com.repositorygoogledemo.domain

import com.repositorygoogledemo.util.smartTruncate

/*
Domain objects are plain Kotlin data classes,
These are the objects that should be displayed on screen, or manipulated by the app.
 */
data class DevByteVideo(val title: String,
                        val description: String,
                        val url: String,
                        val updated: String,
                        val thumbnail: String) {

    /**
     * Short description is used for displaying truncated descriptions in the UI
     */
    val shortDescription: String
        get() = description.smartTruncate(200)
}