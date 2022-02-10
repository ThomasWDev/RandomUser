package com.example.randomuser.core.extensions

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/** ---
 * Extensions for `Gson` related stuffs
 */
inline fun <reified T> Gson.fromJson(json: String) = fromJson<T>(json, object: TypeToken<T>() {}.type)