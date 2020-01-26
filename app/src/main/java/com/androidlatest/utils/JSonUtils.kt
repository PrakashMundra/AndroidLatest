package com.androidlatest.utils

import com.google.gson.Gson


class JSonUtils {
    companion object {
        fun <T> fromJson(data: String, classOfT: Class<T>?): T {
            return Gson().fromJson(data, classOfT)
        }

        fun toJson(obj: Any?): String? {
            var newObj = obj
            if (newObj == null)
                newObj = Any()
            return Gson().toJson(newObj)
        }
    }
}