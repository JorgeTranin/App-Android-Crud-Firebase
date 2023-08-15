package com.jorgetranin.app_android_crud_firebase.model

import android.os.Parcelable
import com.jorgetranin.app_android_crud_firebase.helper.FirebaseHelper
import kotlinx.parcelize.Parcelize

@Parcelize
data class Task(
    var id: String = "",
    var description: String = "",
    var state: Int = 0
) : Parcelable {
    init {
        this.id = FirebaseHelper.getDatabase().push().key ?: ""
    }
}
