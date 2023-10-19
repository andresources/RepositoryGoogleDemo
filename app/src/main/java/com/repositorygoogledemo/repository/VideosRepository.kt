package com.repositorygoogledemo.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.repositorygoogledemo.database.DatabaseVideo


import com.repositorygoogledemo.database.VideosDatabase
import com.repositorygoogledemo.database.asDomainModel
import com.repositorygoogledemo.domain.DevByteVideo
import com.repositorygoogledemo.network.DevByteNetwork
import com.repositorygoogledemo.network.asDatabaseModel

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Repository for fetching devbyte videos from the network and storing them on disk
 */
class VideosRepository(private val database: VideosDatabase) {

    val videos: LiveData<List<DevByteVideo>> = database.videoDao.getVideos().map {
        it.asDomainModel()
    }

    suspend fun refreshVideos() {
        withContext(Dispatchers.IO) {
            val playlist = DevByteNetwork.devbytes.getPlaylist()
            database.videoDao.insertAll(playlist.asDatabaseModel())
        }
    }

}