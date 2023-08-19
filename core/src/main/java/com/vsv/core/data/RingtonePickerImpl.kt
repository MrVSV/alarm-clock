package com.vsv.core.data

import android.content.Context
import android.media.RingtoneManager
import com.vsv.core.domain.MyRingtone
import com.vsv.core.domain.RingtonePicker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class RingtonePickerImpl(
    private val context: Context,
) : RingtonePicker {

    private val ringtoneManager = RingtoneManager(context).apply {
        setType(RingtoneManager.TYPE_ALL)
    }

    private val prefs = context.getSharedPreferences("default_ringtone", Context.MODE_PRIVATE)


    init {
        if (prefs.getString("ringtone_title", "").isNullOrEmpty()
            || prefs.getString("ringtone_title", "").isNullOrEmpty()
        )
            setDefaultRingtone()
    }

    private fun setDefaultRingtone() {
        val defaultRingtoneUri =
            RingtoneManager.getActualDefaultRingtoneUri(context, RingtoneManager.TYPE_ALARM)
        val defaultRingtone = MyRingtone(
            title = defaultRingtoneUri.getQueryParameter("title") ?: "unnamed",
            uri = buildString {
                append(defaultRingtoneUri.scheme)
                append("://")
                append(defaultRingtoneUri.authority)
                append(defaultRingtoneUri.path)
            }
        )
        prefs.edit().run {
            putString("ringtone_title", defaultRingtone.title)
            putString("ringtone_uri", defaultRingtone.uri)
        }.apply()
    }

    override fun setRingtone(myRingtone: MyRingtone) {
        prefs.edit().run {
            putString("ringtone_title", myRingtone.title)
            putString("ringtone_uri", myRingtone.uri)
        }.apply()
    }

    override fun getRingtone(): MyRingtone {
        return MyRingtone(
            title = prefs.getString("ringtone_title", "") ?: "",
            uri = prefs.getString("ringtone_uri", "") ?: ""
        )
    }

    override fun getRingtonesList(): List<MyRingtone> {
        var job: Job? = null
        val ringtoneList = mutableListOf<MyRingtone>()
        var rmCursor = ringtoneManager.cursor
        job = CoroutineScope(Dispatchers.IO).launch {
            while (rmCursor.moveToNext()) {
                val id = rmCursor.getInt(RingtoneManager.ID_COLUMN_INDEX)
                val uri = rmCursor.getString(RingtoneManager.URI_COLUMN_INDEX)
                val title = rmCursor.getString(RingtoneManager.TITLE_COLUMN_INDEX)
                ringtoneList += (MyRingtone(title = title, uri = "$uri/$id"))
            }
            job?.cancel()
            rmCursor = null
        }
        return ringtoneList
    }
}