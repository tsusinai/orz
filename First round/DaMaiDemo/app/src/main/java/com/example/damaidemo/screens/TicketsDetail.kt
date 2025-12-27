package com.example.damaidemo.screens

import android.R.id.bold
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.fitInside
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontVariation.width
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.damaidemo.R


@Preview
@Composable
fun TicketsDetail() {
    Box(
        modifier = Modifier.fillMaxSize()
            .background(Color(0xFFF8F8F8))
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
                .background(Color(0xFFCC3859))
        ) {
            Image(
                painter = painterResource(R.drawable.person_top_3),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(horizontal = allHorizonPadding)
                    .size(15.dp)
            )
            Text(text = "订单详情",
                fontSize = 16.sp,
                color = Color.White,
                modifier = Modifier.align(Alignment.Center)
            )
        }
        LazyColumn(modifier = Modifier.fillMaxSize().padding(top = 30.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
            item { Top() }
            item { Middle() }
            item { MainCard() }
        }
    }
}







@Composable
fun Bottom_In_TicketsDetails(text1:String,color1:Color,color2:Color){
    Box(modifier = Modifier) {
        Button(
            onClick = { /* 搜索逻辑 */ },
            modifier = Modifier
                .height(24.dp),
            shape = RoundedCornerShape(20.dp), // 按钮圆角
            colors = ButtonDefaults.buttonColors(
                containerColor = color1 // 按钮粉色（接近截图）
            )
        ) {}
        Text(
            text = text1,
            color = color2,
            fontSize = 8.sp,
            modifier = Modifier
                .align(Alignment.Center)
                .offset(0.dp, (-1).dp)
        )
    }
}


@Preview
@Composable
fun Top(){
    Column() {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFCC3859))
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = allHorizonPadding + 4.dp)
            ) {
                Text(
                    text = "待支付 1145.14",
                    style = TextStyle(
                        fontSize = 16.sp
                    ),
                    color = Color.White,
                    modifier = Modifier
                        .padding(top = 10.dp)
                )

                Text(
                    text = "因项目火爆，请尽快完成支付。",
                    style = TextStyle(
                        fontSize = 9.sp
                    ),
                    color = Color.White,
                    letterSpacing = 2.sp,
                    modifier = Modifier
                        .padding(top = 6.dp)
                )

                Row(
                    modifier = Modifier
                        .padding(top = 15.dp, bottom = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Bottom_In_TicketsDetails("取消订单", Color(0xFFE76383), Color.White)
                    Bottom_In_TicketsDetails("立即付款", Color.White, Color(0xFFE76383))
                }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 6.dp, top = 6.dp, bottom = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row {
                    Text(
                        text = "服务",
                        style = TextStyle(
                            fontSize = 10.sp,
//                    fontWeight = Bold
                        ),
                        color = Color.Black
                    )
                    for (i in 1..3) {
                        Row(
                            modifier = Modifier
                                .padding(start = 6.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        )
                        {
                            Image(
                                painter = painterResource(R.drawable.detail_1),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(12.dp)
                            )

                            Text(
                                text = when (i) {
                                    1 -> "实名制购票和入场"
                                    2 -> "条件退"
                                    else -> "快递票"
                                },
                                style = TextStyle(
                                    fontSize = 10.sp,
//                    fontWeight = Bold
                                ),
                                color = Color.Gray,
                                modifier = Modifier
                                    .padding(start = 6.dp)
                            )
                        }
                    }
                }

                Text(
                    text = ">",
                    style = TextStyle(
                        fontSize = 10.sp,
//                    fontWeight = Bold
                    ),
                    color = Color.Gray,
                    modifier = Modifier
                        .padding(end = allHorizonPadding)
                )
            }
        }
    }
}

@Preview
@Composable
fun Middle(){
    Column(modifier = Modifier
        .fillMaxWidth()
        .height(50.dp)
        .padding(horizontal = allHorizonPadding)
        .background(Color.White)
    ){
        Row(modifier = Modifier.fillMaxSize()
            .padding(horizontal = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
            Image(
                painter = painterResource(R.drawable.person_top_4),
                contentDescription = null,
                modifier = Modifier.size(14.dp)
            )
            Text(text = "福建省福州市鼓楼区铜盘软件大道89-1号",
                fontSize =12.sp,
                color = Color.Gray
                )
        }
    }
}







@Preview
@Composable
fun MainCard(){
            Column(modifier =  Modifier . background(Color.White)){
                Box(modifier = Modifier
                    .padding(horizontal = allHorizonPadding),
                ){
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(80.dp)
                            .background(Color.White),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Image(painter = painterResource(R.drawable.gao_yang),
                            contentDescription = null,
                            modifier = Modifier
                                .height(60.dp)
                            )

                        Column(modifier=Modifier
                            .padding(start = 10.dp,top=4.dp, bottom = 10.dp)
                            .fillMaxHeight(),
                            verticalArrangement = Arrangement.SpaceAround
                        ){
                            Text(text = "蔡徐坤嘉年华世界巡回演唱会",
                                letterSpacing = 1.sp,
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    )
                                )
                            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                                Text(
                                    text = "长沙 | 长沙茂隆体育中心体育场",
                                    style = TextStyle(
                                        fontSize = 10.sp,
                                    ),
                                    color = Color.Gray
                                )

                                Text(
                                    text = "查看座位图",
                                    style = TextStyle(
                                        fontSize = 10.sp,
                                        fontWeight = Bold
                                    ),
                                    color = Color(0xFF64B3D7)
                                )

                            }
                        }
                    }
                }

