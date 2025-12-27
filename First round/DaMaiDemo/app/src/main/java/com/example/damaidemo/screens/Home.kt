package com.example.damaidemo.screens

import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Canvas
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.example.damaidemo.R
import com.example.damaidemo.data.data_source.BottomShowsL
import com.example.damaidemo.data.data_source.BottomShowsR
import com.example.damaidemo.data.data_source.MiddleGroups
import com.example.damaidemo.data.data_source.bannerList
import com.example.damaidemo.data.model.BottomShow
import com.example.damaidemo.data.model.MiddleGroup
import com.example.damaidemo.ui.components.middleLazyRow
import com.example.damaidemo.ui.components.middleLazyRow1
import com.example.damaidemo.ui.components.typesOnMainScreenOnTop
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


var allHorizonPadding =8.dp
var ClipSize = 6 //卡片切割角

var CardLR1Weight = 190
var CardLR1Height = 100


@Preview
@Composable
fun AppBar(
    modifier: Modifier = Modifier,
//    onClick:(Unit) -> Unit
){
    val linearGradient = Brush.linearGradient(
        colors = listOf(Color(0xFFF7DDE6), Color(0xFFF1F5F4)),
        end = Offset(0f, 700f),
        start = Offset(Float.POSITIVE_INFINITY, 0f)
    )


    Box(modifier=Modifier
        .fillMaxWidth()
        .background(linearGradient)
    ){
    Row(modifier=Modifier
        .fillMaxWidth()
        .height(40.dp)
        .padding(horizontal = allHorizonPadding-4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    )
    {
        Row(
            modifier = Modifier
                .offset(0.dp, -3.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp),
        ) {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color.Transparent
                ),
                modifier = Modifier
                    .offset((-4).dp, 0.dp)
                    .border(width = 1.dp, color = Color.Gray, shape = RectangleShape)
            ) {
                Text(
                    text = "证照\n信息",
                    style = TextStyle(
                        fontSize = 10.sp,
                        color = Color.Gray
                    )
                )
            }
            Box() {Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    "福州",
                    style = TextStyle(
                        fontSize = 20.sp,
                        color = Color.Black
                    )
                )
                Text(
                    "▼",
                    style = TextStyle(
                        fontSize = 10.sp,
                        color = Color.Black
                    )
                )
                }
            }
        }

        Box(modifier = Modifier
            .padding(top=4.dp)
        )
         {
             Canvas(
                 modifier = Modifier
                     .width(270.dp)
                     .height(30.dp)
             ) {
                 // 绘制外层圆角矩形（边框+背景）
                 drawRoundRect(
                     color = Color.White,
                     cornerRadius = CornerRadius(24.dp.toPx()),
                     style = Fill
                 )

                 drawRoundRect(
                     color = Color(0xFFFFC0CB),
                     cornerRadius = CornerRadius(24.dp.toPx()),
                     style = Stroke(width = 1.dp.toPx())
                 )

                 // 绘制分割线（图标与输入框之间）
                 val dividerX = 32.dp.toPx() // 图标+边距后的X坐标
                 drawLine(
                     color = Color(0xFFFFC0CB),
                     start = Offset(dividerX, 8.dp.toPx()),
                     end = Offset(dividerX, 24.dp.toPx()),
                     strokeWidth = 1.dp.toPx()
                 )
             }

             Canvas(
                 modifier = Modifier
                     .padding(top=14.dp)
                     .width(20.dp)
                     .height(16.dp)
             ) {

                 drawRoundRect(
                     color = Color(0xFFFFFFFF),
                     cornerRadius = CornerRadius(0.dp.toPx()),
                     style = Fill
                 )
                 drawLine(
                     color = Color(0xFFFFC0CB),
                     start = Offset(0.dp.toPx(), 0.dp.toPx()),
                     end = Offset(0.dp.toPx(), 16.dp.toPx()),
                     strokeWidth = 1.dp.toPx()
                 )
                 drawLine(
                     color = Color(0xFFFFC0CB),
                     start = Offset(0.dp.toPx(), 16.dp.toPx()),
                     end = Offset(24.dp.toPx(), 16.dp.toPx()),
                     strokeWidth = 1.dp.toPx()
                 )
             }

             // 叠加内容（图标、输入框、按钮）
             Row(
                 modifier = Modifier
                     .width(270.dp)
                     .height(48.dp),
             ) {
                 Box(modifier= Modifier.fillMaxSize()){

                 Image(painter = painterResource(R.drawable.main_topbar_2),
                     contentDescription=null,
                     modifier=Modifier
                         .size(26.dp)
                         .align(Alignment.CenterStart)
                         .padding(start = 6.dp,bottom = 8.dp)
                 )



                 Box(modifier = Modifier
                     .height(30.dp)
                     .width(185.dp)
                     .align(Alignment.TopStart)
                     .padding(start = 36.dp)
                 ){
                     Text(text="蔡徐坤巡回演唱会",
                          style = TextStyle(
                                fontSize = 12.sp,
                          ),
                         color = Color.Gray,
                         maxLines = 1,
                         overflow = TextOverflow.Ellipsis,

                         modifier=Modifier
                             .align(Alignment.CenterStart)
                             .padding(start =3.dp)
                     )
                 }


                 Box(modifier = Modifier
                     .padding(end = 6.dp,top=3.dp)
                     .align(Alignment.TopEnd)
                 ) {
                     Button(
                         onClick = { /* 搜索逻辑 */ },
                         modifier = Modifier
                             .height(24.dp),
                         shape = RoundedCornerShape(20.dp), // 按钮圆角
                         colors = ButtonDefaults.buttonColors(
                             containerColor = Color(0xFFFF85A2) // 按钮粉色（接近截图）
                         )
                     ) {}
                     Text(
                         text = "搜索",
                         color = Color.White,
                         fontSize = 14.sp,
                         modifier = Modifier
                             .align(Alignment.Center)
                     )
                    }
                 }
             }
        }

        BadgedBox(
            modifier = Modifier
            ,
            badge = {
//                Box(
//                    modifier = Modifier
//                        .offset(2.dp, (-9).dp)
//                        .size(10.dp)
//                        .background(
//                            color = Color(0xFFF44336),
//                            shape = RoundedCornerShape(50)
//                        )
//                ){
//                    Text("1",
//                        modifier = Modifier
//                            .align(Alignment.Center)
//                        )
//                }
            }
        ) {
            Box() {
                Image(
                    painter = painterResource(R.drawable.main_topbar_1),
                    contentDescription = "消息",
                    modifier = Modifier.size(25.dp)
                )
                }
              }
        }
    }
}


