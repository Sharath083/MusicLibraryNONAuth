package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class AddToPlayList(val uuid:String,val song:String?,val playList:String?)
@Serializable
data class RemoveFromPlayList(val uuid:String,val song:String?,val playList:String?)
