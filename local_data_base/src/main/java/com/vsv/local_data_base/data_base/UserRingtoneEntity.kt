package com.vsv.local_data_base.data_base

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vsv.core.domain.ringtone.MyRingtone

@Entity(tableName = "user_ringtones")
data class UserRingtoneEntity(
    @PrimaryKey
    val uri: String,
    val title: String,
) {
    fun toModel(): MyRingtone = MyRingtone(
        uri = uri,
        title = title,
        isUserRingtone = true
    )
}

fun MyRingtone.toEntity(): UserRingtoneEntity = UserRingtoneEntity(
    uri = uri,
    title = title,
)
