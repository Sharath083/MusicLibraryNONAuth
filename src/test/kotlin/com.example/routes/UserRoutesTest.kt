package com.example.routes

import com.example.model.*
import com.example.utils.appconstant.APIEndPoints.ADD_TO_PLAYLIST
import com.example.utils.appconstant.APIEndPoints.DELETE_ACCOUNT
import com.example.utils.appconstant.APIEndPoints.DELETE_PLAYLIST
import com.example.utils.appconstant.APIEndPoints.FILTER_BY_ARTIST
import com.example.utils.appconstant.APIEndPoints.REMOVE_FROM_PLAYLIST
import com.example.utils.appconstant.APIEndPoints.USER_LOGIN
import com.example.utils.appconstant.APIEndPoints.USER_REGISTRATION
import com.example.utils.appconstant.APIEndPoints.USER_ROUTES
import com.example.utils.appconstant.APIEndPoints.VIEW_PLAYLIST
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.Test
import java.util.UUID
import kotlin.test.assertEquals

class UserRoutesTest {
    private val register=UserRegistration("sharath8","sharath8@gmail.com","12345")
    private val userLogin=UserLogin("sharath5","12345")
    private val input=InputSong("cold", "coldplay", "3:47")
    private val playList=AddToPlayList("8b83d5bc-7c28-4fd5-9426-f2322aab09a3","colds", "my1")
    private val viewPlayList=ViewPlayList("8b83d5bc-7c28-4fd5-9426-f2322aab09a3","my01")
    private val removePlayList=RemoveFromPlayList("8b83d5bc-7c28-4fd5-9426-f2322aab09a3","cold", "my1")
    private val artistData=ArtistData("coldplay")
    private val deleteAccount=DeleteAccount("8b83d5bc-7c28-4fd5-9426-f2322aab09a3")
    lateinit var  userId:UUID
    @Test
    fun userRegistrationRouteTest()= testApplication {
        val serializedData = Json.encodeToString(register)
        val response = client.post(USER_ROUTES + USER_REGISTRATION){
            headers[HttpHeaders.ContentType] = ContentType.Application.Json.toString()
            setBody(serializedData)
        }
        assertEquals(HttpStatusCode.Created, response.status)
    }
    @Test
    fun userLoginRouteTest()= testApplication {
        val serializedData = Json.encodeToString(userLogin)
        val response = client.post(USER_ROUTES + USER_LOGIN){
            headers[HttpHeaders.ContentType] = ContentType.Application.Json.toString()
            setBody(serializedData)
        }
        assertEquals(HttpStatusCode.OK, response.status)
    }
    @Test
    fun filterByArtistRouteTest()= testApplication {
        val serializedData = Json.encodeToString(artistData)
        val response = client.get(USER_ROUTES + FILTER_BY_ARTIST){
            headers[HttpHeaders.ContentType] = ContentType.Application.Json.toString()
            setBody(serializedData)
        }
        assertEquals(HttpStatusCode.OK, response.status)
    }
    @Test
    fun addToPlayListRouteTest()= testApplication {
        val serializedData = Json.encodeToString(playList)
        val response = client.post(USER_ROUTES + ADD_TO_PLAYLIST){
            headers[HttpHeaders.ContentType] = ContentType.Application.Json.toString()
            setBody(serializedData)
        }
        assertEquals(HttpStatusCode.BadRequest, response.status)
    }
    @Test
    fun removeFromPlayListRouteTest()= testApplication {
        val serializedData = Json.encodeToString(removePlayList)
        val response = client.post(USER_ROUTES + REMOVE_FROM_PLAYLIST){
            headers[HttpHeaders.ContentType] = ContentType.Application.Json.toString()
            setBody(serializedData)
        }
        assertEquals(HttpStatusCode.BadRequest, response.status)
    }
    @Test
    fun viewPlayListRouteTest()= testApplication {
        val serializedData = Json.encodeToString(viewPlayList)
        val response = client.get(USER_ROUTES + VIEW_PLAYLIST){
            headers[HttpHeaders.ContentType] = ContentType.Application.Json.toString()
            setBody(serializedData)
        }
        assertEquals(HttpStatusCode.BadRequest, response.status)
    }
    @Test
    fun deletePlayListRouteTest()= testApplication {
        val serializedData = Json.encodeToString(viewPlayList)
        val response = client.post(USER_ROUTES + DELETE_PLAYLIST){
            headers[HttpHeaders.ContentType] = ContentType.Application.Json.toString()
            setBody(serializedData)
        }
        assertEquals(HttpStatusCode.BadRequest, response.status)
    }
    @Test
    fun deleteAccountRouteTest()= testApplication {
        val serializedData = Json.encodeToString(deleteAccount)
        val response = client.delete(USER_ROUTES + DELETE_ACCOUNT){
            headers[HttpHeaders.ContentType] = ContentType.Application.Json.toString()
            setBody(serializedData)
        }
        assertEquals(HttpStatusCode.OK, response.status)
    }

}
