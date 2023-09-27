package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class PlayListDatas(val userId:String,val playListName:String,val songId:String)