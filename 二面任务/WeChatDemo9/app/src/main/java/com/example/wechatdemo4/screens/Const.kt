package com.example.wechatdemo4.screens

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import com.example.wechatdemo4.MainActivity
import com.example.wechatdemo4.Next
import com.example.wechatdemo4.R
import com.example.wechatdemo4.navigation.NavRoutes


const val Card_heights=64
const val Card_widths=402


@Composable
fun Cards (CardLable:String,ImageID:Int ,modifier: Modifier= Modifier,navController: NavController, context: Context) {

    Card(
        modifier = modifier
            .fillMaxWidth()//宽度填充
            .height(Card_heights.dp)
            .width(Card_widths.dp)
            .clickable{ Log.d("JumpTest", "Click event triggered")
            val intent = Intent(context, //创建一个intent跳转常量
                Next::class.java
            )
            context.startActivity(intent) //开始调用跳转
        },
        shape = RectangleShape, //形状设置
        colors = CardDefaults.cardColors(
            containerColor = Color.White, //颜色（card
        )
    ) {
        Row(
            modifier = Modifier
                .padding(13.dp)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        )
        {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = ImageID),
                    null,

                )
                Spacer(modifier = modifier.size(14.dp))

                Text(text = CardLable,
                    fontSize = 16.sp,
                    fontWeight = FontWeight(400))
            }

            Image(painter = painterResource(id=R.drawable.chevron_right),
                contentDescription = null,
            )
        }
    }
}

@Composable
fun uCards(CardLable:String,ImageID:Int ,modifier: Modifier= Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()//宽度填充
            .height(Card_heights.dp)
            .width(Card_widths.dp),
        shape = RectangleShape, //形状设置
        colors = CardDefaults.cardColors(
            containerColor = Color.White, //颜色（card
        )
    ) {
        Row(
            modifier = Modifier
                .padding(13.dp)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        )
        {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = ImageID),
                    null,
                )
                Spacer(modifier = modifier.size(14.dp))

                Text(text = CardLable,
                    fontSize = 16.sp,
                    fontWeight = FontWeight(400))
            }

            Image(painter = painterResource(id=R.drawable.chevron_right),
                contentDescription = null,
            )
        }
    }
}

@Composable
fun cCards(CardLable:String,ImageID:Int ,modifier: Modifier= Modifier,navController: NavController) {
    Card(
        modifier = modifier
            .fillMaxWidth()//宽度填充
            .height(Card_heights.dp)
            .clickable{navController.navigate(NavRoutes.Cards_And_Offers)}
            .width(Card_widths.dp),

        shape = RectangleShape, //形状设置
        colors = CardDefaults.cardColors(
            containerColor = Color.White, //颜色（card
        )


    ) {
        Row(
            modifier = Modifier
                .padding(13.dp)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        )
        {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = ImageID),
                    null,
                )
                Spacer(modifier = modifier.size(14.dp))

                Text(text = CardLable,
                    fontSize = 16.sp,
                    fontWeight = FontWeight(400))
            }

            Image(painter = painterResource(id=R.drawable.chevron_right),
                contentDescription = null,
            )
        }
    }
}