//=====================================
            Box(modifier= Modifier
                .fillMaxWidth()
                .height(40.dp)
                .padding(horizontal = allHorizonPadding)
                .clip(RoundedCornerShape(4.dp))
                .background(Color(0xFFE7F7FF),RoundedCornerShape(4.dp))
            ){
                Row(modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = "时间",
                        style = TextStyle(
                            fontSize = 10.sp,
                            fontWeight = Bold
                        ),
                        color = Color(0xFF64B3D7)
                    )

                    Text(
                        text = "2025.11.11 20:00",
                        style = TextStyle(
                            fontSize = 10.sp,
                            fontWeight = Bold
                        ),
                        color = Color(0xFF64B3D7),
                        modifier = Modifier.padding(start = 20.dp)
                    )
                }
            }
//=====================================
            Box(modifier =Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(horizontal = allHorizonPadding)
                .background(Color.White)
            ) {
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Color.Transparent
                    ),
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(horizontal = allHorizonPadding, vertical = 16.dp)
                        .border(width = 1.dp, color = Color.Gray, shape = RectangleShape)
                ) {
                    Text(
                        text = "预售",
                        style = TextStyle(
                            fontSize = 10.sp,
                            color = Color.Gray
                        ),
                        modifier = Modifier
                            .padding(horizontal = 6.dp, vertical = 1.dp)
                    )
                }

                Text(
                    text = "须知事项 >",
                    style = TextStyle(
                        fontSize = 13.sp,
                    ),
                    color = Color(0xFFB8476A),

                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(horizontal = allHorizonPadding, vertical = 16.dp)
                )
            }
//=====================================
                Card(modifier = Modifier
                    .height(60.dp)
                    .fillMaxWidth()
                    .padding(horizontal = allHorizonPadding),
                    colors = CardDefaults.cardColors(
                        Color.White
                    ),
                    shape = RectangleShape,
                ){
                    Row(modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Text(text = "看台1200元")
                        Text(text = "￥2400.0")
                    }
                }
                Card(modifier = Modifier
                    .height(60.dp)
                    .fillMaxWidth()
                    .padding(horizontal = allHorizonPadding),
                    colors = CardDefaults.cardColors(
                        Color.White
                    ),
                    shape = RectangleShape,
                ) {
                    Column {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 10.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "商品总额",
                                style = TextStyle(
                                    fontSize = 12.sp,
                                ),
                                color = Color.Gray,
                            )
                            Text(
                                text = "￥2400.0",
                                style = TextStyle(
                                    fontSize = 12.sp,
                                ),
                                color = Color.Gray,
                            )
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxSize()
                                .padding(horizontal = 10.dp,vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "运费",
                                style = TextStyle(
                                    fontSize = 12.sp,
                                ),
                                color = Color.Gray,
                            )
                            Text(
                                text = "￥18.0",
                                style = TextStyle(
                                    fontSize = 12.sp,
                                ),
                                color = Color.Gray,
                            )
                        }

                    }
                }
                Card(modifier = Modifier
                    .height(60.dp)
                    .fillMaxWidth()
                    .padding(horizontal = allHorizonPadding),
                    colors = CardDefaults.cardColors(
                        Color.White
                    ),
                    shape = RectangleShape,
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "需付",
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontWeight = Bold
                            ),
                        )
                        Text(
                            text = "￥2418.0",
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontWeight = Bold
                            ),
                        )
                    }
                }
            }
    }
















//                    Canvas(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .fillMaxHeight()
//                    ){
//                        val Path1 = Path().apply {
//                            moveTo(0.dp.toPx(),0.dp.toPx())
//                            lineTo(0.dp.toPx(),1f)
//                        }
//                        val path2 = Path().apply {
//                            moveTo(60.dp.toPx(),0.dp.toPx())
//                            lineTo(60.dp.toPx(),1f)
//                        }
//
//                        drawPath(
//                            path = Path1,
//                            color = Color.Gray
//                        )
//                        drawPath(
//                            path = path2,
//                            color = Color.Gray
//                        )
//                    }