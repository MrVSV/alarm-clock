package com.vsv.feature_alarm_clock.presentation.ringtone_picker_screen

import android.content.ContentValues.TAG
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.vsv.core.domain.MyRingtone

@Composable
fun RingtonePickerScreen(
    alarmItemId: Int,
    state: RingtonePickerScreenState,
    onEvent: (RingtonePickerScreenEvent) -> Unit,
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    Log.d(TAG, "RingtonePickerScreen: ${state.defaultRingtone}")
    val ringtonesList by remember {
        derivedStateOf { state.ringtonesList }
    }
    var pickedRingtone by remember {
        mutableStateOf(state.defaultRingtone)
    }
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val mediaPlayer = MediaPlayer()
    val audioAttributes = AudioAttributes.Builder(
    )
        .setUsage(AudioAttributes.USAGE_ALARM)
        .setLegacyStreamType(AudioManager.STREAM_ALARM)
        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
        .build()

    fun playRingtone(ringtone: MyRingtone) {
        try {
            mediaPlayer.apply {
                reset()
                setDataSource(context, Uri.parse(ringtone.uri))
                setAudioAttributes(audioAttributes)
                isLooping = false
                prepare()
                start()
            }
        } catch (e: Exception) {
            Log.d(TAG, "playRingtone: ${e.message}")
        }
    }

    DisposableEffect(key1 = Unit){

        onDispose {
            mediaPlayer.stop()
            mediaPlayer.release()

//            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    Scaffold(
        modifier = modifier.fillMaxSize()
    ) { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            LazyColumn(
                contentPadding = paddingValues,
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier
                    .padding(16.dp)
                    .weight(1f)
                    .selectableGroup()
            ) {
                items(items = ringtonesList) { ringtone ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .selectable(
                                selected = ringtone.uri == pickedRingtone?.uri,
                                onClick = {
                                    pickedRingtone = ringtone
                                    playRingtone(ringtone)
                                },
                                role = Role.RadioButton
                            )
                    ) {
                        RadioButton(
                            selected = ringtone.uri == pickedRingtone?.uri,
                            onClick = null,
                        )
                        Text(text = ringtone.title)
                    }
                }
            }
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Button(
                    onClick = {
                        onEvent(
                            RingtonePickerScreenEvent.SetRingtone(
                                alarmItemId,
                                (pickedRingtone as MyRingtone)
                            )
                        )
                        navController.navigateUp()
                    }
                ) {
                    Text(text = "SAVE")
                }
            }
        }
    }
}


