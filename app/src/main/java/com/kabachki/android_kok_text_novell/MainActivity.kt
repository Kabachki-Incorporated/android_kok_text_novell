package com.kabachki.android_kok_text_novell

import android.media.MediaPlayer
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.kabachki.android_kok_text_novell.ui.theme.Android_kok_text_novellTheme

class MainActivity : ComponentActivity() {

    lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );
        WindowCompat.setDecorFitsSystemWindows(window, false)
        mediaPlayer = MediaPlayer.create(this, R.raw.epic)
        mediaPlayer.setOnErrorListener { mp, what, extra ->
            print("hello")
            true
        }
        mediaPlayer.setVolume(1000f, 1000f)
        mediaPlayer.start()
        setContent {
            Android_kok_text_novellTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    Greeting()
                }
            }
        }
    }
}

@Composable fun Greeting() {
    Box(
        modifier = Modifier
            .safeContentPadding()
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.radioactive),
            contentDescription = "Radioactive !!",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Pulsating(modifier = Modifier
            .align(Alignment.BottomStart)
            .padding(16.dp)) {
            Button(onClick = { }) {
                Text(text = "В путешествие !")
            }
        }
        Pulsating(modifier = Modifier
            .align(Alignment.BottomEnd)
            .padding(16.dp)) {
            Button(onClick = { }) {
                Text(text = "Я лох !")
            }
        }
    }
}

@Composable
fun Pulsating(modifier: Modifier, pulseFraction: Float = 1.2f, content: @Composable () -> Unit) {
    val infiniteTransition = rememberInfiniteTransition()

    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = pulseFraction,
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(modifier = modifier.scale(scale)) {
        content()
    }
}