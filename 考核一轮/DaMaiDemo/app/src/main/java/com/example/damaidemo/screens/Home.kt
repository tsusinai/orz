package com.example.damaidemo.screens

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.util.packInts
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.xr.compose.testing.toDp
import com.example.damaidemo.R
import com.example.damaidemo.data.data_source.BottomShowsL
import com.example.damaidemo.data.data_source.BottomShowsR
import com.example.damaidemo.data.data_source.MiddleGroups
import com.example.damaidemo.data.data_source.TopCardsLine1
import com.example.damaidemo.data.data_source.TopCardsLine2
import com.example.damaidemo.data.data_source.bannerList
import com.example.damaidemo.data.model.BottomShow
import com.example.damaidemo.data.model.MiddleGroup
import com.example.damaidemo.data.model.TopCards
import com.example.damaidemo.ui.components.TopCard
import com.example.damaidemo.ui.components.middleLazyRow
import com.example.damaidemo.ui.components.middleLazyRow1
import com.example.damaidemo.ui.components.typesOnMainScreenOnTop
import com.example.wechatdemo4.navigation.MyNavHost


var allHorizonPadding =8.dp
var ClipSize = 10 //卡片切割角

var CardLR1Weight = 190
var CardLR1Height = 100

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(
    navController: NavController
) {
    val linearGradient = Brush.linearGradient(
        colors = listOf(Color(0xFFFDDAE5), Color(0xFFFFFFFF)),
        start = Offset(0f, Float.POSITIVE_INFINITY),
        end = Offset(Float.POSITIVE_INFINITY, 0f)
    )
            TopAppBar(

                modifier = Modifier
                    .background(brush = linearGradient)
                    .height(50.dp)
                ,
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                ),

                navigationIcon = {
                    Row(
                        modifier = Modifier
                            .offset(0.dp,-3.dp)
                        ,
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
                        Text(
                            "福州",
                            style = TextStyle(
                                fontSize = 20.sp,
                                color = Color.Black
                            )
                        )
                    }
                },
                title = {},
                actions = {
                    BadgedBox(
                        badge = {
                            Box(
                                modifier = Modifier
                                    .offset(2.dp, (-9).dp)
                                    .size(10.dp)
                                    .background(
                                        color = Color(0xFFF44336),
                                        shape = RoundedCornerShape(50)
                                    )
                            )
                        }
                    ) {
                        Box() {
                            Image(
                                painter = painterResource(R.drawable.chat),
                                contentDescription = "消息",
                                modifier = Modifier.size(25.dp)
                            )
                        }
                    }
        }
    )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top=60.dp)
            ,

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
                    padding(horizontal = 10.dp),
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {

                    Box(
                        modifier = Modifier
                            .height(70.dp)
                            .width(60.dp)

                    ) {
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
                                    text = "￥380起",
                                    color = Color.White,
                                    fontSize = 9.sp

                                )
                            }
                        }

                    }


                    Box(
                        modifier = Modifier
                            .height(70.dp)
                            .width(60.dp)

                    ) {
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
                                    text = "￥380起",
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




@Composable
//@Preview
fun middleGroups(
    items: List<MiddleGroup>,
){
    var CardMWeight = 125
    var CardMHeight = 60

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = allHorizonPadding),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween

    )
    {
        items.forEach { item ->
            Card(
                modifier = Modifier
                    .height(CardMHeight.dp)
                    .width(CardMWeight.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White, // 默认背景色（容器色）
                )
            )
            {   Row(
                modifier = Modifier.fillMaxWidth()
            ){
                Column(
                    modifier = Modifier
                        .padding(start = 7.dp,top = 9.dp, end = 13.dp),//这个来控制image？
                    verticalArrangement= Arrangement.spacedBy(6.dp)

                ) {
                    Text(
                        text = item.text1,
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.SansSerif,
                        )
                    )
                    Text(
                        text = item.text2,
                        color = Color(214, 214, 214),
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
                        .clip(RoundedCornerShape(ClipSize.dp))
                        .size(25.dp)
                        .align(Alignment.Bottom)
                )
            }
            }
            Spacer(modifier = Modifier.size(6.dp))
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
                val lineSize = 35.dp
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

@Composable
@Preview
fun bottomColumn(modifier: Modifier = Modifier) {
    Row(modifier = Modifier
        .fillMaxWidth())
    {
        BottomShowLR(modifier,BottomShowsL)
        BottomShowLR1(modifier,BottomShowsR)
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
        Text(text = "为你推荐")

        items.forEach{items->
            Card(
                modifier = Modifier
                    .width(componentWidth)
                    .height(300.dp)
                    .padding(horizontal = 5.dp, vertical = 5.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White, // 默认背景色（容器色）
                )

            ) {
                Column(
                    modifier = Modifier.height(200.dp)
                ) {
                    Image(
                        painter = painterResource(id = items.image),
                        contentDescription = null,
                        modifier = Modifier
                            .clip(RoundedCornerShape(ClipSize.dp))
                            .fillMaxWidth()
                            .height(150.dp)
                    )
                    //不要打太多字球球了
                    Text(text = items.title)
                    Text(text = items.date)

                }
                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,

                    ) {
                    Text(
                        text = "￥sdfsdf",
                        color = Color(255, 0, 0),
                        fontWeight = FontWeight(400)
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
    ) {
        Spacer(modifier = Modifier.size(80.dp))

        items.forEach{items->
            Card(
                modifier = Modifier
                    .width(componentWidth)
                    .height(280.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White, // 默认背景色（容器色）
                )

            ) {
                Column(
                    modifier = Modifier.height(200.dp)
                ) {
                    Image(
                        painter = painterResource(id = items.image),
                        contentDescription = null,
                        modifier = Modifier
                            .clip(RoundedCornerShape(ClipSize.dp))
                            .fillMaxWidth()
                    )
                    //不要打太多字球球了
                    Text(text = items.title)
                    Text(text = items.date)

                }
                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,

                    ) {
                    Text(
                        text = "￥sdfsdf",
                        color = Color(255, 0, 0),
                        fontWeight = FontWeight(400)
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















