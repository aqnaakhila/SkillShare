package com.example.skillsharehub.models

import android.os.Parcelable.Creator

data class ScheduleModel(
    val title: String,
    val creator: String,
    val time: String
)