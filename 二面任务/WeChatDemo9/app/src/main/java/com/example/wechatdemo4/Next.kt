package com.example.wechatdemo4

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.wechatdemo4.screens.Cards
import com.example.wechatdemo4.ui.theme.WeChatDemo4Theme
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.Navigator
import com.example.wechatdemo4.navigation.NavRoutes
import com.example.wechatdemo4.screens.uCards
import com.example.wechatdemo4.MainActivity

class Next : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeChatDemo4Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Main2(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}


@Composable
//@Preview(showBackground = true)  //可预览标签
fun Groups2(modifier: Modifier= Modifier){
    Column(
        modifier = Modifier.fillMaxWidth(), //填充屏幕
        verticalArrangement = Arrangement.spacedBy(0.dp)

    ) {
        uCards("Membership Cards",R.drawable.mebership_icon,modifier = modifier)
        Spacer(modifier = Modifier.size(11.dp))
        uCards("Offers & Gifts Cards",R.drawable.offersandgiftcard_icon,modifier = modifier)
        uCards("Tickers & Licenses",R.drawable.tickets_icon,modifier = modifier)
    }
}

@Composable
@Preview
fun Main2(modifier: Modifier= Modifier){
    Column(
        modifier = Modifier
            .fillMaxSize() // 占满全屏
            .clip(RoundedCornerShape(16.dp)) //切角
            .background(Color(242, 242, 242)), // 背景为灰色
    ){
        Box(
            modifier = Modifier.fillMaxWidth()
                .background(Color(242,242,242))
                .size(96.dp)
        )
        {
            ImageButton_2() //缺少防抖功能


            Text(text="Cards & Offers",
                fontSize = 18.sp,
                fontWeight = FontWeight(400),
                modifier=Modifier.offset(138.dp,60.dp))


            Image(painter = painterResource(id=R.drawable.expand_left_icon),
                null,
                modifier=Modifier.offset(364.dp,58.dp))

        }

        Groups2(modifier=Modifier)
    }
}

@Preview
@Composable
fun ImageButton_2(
) {
    val context = LocalContext.current  //传入当前activity的context
    Image(
        painter = painterResource(id = R.drawable.expand_left__1_icon), // 加载 drawable 图标
        contentDescription = null, // 如果是装饰性图标可以为 null
        modifier = Modifier
            .offset(11.dp,60.dp)
            .clickable { val intent= Intent(context , MainActivity::class.java )
                intent.putExtra("返回值","返回成功") //数据传输
                context.startActivity(intent)
            } // 点击事件
    )
}

