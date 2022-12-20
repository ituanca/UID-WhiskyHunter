package com.example.whiskyhunter

import android.content.Intent

interface OnIntentReceived {
    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
}