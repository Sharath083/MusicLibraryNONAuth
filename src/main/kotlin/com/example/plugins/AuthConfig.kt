//package com.example.plugins
//
//import com.auth0.jwt.JWT
//import com.auth0.jwt.algorithms.Algorithm
//import com.example.exceptions.InvalidTokenException
//import com.example.config.JWTData
//import io.ktor.http.*
//import io.ktor.server.application.*
//import io.ktor.server.auth.*
//import io.ktor.server.auth.jwt.*
//
//
//fun Application.configureSecurity() {
//    val secretAdmin = JWTData.secretAdmin
//    val issuer = JWTData.issuer
//    val audience = JWTData.audience
//    authentication {
//        jwt("Admin"){
//            verifier(
//                JWT
//                    .require(Algorithm.HMAC256(secretAdmin))
//                    .withAudience(audience)
//                    .withIssuer(issuer)
//                    .build()
//            )
//            validate { credential ->
//                if (credential.payload.audience.contains(audience)) JWTPrincipal(credential.payload) else null
//            }
//            challenge { _, _ ->
//                throw InvalidTokenException("The token is invalid or Expired", HttpStatusCode.BadRequest)
//            }
//        }
//    }
//}
