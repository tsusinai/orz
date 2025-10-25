package com.example.wechatdemo4.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.wechatdemo4.R
import com.example.wechatdemo4.navigation.NavItem
import com.example.wechatdemo4.navigation.navItems


@Composable
fun Cards_And_Offers1(navController: NavController) {
    Main2T(navController =navController)
}

@Composable
//@Preview
fun Main2T(modifier: Modifier= Modifier,navController :NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize() // 占满全屏
            .clip(RoundedCornerShape(16.dp)) //切角
            .background(Color(242, 242, 242)), // 背景为灰色
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
                .background(Color(242, 242, 242))
                .size(96.dp)
        )
        {

            ImageButton(navController = navController) //缺少防抖功能

            Text(
                text = "Cards & Offers",
                fontSize = 18.sp,
                fontWeight = FontWeight(400),
                modifier = Modifier.offset(138.dp, 60.dp)
            )


            Image(
                painter = painterResource(id = R.drawable.expand_left_icon),
                null,
                modifier = Modifier.offset(364.dp, 58.dp)
            )

        }

        Groups3(modifier = Modifier, navController = navController, items = cardItems)
    }
}


data class CardItem(
    val ImageID:Int,
    val CardLable : String,
)
val cardItems =listOf(
    CardItem(
        R.drawable.mebership_icon,
        "Membership Cards"
    ),
    CardItem(
        R.drawable.offersandgiftcard_icon,
        "Offers & Gifts Cards"
    ),
    CardItem(
        R.drawable.tickets_icon,
        "Tickers & Licenses"
    ),
)


@Composable
//@Preview(showBackground = true)  //可预览标签
fun Groups3(
    modifier: Modifier = Modifier,
    navController: NavController,
    items: List<CardItem>,
    ) {
    Column(
        modifier = Modifier.fillMaxWidth(), //填充屏幕
        verticalArrangement = Arrangement.spacedBy(0.dp)
    ) {
        items.forEachIndexed { index ,item -> //需要索引
            if (index == 1) {
                Spacer(modifier = Modifier.height(16.dp))
            }
            uCards(
                CardLable = item.CardLable,
                ImageID = item.ImageID,
                modifier = modifier
            )
        }
    }
}





@Composable
//@Preview(showBackground = true)  //可预览标签
fun Groups4(
    modifier: Modifier = Modifier,
    navController: NavController,
    items: List<CardItem>,
) {
    Column(
        modifier = Modifier.fillMaxWidth(), //填充屏幕
        verticalArrangement = Arrangement.spacedBy(0.dp)
    ) {
        items.forEach { item -> //无需索引
            uCards(
                CardLable = item.CardLable,
                ImageID = item.ImageID,
                modifier = modifier
            )
        }
    }
}


@Composable
fun ImageButton_1(
    navController: NavController,
) {
    Image(
        painter = painterResource(id = R.drawable.expand_left__1_icon), // 加载 drawable 图标
        contentDescription = null, // 如果是装饰性图标可以为 null
        modifier = Modifier
            .offset(8.dp,59.dp)
            .clickable {  navController.popBackStack() } // 点击事件
    )
}