@Composable
fun Home(
    navController: NavController
) {
    Box(modifier=Modifier.background(Color(0xFFF1F5F4))) {
        AppBar()
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 46.dp),

            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item { typesOnMainScreenOnTop(modifier = Modifier) }
            item { cardLR1_MainGroupsOnMainScreen(modifier = Modifier) }
            item { middleGroups(MiddleGroups) }
            item { carouselBanner(modifier = Modifier) }
            item { middleLazyRow(modifier = Modifier) }
            item { middleLazyRow1(modifier = Modifier) }
            item { bottomColumn(modifier = Modifier) }
        }
    }
}


@Preview
@Composable
fun cardLR1_MainGroupsOnMainScreen(modifier: Modifier = Modifier){

    val linearGradient1 = Brush.linearGradient(
        colors = listOf(Color(0xFFFFFFFF), Color(0xFFFAF0F5)), // 紫色 → 青色
        start = Offset(200f, 50f),
        end = Offset(Float.POSITIVE_INFINITY, 0f), // 水平渐变
    )
    val linearGradient2 = Brush.linearGradient(
        colors = listOf(Color(0xFFFFFFFF), Color(0xFFFBF7E4)), // 紫色 → 青色
        start = Offset(200f, 50f),
        end = Offset(Float.POSITIVE_INFINITY, 0f), // 水平渐变
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = allHorizonPadding)
        ,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    )
    {
        Card(
            modifier = Modifier
                .height(CardLR1Height.dp)
                .width(CardLR1Weight.dp)
                .clip(RoundedCornerShape(ClipSize.dp))
                .background(brush = linearGradient1),

            colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        )
        {
            Column(
                modifier = Modifier
                    .padding(vertical = 4.dp)
                    .padding(horizontal = 10.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(20.dp)
                ) {
                    Text(
                        text = "爆款推荐",
                        style = TextStyle(
                            fontWeight = Bold
                        ),
                        modifier = Modifier
                    )
                    Box() {
                        //轮播
                    }
                }

                Row(
                    modifier = Modifier,
                    horizontalArrangement = Arrangement.spacedBy(15.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.zou_can_xong),
                        null,
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(50.dp)
                            .border(1.dp,Color(0xFFDFA410),CircleShape)
                    )
                    Column(
                        modifier = Modifier
                            .width(100.dp)
                    ) {
                        Text(
                            text = "2025周传雄「念念不忘·再遇见」巡回演唱会-福州站",
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                        )

                        Row(
                            modifier = Modifier,
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,

                            ) {
                            Text(
                                text = "￥380",
                                color = Color(255, 0, 0),
                                fontWeight = FontWeight(400),


                                )
                            Text(
                                text = " 起",
                                color = Color.Gray
                            )
                        }

                    }

                }
            }
        }

        Spacer(modifier = Modifier.size(9.dp))

        Card(
            modifier = Modifier
                .height(CardLR1Height.dp)
                .width(CardLR1Weight.dp)
                .clip(RoundedCornerShape(ClipSize.dp))
                .background(brush = linearGradient2),
            colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        )
        {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .padding(horizontal = 10.dp),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(24.dp)
                ) {
                    Text(
                        text = "天天低价",
                        style = TextStyle(
                            fontWeight = Bold,
                            fontSize = 16.sp
                        ),
                        modifier = Modifier
                    )
                    Box() {
                        //轮播
                    }
                }

                Row(
                    modifier = Modifier.
                        fillMaxWidth().
                    padding(top = 5.dp, start = 15.dp, end = 15.dp),
                    horizontalArrangement = Arrangement.spacedBy(20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Box(
                        modifier = Modifier
                            .height(60.dp)
                            .width(55.dp)
                    )
                    {
                        Image(
                            painter = painterResource(id = R.drawable.lin_yi_lian),
                            null,
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                        Box(
                            modifier = Modifier
                                .wrapContentSize()
                                .align(Alignment.BottomCenter)
                                .clip(RoundedCornerShape(size = ClipSize.dp))
                                .background(Color(0xFFFA7085))
                                .zIndex(1f) // 手动提升层级
                        ) {
                            Row(
                                modifier = Modifier,
                            ) {
                                Text(
                                    text = "  ￥380起  ",
                                    color = Color.White,
                                    fontSize = 9.sp

                                )
                            }
                        }

                    }


                    Box(
                        modifier = Modifier
                            .height(60.dp)
                            .width(55.dp)
                    )
                    {
                        Image(
                            painter = painterResource(id = R.drawable.kai_xin_ma_hua),
                            null,
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                        Box(
                            modifier = Modifier
                                .wrapContentSize()
                                .align(Alignment.BottomCenter)
                                .clip(RoundedCornerShape(size = ClipSize.dp))
                                .background(Color(0xFFFA7085))
                                .zIndex(1f) // 手动提升层级
                        ) {
                            Row(
                                modifier = Modifier,
                            ) {
                                Text(
                                    text = "  ￥380起  ",
                                    color = Color.White,
                                    fontSize = 9.sp

                                )
                            }
                        }

                    }

                }
            }
        }

    }
}

