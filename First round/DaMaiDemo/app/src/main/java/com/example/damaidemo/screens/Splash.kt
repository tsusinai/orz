package com.example.damaidemo.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay
import com.example.damaidemo.R

/**
 * 带倒计时的开屏广告页面
 * @param navController 导航控制器，用于广告结束后跳转首页
 * @param adImageUrl 广告图片地址（支持本地资源/网络图片）
 * @param countdownSeconds 倒计时总时长（默认5秒）
 */
@Composable
fun SplashAdScreen(
    navController: NavHostController,
    adImageUrl: String = "https://example.com/your_ad_image.jpg", // 替换为你的广告图地址
    countdownSeconds: Int = 5
) {
    // 倒计时剩余秒数（使用mutableIntStateOf优化性能）
    var remainingSeconds by remember { mutableIntStateOf(countdownSeconds) }
    // 广告是否已结束（用于控制跳转逻辑）
    var adFinished by remember { mutableStateOf(false) }
    // 倒计时进度（用于环形进度条）
    val progress by animateFloatAsState(
        targetValue = remainingSeconds / countdownSeconds.toFloat(),
        label = "countdown progress"
    )

    // 倒计时逻辑
    LaunchedEffect(key1 = remainingSeconds, key2 = adFinished) {
        if (remainingSeconds > 0 && !adFinished) {
            delay(1000) // 每秒递减1
            remainingSeconds--
        } else if (remainingSeconds == 0 && !adFinished) {
            // 倒计时结束，标记广告完成并跳转
            adFinished = true
            navigateToHome(navController)
        }
    }

    // 广告主布局
    Box(modifier = Modifier.fillMaxSize()) {
        // 1. 广告背景图
        Image(
            painter = painterResource(R.drawable.gao_yang),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop // 填充屏幕并裁剪
        )

        // 2. 右上角跳过按钮（带倒计时）
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
                .size(40.dp)
                .background(Color.Black.copy(alpha = 0.5f), shape = CircleShape)
                .clickable(
                    enabled = !adFinished,
                    onClick = {
                        // 点击跳过，提前结束广告
                        adFinished = true
                        navigateToHome(navController)
                    }
                ),
            contentAlignment = Alignment.Center
        ) {
            // 环形进度条（可选，增强视觉效果）
            CircularProgressIndicator(
                progress = progress,
                modifier = Modifier.size(40.dp),
                color = Color.White,
                trackColor = Color.Transparent,
                strokeWidth = 2.dp
            )
            // 倒计时文字
            Text(
                text = "跳过\n$remainingSeconds",
                color = Color.White,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.wrapContentSize()
            )
        }
    }
}

/**
 * 跳转到首页的逻辑
 */
private fun navigateToHome(navController: NavHostController) {
    // 替换为你的首页路由，popUpTo清除回退栈，避免返回广告页
    navController.navigate("home") {
        popUpTo("splash_ad") { inclusive = true } //栈回退，防止回到广告
    }
}

