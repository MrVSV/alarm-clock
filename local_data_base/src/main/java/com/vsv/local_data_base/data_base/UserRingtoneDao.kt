package com.vsv.local_data_base.data_base

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface UserRingtoneDao {

    @Upsert
    suspend fun addUserRingtone(userRingtoneEntity: UserRingtoneEntity)

    @Delete
    suspend fun deleteUserRingtone(userRingtoneEntity: UserRingtoneEntity)

    @Query("SELECT * FROM user_ringtones WHERE uri=:uri")
    suspend fun getRingtoneByUri(uri: String): UserRingtoneEntity

    @Query("SELECT * FROM user_ringtones")
    fun getUserRingtoneList(): Flow<List<UserRingtoneEntity>>
}