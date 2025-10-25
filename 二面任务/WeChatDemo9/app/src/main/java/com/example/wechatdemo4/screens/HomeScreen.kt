package com.example.wechatdemo4.screens

import android.content.Context
import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.example.wechatdemo4.R
import com.example.wechatdemo4.navigation.NavRoutes

//val context1 = LocalContext.current//脱离函数会出问题

@Composable
//@Preview
fun HomeScreen(navController: NavController,context: Context){

    Column(
        modifier = Modifier
            .fillMaxSize() // 占满全屏
            .clip(RoundedCornerShape(16.dp)) //切角
            .background(color = colorResource(id = R.color.background)) // 背景为灰色
    ){
        home(modifier = Modifier,navController=navController)

        Spacer(modifier= Modifier.size(22.dp)) //修改

        Groups1(modifier= Modifier,navController = navController,context=context)
    }
}

@Composable   //Compose函数标签
//@Preview(showBackground = true)  //可预览标签
fun Groups1(modifier: Modifier = Modifier, navController: NavController,context: Context){
    val context1 = LocalContext.current
    Column( //竖直线性布局

        modifier = Modifier.fillMaxWidth(), //填充屏幕横向
        verticalArrangement = Arrangement.spacedBy(0.dp) //设置子组件间距

    ) {   //Column垂直线性布局
        cCards("Pay and Service",R.drawable.payandservices_icon,modifier= Modifier,navController=navController) //PayAndService
        Spacer(modifier= Modifier.size(11.dp))
        uCards("Favorites",R.drawable.favorite_icon,modifier = modifier)
        uCards("My Posts",R.drawable.mypost_icon,modifier = Modifier)
        uCards("Channels",R.drawable.channel_icon,modifier = Modifier)
        Cards("Cards & Offers",R.drawable.cardsoffers,modifier= Modifier,navController = navController , context = context1)
        uCards("Sticker Gallery",R.drawable.stickergallery_icon,modifier= Modifier)
        Spacer(modifier= Modifier.size(11.dp))
        uCards("Settings",R.drawable.setting_icon,modifier= Modifier)
    }
}


@Composable
fun home(modifier: Modifier= Modifier, navController: NavController){
    Box(
        modifier= Modifier
            .fillMaxWidth()//宽度填充
            .background(Color(255, 255, 255))
            .height(203.dp)
            .width(402.dp)
    ){

        Box(
            modifier = Modifier
                .size(64.dp)
                .offset(x = 30.dp, y = 55.dp)
        )
        {

            Box(
                modifier = Modifier
                    .size(62.dp)
                    .offset(x = 0.dp, y = 4.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.Black.copy(alpha = 0.2f))
            )
            Image(
                painter = painterResource(id =R.drawable.avatar1),
                null,
                modifier = Modifier
                    .size(62.dp)
                    .clip(RoundedCornerShape(10.dp))
            ) //图片通过裁剪：形状+尺寸

        }

        Text(modifier = Modifier.offset(114.dp,100.dp)
            ,
            fontSize = 14.sp,
            color = Color(63,63,63),
            text="Weixin ID:xiyang",
            fontWeight = FontWeight(400))

        Text(modifier = Modifier
            .offset(114.dp, 55.dp),
            fontSize = 18.sp,

            text="Taoxiyang",
            fontWeight = FontWeight(400))


        Card(modifier=modifier
            .width(66.dp)
            .height(20.dp)
            .offset(x = 114.dp, y = 140.dp)
            .border(
                width = 1.dp,
                color = Color(236, 236, 236),
                shape = RoundedCornerShape(50)
            ),
            shape = RectangleShape,
            colors = CardDefaults.cardColors(
                containerColor = Color(255,255,255),
            )
        ){
            Row(
                modifier = modifier
                    .fillMaxSize()
                    .padding(start = 8.dp),
                verticalAlignment = Alignment.CenterVertically,

            ){
                Text("+",
                    color = Color(134,134,134 ),
                    fontSize = (13.sp)
                )

                Spacer(modifier= Modifier.size(2.dp))

                Text("Status",
                    color = Color(134,134,134 ),
                    fontSize = (13.sp),
                    fontWeight = FontWeight(400)
                )
            }


        }
        Card(modifier=modifier
            .width(91.dp)
            .height(20.dp)
            .offset(x = 193.dp, y = 140.dp)
            .border(
                width = 1.dp,
                color = Color(236, 236, 236),
                shape = RoundedCornerShape(50)
            )
            ,
            colors = CardDefaults.cardColors(
                containerColor = Color(255,255,255),
            )
        ) {
            Row(
                modifier = modifier
                    .fillMaxSize()
                    .padding(start = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.Center

            ){
                Image(painter = painterResource(id=R.drawable.avatar2),
                    null
                )
                Spacer(modifier = Modifier.size(5.dp))
                Text("1",
                    color=Color(149,149,149 ),
                    fontSize = 13.sp,
                    fontWeight = FontWeight(400)
                )
                Text(text="friends",
                    color=Color(149,149,149 ),
                    fontSize = 13.sp,
                    fontWeight = FontWeight(400))
            }

        }

        Row(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(20.dp)
        ){
            Image(painter=painterResource(id=R.drawable.two_code),
                null)
            Spacer(modifier=modifier.size(10.dp))
            Image(painter = painterResource(id=R.drawable.chevron_right),
                null)
        }

    }
}


//图形化按钮
@Composable
fun ImageButton(
    navController: NavController,
) {
    Image(
        painter = painterResource(id = R.drawable.chevron_right), // 加载 drawable 图标
        contentDescription = null, // 如果是装饰性图标可以为 null
        modifier = Modifier
            .clickable {  navController.navigate(NavRoutes.Cards_And_Offers) } // 点击事件
    )
}