@Preview
@Composable
fun MiddleGroups(){
    middleGroups(MiddleGroups)
}

private enum class Enter{ //创建枚举类，记录box两个状态
    Small,
    Large,
}
@Composable
//@Preview
fun middleGroups(
    items: List<MiddleGroup>,
){
    var EnterState by remember { mutableStateOf(Enter.Small) } //创建box状态变量

    val transition = updateTransition(
        targetState = EnterState
    )

    val size by transition.animateDp {state -> //尺寸
        when(state){
            Enter.Small ->26.dp
            Enter.Large -> 29.dp
        }
    }

    LaunchedEffect(Unit) {
        while (true) {
            delay(1000)
            EnterState = when (EnterState) {
                Enter.Small -> Enter.Large
                Enter.Large -> Enter.Small
            }
        }
    }

    var CardMWeight = 124
    var CardMHeight = 55

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = allHorizonPadding),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween

    )
    {
        items.forEachIndexed { index, item ->
            Card(
                modifier = Modifier
                    .height(CardMHeight.dp)
                    .width(CardMWeight.dp)
                    .clip(shape = RoundedCornerShape(ClipSize.dp))
                    .then(
                        if (index == 2) {
                            Modifier.border(
                                width = 1.dp,
                                color = Color.Red,
                                shape = RoundedCornerShape(ClipSize.dp)
                            )
                        } else {
                            Modifier
                        }
                    ),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            )

            {   Row(
                modifier = Modifier.fillMaxSize()
            ){
                Column(
                    modifier = Modifier
                        .padding(start = 7.dp,top = 6.dp, end = 12.dp),//这个来控制image？
                    verticalArrangement= Arrangement.spacedBy(3.dp)

                ) {
                    Text(
                        text = item.text1,
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.SansSerif,
                        )
                    )
                    Text(
                        text = item.text2,
                        color = Color(0xFF545557),
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily.SansSerif,
                        )
                    )
                }

                Image(
                    painter = painterResource(id = item.imageId),
                    null,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .then(
                            if (index == 2) {
                                Modifier.size(size)
                                    .offset(-3.dp,0.dp)
                            } else {
                                Modifier.size(26.dp)
                                    .offset(0.dp,6.dp)
                                    .clip(RoundedCornerShape(ClipSize.dp))
                            }
                        ),
                     )
                }
            }
            Spacer(modifier = Modifier.then(
                if (index == 2) {
                    Modifier.size(0.dp)
                } else {
                    Modifier.size(6.dp)
                }
            )
            )
        }
    }
}



