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


@Composable
fun Cards_And_Offers(navController: NavController) {
    Main2(navController =navController) }


@Composable
//@Preview(showBackground = true)  //可预览标签
fun Groups2(modifier: Modifier= Modifier,navController :NavController){
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
//@Preview
fun Main2(modifier: Modifier= Modifier,navController :NavController){
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

            ImageButton_1(navController = navController) //缺少防抖功能

            Text(text="Cards & Offers",
                fontSize = 18.sp,
                fontWeight = FontWeight(400),
                modifier=Modifier.offset(138.dp,60.dp))


            Image(painter = painterResource(id=R.drawable.expand_left_icon),
                null,
                modifier=Modifier.offset(364.dp,58.dp))

        }

        Groups2(modifier=Modifier,navController= navController)
    }

}
//图形化按钮
