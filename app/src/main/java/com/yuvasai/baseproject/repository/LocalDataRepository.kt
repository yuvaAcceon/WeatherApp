package com.yuvasai.baseproject.repository

import android.content.Context
import javax.inject.Inject

class LocalDataRepository @Inject constructor(val context: Context) : ILocalDataRepository {
    override fun getCurrentToken(): String {
        return ""
    }

}