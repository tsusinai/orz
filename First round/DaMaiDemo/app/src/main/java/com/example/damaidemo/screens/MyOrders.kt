package com.example.damaidemo.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.damaidemo.R

enum class ordertypes {
    YanChu,
    TuanGou,
    DianYin,
    XiaoShi,
    ShangCheng,
    JuBenSha,
}

data class orderTypesExample(
    val orders:String,
    val type: ordertypes,
)

val orderTypes=listOf(
    orderTypesExample("演出/门票", ordertypes.YanChu),
    orderTypesExample("团购卷", ordertypes.TuanGou),
    orderTypesExample("电影", ordertypes.DianYin),
    orderTypesExample("小食", ordertypes.XiaoShi),
    orderTypesExample("商城", ordertypes.ShangCheng),
    orderTypesExample("剧本杀", ordertypes.JuBenSha)

)

@Preview
@Composable
fun MyOrders(modifier:Modifier=Modifier){

    var curType by remember{ mutableStateOf(ordertypes.YanChu) }

    Box(modifier=modifier
        .fillMaxSize()
        .background(Color(0xFFF8F8F8))
    )
    Column(modifier = modifier.fillMaxSize()){
        Top(modifier=modifier)
        TopLazy(modifier=modifier,orderTypes,curType=curType,{ curType = it })

        when(curType){
            ordertypes.YanChu -> YanChu(modifier=modifier,YanChuRows)
            else -> null
        }

    }
}

@Composable
fun Top(modifier:Modifier=Modifier){
    Row(modifier = modifier
        .background(Color.White)
        .height(60.dp)
        .padding(start = 6.dp),
        verticalAlignment = Alignment.CenterVertically

    ){
        Box(modifier=modifier
            .fillMaxSize()
        ){
            Icon(painter = painterResource(R.drawable.person_topbar_1),
            null,
                modifier=modifier
                    .size(32.dp)
                    .align(Alignment.CenterStart)
    //                .clickable{navController.popBackStack()}
            )

            Text(text = "我的订单",
                 fontSize = 26.sp,

                modifier=modifier
                    .align(Alignment.Center)
            )
        }
    }
}


@Preview
@Composable
fun TopLazyT(){
}


@Composable
fun TopLazy(modifier: Modifier =Modifier, items: List<orderTypesExample>, curType: ordertypes, onTypeChange: (ordertypes) -> Unit){
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val lazyWidth = screenWidth/5



    val linnerBrush = Brush.linearGradient(
        colors = listOf(Color(0xFFFD79BD),Color(0xFFFEB9E9)),
        start = Offset(x=0f,y=0f),
        end = Offset(x= Float.POSITIVE_INFINITY,y=0f)
    )

    LazyRow(
        modifier=modifier
            .height(40.dp)
            .fillMaxWidth()
            .background(Color.White)
    ) {
        items(items) { items ->
            Box(
                modifier=modifier
                    .height(40.dp)
                    .width(lazyWidth)
                    .clickable{  onTypeChange(items.type) }//通过回调修改父组件状态，原代码直接赋值无法生效
            ){
                Text(text = items.orders,
                    fontSize = 16.sp,
                    color = if (curType==items.type) Color(0xFF000000) else Color(0xFFA1A2A8),
                    modifier=modifier
                        .align(Alignment.Center)
                )
                if (curType==items.type) {
                    Box(
                        modifier = modifier
                            .width(24.dp)
                            .height(4.dp)
                            .background(brush = linnerBrush, RoundedCornerShape(24.dp))
                            .align(Alignment.BottomCenter)
                            .clip(RoundedCornerShape(24.dp))
                    )
                    {
                    }
                }
            }
        }
    }
}


enum class YanChuRowTypes{
    SouSuo,FuKuan,SouHuo,PinJia
}


data class YanChuRow(
    val name:String,
    val types: YanChuRowTypes
)

val YanChuRows=listOf(
    YanChuRow("搜索", YanChuRowTypes.SouSuo),
    YanChuRow("待付款",YanChuRowTypes.FuKuan),
    YanChuRow("待收货",YanChuRowTypes.SouHuo),
    YanChuRow("待评价", YanChuRowTypes.PinJia),
)


@Composable
@Preview
fun YanChuT(){

}

@Composable
fun YanChu(modifier:Modifier=Modifier,items: List<YanChuRow>){
    var YanChuCurtype by remember{ mutableStateOf(YanChuRowTypes.SouSuo) }

    Row(modifier = modifier
        .fillMaxWidth()
        .height(40.dp)
        .background(Color.White)
        .padding(horizontal = allHorizonPadding),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)

    ){
        items.forEach{items ->
        Box(modifier = Modifier
            .padding(end = 6.dp)
        ) {
                Button(
                    onClick = {YanChuCurtype=items.types},
                    modifier = Modifier
                        .height(30.dp),
                    shape = RoundedCornerShape(20.dp), // 按钮圆角
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if(YanChuCurtype==items.types)Color(0xFFFF85A2)  else Color(0xFFBFC9D8)// 按钮粉色（接近截图）
                    )
                ) {}
                Text(
                    text = items.name,
                    color = if(YanChuCurtype==items.types)Color.White else Color(0xFF000000),
                    fontSize = 14.sp,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
             }
        }
    }
}


fun order(){

}