//还需要设置监听器：❗监听所在index
@Composable
fun carouselBanner(modifier: Modifier = Modifier) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    var currentIndex by remember { mutableStateOf(0) } // 当前轮播索引
    val scope = rememberCoroutineScope()

    val listState = rememberLazyListState(
        initialFirstVisibleItemIndex = currentIndex // 初始显示第0项
    )
    // 自动轮播逻辑：每秒切换一次
    LaunchedEffect(key1 = Unit) {
        scope.launch {
            while(true) {
                delay(1500) // 轮播间隔（1.5秒）
                currentIndex = (currentIndex + 1) % bannerList.size
                listState.animateScrollToItem(
                    index = currentIndex,
                    scrollOffset = 0,
                )
            }
        }
    }

    //轮播图会卡到最后一个
    LaunchedEffect(listState.isScrollInProgress) {
        if (!listState.isScrollInProgress) {
            currentIndex = listState.firstVisibleItemIndex
        }
    }

    Box(modifier = Modifier.fillMaxWidth()) {
        // 轮播图主体（LazyRow + 水平滚动）
        LazyRow(
            state = listState,
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp),
            horizontalArrangement = Arrangement.Center,
            userScrollEnabled = true // 允许手动滑动

        ) {
            items(bannerList.size) { index ->
                // 轮播项：图片 + 文字
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .width(screenWidth)
                        .padding(horizontal = allHorizonPadding)
                        .clip(RoundedCornerShape(ClipSize.dp))
                        .clickable
                        {

                        },
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = bannerList[index].imageRes),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,//填满容器
                        modifier = Modifier.fillMaxSize()
                    )

                }
            }
        }

        // 指示器（小圆点）
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .offset(0.dp, 80.dp)
            ,
            horizontalArrangement = Arrangement.Center

        ) {
            bannerList.forEachIndexed { index, _ ->
                val isSelected = index == currentIndex
                val lineSize = 150.dp / bannerList.size
                val lineColor = if (isSelected) Color.White else Color.Gray

                Box(
                    modifier = Modifier
                        .width(lineSize)
                        .height(3.dp)
                        .background(lineColor)
                        .alpha(0.2F)
                        //     .clip(RoundedCornerShape(6.dp))
                        .clickable { currentIndex = index }
                )
            }
        }
    }
}

private enum class BottomColumns{ //创建枚举类，记录box两个状态
    Column1,
    Column2,
}

@Composable
@Preview
fun bottomColumn(modifier: Modifier = Modifier) {

    var type by remember{mutableStateOf(BottomColumns.Column1)}

    Column(modifier=Modifier)
    {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = allHorizonPadding),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ){
        Text(
            text = "天天低价",
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = if (type == BottomColumns.Column1) FontWeight.Bold else FontWeight.Normal
            ),
            modifier = Modifier
                .clickable{type= BottomColumns.Column1},
        )

        Text(
            text = "摸娱攻略",
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = if (type == BottomColumns.Column2) FontWeight.Bold else FontWeight.Normal
            ),
            modifier = Modifier
                .clickable{type= BottomColumns.Column2},
        )
    }

    Row(modifier = Modifier
        .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    )
    {
        if (type == BottomColumns.Column1){
        BottomShowLR(modifier, BottomShowsL)
        BottomShowLR1(modifier, BottomShowsR)}
      }

    }
}


