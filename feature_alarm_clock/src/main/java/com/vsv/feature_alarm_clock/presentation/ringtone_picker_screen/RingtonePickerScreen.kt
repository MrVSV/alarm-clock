package com.vsv.feature_alarm_clock.presentation.ringtone_picker_screen

import android.content.ContentValues.TAG
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaMetadataRetriever
import android.media.MediaPlayer
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.vsv.core.domain.ringtone.MyRingtone
import org.koin.androidx.compose.koinViewModel

@Composable
fun RingtonePickerScreen(
    alarmItemId: Int,
    modifier: Modifier = Modifier,
    viewModel: RingtonePickerScreenViewModel = koinViewModel(),
    navController: NavController,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val ringtones by viewModel.ringtonesList.collectAsStateWithLifecycle()
    val deviceRingtones by remember {
        derivedStateOf { ringtones.filter { !it.isUserRingtone }}
    }
    val userRingtones by remember {
        derivedStateOf { ringtones.filter { it.isUserRingtone }}
    }
    var pickedRingtone by remember {
        mutableStateOf(state.defaultRingtone)
    }
    val onEvent = viewModel::onEvent
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current
    /**вынести в отдельный класс*/
    val mediaPlayer by remember {
        mutableStateOf(MediaPlayer())
    }
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

    DisposableEffect(key1 = lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_PAUSE) {
                mediaPlayer.stop()
            }
            if (event == Lifecycle.Event.ON_DESTROY) {
                mediaPlayer.release()
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
    /**сделать красиво*/
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            if (uri != null) {
                val r = MediaMetadataRetriever()
                r.setDataSource(context, uri)
                val ringtone = MyRingtone(
                    uri = uri.toString(),
                    title = r.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE) ?: "unnamed",
                    isUserRingtone = true
                )
                onEvent(RingtonePickerScreenEvent.AddUserRingtone(ringtone))
            }
        }
    )
    Scaffold(
        floatingActionButton = {
            Button(
                onClick = {
                    onEvent(
                        RingtonePickerScreenEvent.SetRingtone(
                            alarmItemId,
                            (pickedRingtone as MyRingtone)
                        )
                    )
                    navController.navigateUp()
                },
                modifier = Modifier
            ) {
                Text(text = "Save", fontSize = 18.sp)
            }
        },
        modifier = modifier.fillMaxSize()
    ) { paddingValues ->
        LazyColumn(
            contentPadding = paddingValues,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .selectableGroup()
        ) {
            item {
                Text(text = "Your ringtones")
            }
            item {
                IconButton(
                    onClick = { launcher.launch("audio/*") },
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "add alarm",
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier
                            .size(48.dp)
                            .background(MaterialTheme.colorScheme.primary)
                    )
                }
            }
            items(items = userRingtones){  ringtone ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .selectable(
                            selected = ringtone.uri == pickedRingtone?.uri,
                            onClick = {
                                pickedRingtone = ringtone
                                playRingtone(pickedRingtone as MyRingtone)
                            },
                            role = Role.RadioButton
                        )
                ) {
                    RadioButton(
                        selected = ringtone.uri == pickedRingtone?.uri,
                        onClick = null,
                    )
                    Text(
                        text = ringtone.title,
                        fontSize = MaterialTheme.typography.titleLarge.fontSize,
                        modifier = Modifier
                            .padding(start = 16.dp)
                    )
                }
            }
            item {
                Text(text = "Device ringtones")
            }
            items(items = deviceRingtones) { ringtone ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .selectable(
                            selected = ringtone.uri == pickedRingtone?.uri,
                            onClick = {
                                pickedRingtone = ringtone
                                playRingtone(pickedRingtone as MyRingtone)
                            },
                            role = Role.RadioButton
                        )
                ) {
                    RadioButton(
                        selected = ringtone.uri == pickedRingtone?.uri,
                        onClick = null,
                    )
                    Text(
                        text = ringtone.title,
                        fontSize = MaterialTheme.typography.titleLarge.fontSize,
                        modifier = Modifier
                            .padding(start = 16.dp)
                    )
                }
            }
        }
    }
}



