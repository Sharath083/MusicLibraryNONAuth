package com.example.routes

import com.example.model.*
import com.example.service.UserServices
import com.example.utils.appconstant.APIEndPoints.ADD_TO_PLAYLIST
import com.example.utils.appconstant.APIEndPoints.DELETE_ACCOUNT
import com.example.utils.appconstant.APIEndPoints.DELETE_PLAYLIST
import com.example.utils.appconstant.APIEndPoints.FILTER_BY_ARTIST
import com.example.utils.appconstant.APIEndPoints.REMOVE_FROM_PLAYLIST
import com.example.utils.appconstant.APIEndPoints.USER_LOGIN
import com.example.utils.appconstant.APIEndPoints.USER_REGISTRATION
import com.example.utils.appconstant.APIEndPoints.USER_ROUTES
import com.example.utils.appconstant.APIEndPoints.VIEW_PLAYLIST
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import java.util.UUID

fun Route.userRouting(){
    val userServices: UserServices by inject()
    route(USER_ROUTES){
        post(USER_REGISTRATION) {
            val regValues=call.receive<UserRegistration>()
            userServices.userRegistrationService(regValues)
                .apply {
                    call.respond(HttpStatusCode.Created,this) }
        }
        post (USER_LOGIN){
            val input=call.receive<UserLogin>()
            userServices.userLoginCheckService(input)
                .apply {
//                    userServices.getUserIdService(input.name!!).apply {
//                        call.sessions.set(UserId(this))
//                    }
                    call.respond(HttpStatusCode.OK, this)
                }
        }
        get(FILTER_BY_ARTIST) {
            val input = call.receive<ArtistData>()
//            call.sessions.get<UserId>()
//                ?:throw SessionDataIsNullException(SESSION_MESSAGE,HttpStatusCode.Unauthorized)
            userServices.filterByArtistService(input.artist!!)
                .apply {
                    call.respond(HttpStatusCode.OK,this) }
        }

        post(ADD_TO_PLAYLIST) {

            val input = call.receive<AddToPlayList>()
//            val sessionData = call.sessions.get<UserId>()
//                ?: throw SessionDataIsNullException(SESSION_MESSAGE, HttpStatusCode.Unauthorized)
            val uuid=UUID.fromString(input.uuid)
            userServices.addSongToPlayListService(input,uuid)
                .apply {
                    call.respond(HttpStatusCode.OK, this)
                }

        }
        post(REMOVE_FROM_PLAYLIST) {
            val input = call.receive<RemoveFromPlayList>()
//            val sessionData=call.sessions.get<UserId>()
//                ?:throw SessionDataIsNullException(SESSION_MESSAGE,HttpStatusCode.Unauthorized)
            val uuid=UUID.fromString(input.uuid)
            userServices.removeFromPlayListService(input,uuid)
                .apply {
                    call.respond(HttpStatusCode.OK,this) }
        }

        get(VIEW_PLAYLIST) {
            val input = call.receive<ViewPlayList>()
//            val sessionData=call.sessions.get<UserId>()
//                ?:throw SessionDataIsNullException(SESSION_MESSAGE,HttpStatusCode.Unauthorized)
            val uuid=UUID.fromString(input.uuid)
            userServices.viewPlayListService(input.playlistName!!, uuid)
                .apply {
                    call.respond(HttpStatusCode.OK,this) }
        }
        post(DELETE_PLAYLIST) {
            val input = call.receive<ViewPlayList>()
            val uuid=UUID.fromString(input.uuid)

//            val sessionData=call.sessions.get<UserId>()?:throw SessionDataIsNullException(SESSION_MESSAGE,HttpStatusCode.Unauthorized)
            userServices.deletePlayListService(input.playlistName!!,uuid)
                .apply {
                    call.respond(HttpStatusCode.OK,this) }
        }

//        post(USER_LOGOUT){
//            call.sessions.clear<UserId>()
//            call.respond(HttpStatusCode.OK, SuccessResponse("Logout Success",HttpStatusCode.Accepted.toString()))
//        }

        delete(DELETE_ACCOUNT) {
            val input = call.receive<DeleteAccount>()
            val uuid=UUID.fromString(input.uuid)
//            val input=call.sessions.get<UserId>()
//                ?:throw SessionDataIsNullException(SESSION_MESSAGE,HttpStatusCode.Unauthorized)
            userServices.deleteAccountService(uuid)
                .apply {
//                    call.sessions.clear<UserId>()
                    call.respond(HttpStatusCode.OK,this)
                }

        }


    }


}