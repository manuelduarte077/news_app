package dev.donmanuel.newsapp.presentation.common

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImagePainter
import coil3.compose.rememberAsyncImagePainter
import news_kmp_app.composeapp.generated.resources.Res
import news_kmp_app.composeapp.generated.resources.logo
import org.jetbrains.compose.resources.painterResource

/**
 * A reusable composable for loading images asynchronously with loading animation and fallback.
 * 
 * @param imageUrl The URL of the image to load
 * @param contentDescription Content description for accessibility
 * @param modifier Modifier to be applied to the image
 * @param contentScale How the image should be scaled within its bounds
 * @param enableAnimation Whether to enable the animation when the image loads
 */
@Composable
fun AsyncImageLoader(
    imageUrl: String?,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
    enableAnimation: Boolean = true
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        var imageLoadResult by remember {
            mutableStateOf<Result<Painter>?>(null)
        }
        val painter = rememberAsyncImagePainter(
            model = imageUrl,
            onSuccess = {
                imageLoadResult =
                    if (it.painter.intrinsicSize.width > 1 && it.painter.intrinsicSize.height > 1) {
                        Result.success(it.painter)
                    } else {
                        Result.failure(Exception("Invalid image size"))
                    }
            },
            onError = {
                it.result.throwable.printStackTrace()
                imageLoadResult = Result.failure(it.result.throwable)
            }
        )

        val painterState by painter.state.collectAsState()
        val transition by animateFloatAsState(
            targetValue = if (painterState is AsyncImagePainter.State.Success) {
                1f
            } else {
                0f
            },
            animationSpec = tween(durationMillis = 800)
        )

        when (val result = imageLoadResult) {
            null -> {
                PulseAnimation(
                    modifier = Modifier.fillMaxSize()
                )
            }

            else -> {
                Image(
                    painter = if (result.isSuccess) painter else {
                        painterResource(Res.drawable.logo)
                    },
                    contentDescription = contentDescription,
                    contentScale = contentScale,
                    modifier = Modifier
                        .fillMaxSize()
                        .graphicsLayer {
                            if (result.isSuccess && enableAnimation) {
                                rotationX = (1f - transition) * 30f
                                val scale = 0.8f + (0.2f * transition)
                                scaleX = scale
                                scaleY = scale
                            }
                        }
                )
            }
        }
    }
}