@Composable
fun BottomShowLR(
    modifier: Modifier = Modifier,
    items: List<BottomShow>,
) {
// 获取屏幕宽度（dp）
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    // 组件宽度 = 屏幕宽度的一半
    val componentWidth = screenWidth / 2
    Column(
        modifier = Modifier
            .width(componentWidth)
    ) {

        items.forEach{items->
            Card(
                modifier = Modifier
                    .width(componentWidth)
                    .height(350.dp)
                    .padding(top = 5.dp, bottom = 5.dp, start = 6.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White, // 默认背景色（容器色）
                )

            ) {
                Column(
                    modifier = Modifier.height(350.dp)
                        .fillMaxSize()
                ) {
                    Box{
                    Image(
                        painter = painterResource(id = items.image),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(240.dp),
                            contentScale = ContentScale.FillBounds // 强制填充
                    )
                        Box(modifier = Modifier
                            .offset(5.dp,-7.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color(0xFF000000))
                            .border(0.5f.dp,Color.White,RoundedCornerShape(10.dp))
                            .align(Alignment.BottomStart),
                        ){
                            Text(
                                text = items.type,
                                style = TextStyle(
                                    fontSize = 13.sp,
                                ),
                                color = Color(0xFFFFFFFF),
                                modifier = Modifier
                                    .padding(bottom = 3.dp, top = 1.dp, start = 7.dp, end = 7.dp)
                            )
                        }
                    }


                    //不要打太多字球球了
                    Text(
                        text = "2025周传雄「念念不忘·再遇见」巡回演唱会-福州站",
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,

                        style = TextStyle(
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                        ),

                        modifier = Modifier
                            .padding(horizontal = 6.dp)
                    )

                    Spacer(modifier = Modifier.size(3.dp))

                    Text(text = items.date,
                        color = Color.Gray,
                        modifier = Modifier
                            .padding(horizontal = 6.dp)
                    )

                    Row(
                    modifier = Modifier
                        .padding(horizontal = 6.dp)
                        ,
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,

                    ) {
                    Text(
                        text = "￥sdfsdf",
                        color = Color(255, 0, 0),
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    )
                    Text(
                        text = " 起",
                        color = Color.Gray
                    )
                      }
                }
            }
        }
    }
}

@Composable
fun BottomShowLR1(
    modifier: Modifier = Modifier,
    items: List<BottomShow>,
) {
// 获取屏幕宽度（dp）
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    // 组件宽度 = 屏幕宽度的一半
    val componentWidth = screenWidth / 2
    Column(
        modifier = Modifier
            .width(componentWidth)
            .padding(horizontal = allHorizonPadding),
    ) {
        Spacer(modifier=Modifier.size(60.dp))

        items.forEach{items->
            Card(
                modifier = Modifier
                    .width(componentWidth)
                    .height(350.dp)
                    .padding(top = 5.dp, bottom = 5.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White, // 默认背景色（容器色）
                )

            ) {
                Column(
                    modifier = Modifier.height(350.dp)
                        .fillMaxSize()
                ) {

                    Box{
                        Image(
                            painter = painterResource(id = items.image),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(240.dp),
                            contentScale = ContentScale.FillBounds // 强制填充
                        )
                        Box(modifier = Modifier
                            .offset(5.dp,-7.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color(0xFF000000))
                            .border(0.5f.dp,Color.White,RoundedCornerShape(10.dp))
                            .align(Alignment.BottomStart),
                        ){
                            Text(
                                text = items.type,
                                style = TextStyle(
                                    fontSize = 13.sp,
                                ),
                                color = Color(0xFFFFFFFF),
                                modifier = Modifier
                                    .padding(bottom = 3.dp, top = 1.dp, start = 7.dp, end = 7.dp)
                            )
                        }
                    }


                    //不要打太多字球球了
                    Text(
                        text = "2025周传雄「念念不忘·再遇见」巡回演唱会-福州站",
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,

                        style = TextStyle(
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                        ),

                        modifier = Modifier
                            .padding(horizontal = 6.dp)
                    )

                    Spacer(modifier = Modifier.size(3.dp))

                    Text(text = items.date,
                        color = Color.Gray,
                        modifier = Modifier
                            .padding(horizontal = 6.dp)
                    )

                    Row(
                        modifier = Modifier
                            .padding(horizontal = 6.dp)
                        ,
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,

                        ) {
                        Text(
                            text = "￥sdfsdf",
                            color = Color(255, 0, 0),
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                            )
                        )
                        Text(
                            text = " 起",
                            color = Color.Gray
                        )
                    }
                }
            }
        }
    }
}











