package com.gmail.eamosse.idbdata.data

/**
 * Classe modélisant un token utilisateur
 * Classe modélisant les instances de token exposées à l'utilisateur
 */
data class Token(
    val expiresAt: String,
    val requestToken: String
)