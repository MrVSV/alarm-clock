package com.vsv.core.data

import android.content.Context
import android.media.MediaMetadataRetriever
import android.media.RingtoneManager
import android.net.Uri
import androidx.core.net.toUri
import com.vsv.core.domain.ringtone.MyRingtone
import com.vsv.core.domain.ringtone.RingtonePicker
import java.io.File
import java.io.FileOutputStream

class RingtonePickerImpl(
    private val context: Context,
) : RingtonePicker {

    private val ringtoneManager = RingtoneManager(context).apply {
        setType(RingtoneManager.TYPE_ALL)
    }

    private val prefs = context.getSharedPreferences("default_ringtone", Context.MODE_PRIVATE)

    override suspend fun getRingtonesList(): List<MyRingtone> {
        val ringtoneList = mutableListOf<MyRingtone>()
        val rmCursor = ringtoneManager.cursor
        while (rmCursor.moveToNext()) {
            val id = rmCursor.getInt(RingtoneManager.ID_COLUMN_INDEX)
            val uri = rmCursor.getString(RingtoneManager.URI_COLUMN_INDEX)
            val title = rmCursor.getString(RingtoneManager.TITLE_COLUMN_INDEX)
            ringtoneList += (MyRingtone(title = title, uri = "$uri/$id"))
        }
        ringtoneList.add(
            0, MyRingtone(
                title = "(Default) ${getDefaultDeviceRingtone().title}",
                uri = getDefaultDeviceRingtone().uri
            )
        )
        if (prefs.getString(RINGTONE_TITLE_KEY, "").isNullOrEmpty()
            || prefs.getString(RINGTONE_URI_KEY, "").isNullOrEmpty()
        ) {
            prefs.edit().run {
                putString(RINGTONE_TITLE_KEY, ringtoneList.first().title)
                putString(RINGTONE_URI_KEY, ringtoneList.first().uri)
            }.apply()
        }
        return ringtoneList
    }

    override suspend fun getLastPickedRingtone(): MyRingtone {
        getRingtonesList()
        return MyRingtone(
            title = prefs.getString(RINGTONE_TITLE_KEY, "") ?: "",
            uri = prefs.getString(RINGTONE_URI_KEY, "") ?: ""
        )
    }

    override suspend fun setRingtoneAsLastPicked(myRingtone: MyRingtone) {
        prefs.edit().run {
            putString(RINGTONE_TITLE_KEY, myRingtone.title)
            putString(RINGTONE_URI_KEY, myRingtone.uri)
        }.apply()
    }

    override suspend fun addUserRingtone(uri: Uri): MyRingtone {
        val bytes = context.contentResolver.openInputStream(uri)?.use {
            it.readBytes()
        }
        val r = MediaMetadataRetriever()
        r.setDataSource(context, uri)
        val file = File(
            context.filesDir,
            r.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE).toString()
        )
        FileOutputStream(file).use {
            it.write(bytes)
        }
        return MyRingtone(
            uri = file.toUri().toString(),
            title = file.name,
            isUserRingtone = true
        )
    }

//    override suspend fun deleteUserRingtone(ringtone: MyRingtone): Boolean {
//        val filter =  FileFilter{ file -> file.toUri().toString() == ringtone.uri }
//        return context.filesDir.listFiles(filter)?.first()?.delete() ?: false
//    }

    private fun getDefaultDeviceRingtone(): MyRingtone {
        val defaultRingtoneUri =
            RingtoneManager.getActualDefaultRingtoneUri(context, RingtoneManager.TYPE_ALARM)
        return MyRingtone(
            title = defaultRingtoneUri.getQueryParameter("title") ?: "unnamed",
            uri = buildString {
                append(defaultRingtoneUri.scheme)
                append("://")
                append(defaultRingtoneUri.authority)
                append(defaultRingtoneUri.path)
            }
        )
    }

    companion object {
        const val RINGTONE_TITLE_KEY = "ringtone_title"
        const val RINGTONE_URI_KEY = "ringtone_uri"
    }
}