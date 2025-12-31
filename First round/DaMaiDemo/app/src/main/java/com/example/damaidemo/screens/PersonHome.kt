package com.example.damaidemo.screens

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.damaidemo.R
import com.example.damaidemo.data.data_source.LazyTopTools
import com.example.damaidemo.data.data_source.StrollVipInfos
import com.example.damaidemo.data.data_source.TopTools
import com.example.damaidemo.data.data_source.personBannerList
import com.example.damaidemo.data.model.TopToolsExample
import com.example.damaidemo.ui.theme.ClipSize
import com.example.damaidemo.ui.theme.allHorizonPadding
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.sample
import kotlinx.coroutines.launch

@Composable
fun PersonHome(navController: NavController){
    PersonHomeT(modifier = Modifier,navController)
}


@Composable
fun PersonAppBar(totalScrollOffsetPx:Int,modifier: Modifier) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .padding(horizontal = allHorizonPadding),
        verticalAlignment = Alignment.CenterVertically,
    ){Box(
        modifier=modifier
            .fillMaxSize()
    ){
        if(totalScrollOffsetPx>60) {
            Row(modifier=modifier
                .align(Alignment.CenterStart)
            ) {
                Image(
                    modifier = Modifier
                        .padding(horizontal = paddingHorizional)
                        .clip(CircleShape)
                        .size(34.dp)
                        .border(1.dp, Color(0xFFFFFFFF), CircleShape),
                    painter = painterResource(id = R.drawable.top_test),
                    contentDescription = null,
                )
                Text(
                    text = "帅气的麦子",
                    style = TextStyle(
                        fontSize = 19.sp,
                        fontWeight = Bold,
                    ),
                    color = if (isVIP) Color(0xFFFE231A) else Color(0xFF000000),
                )
            }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier=modifier
                .align(Alignment.CenterEnd)
                .padding(end = 6.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.person_topbar_1),
                null,
                modifier = Modifier.size(26.dp)
            )

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
                    Icon(
                        painter = painterResource(R.drawable.person_topbar_2),
                        contentDescription = "消息",
                        modifier = Modifier.size(26.dp)
                    )
                }
            }
            }
        }
    }
}

@OptIn(FlowPreview::class)
@Composable
fun PersonHomeT(modifier: Modifier=Modifier,navController: NavController){
    // 节流间隔：20ms（1秒50次，低于人眼感知阈值）
    val throttleMillis: Long = 20
    // 防抖延迟：滚动停止后50ms再更新
    val debounceMillis: Long = 50

    // 1. 创建 LazyListState 管理列表状态
    val lazyListState = rememberLazyListState()

    // 总滚动像素（从列表顶部开始，向上滚动为正）
    var totalScrollOffsetPx by remember { mutableStateOf(0) }

    LaunchedEffect(key1 = lazyListState) {
        snapshotFlow { lazyListState.layoutInfo }
            .distinctUntilChanged()
            // 第一步：节流 - 每throttleMillis只取一次值，降低频率
            .sample(throttleMillis)
            // 第二步：防抖 - 滚动停止后再更新，避免滚动中频繁变化
            .debounce(debounceMillis)
            .collect { layoutInfo ->
                if (layoutInfo.visibleItemsInfo.isEmpty()) {
                    totalScrollOffsetPx = 0
                    return@collect
                }

                val firstVisibleItem = layoutInfo.visibleItemsInfo.first()
                val firstItemIndex = firstVisibleItem.index
                val firstItemOffset = firstVisibleItem.offset

                var previousItemsHeight = 0
                for (i in 0 until firstItemIndex) {
                    layoutInfo.visibleItemsInfo.find { it.index == i }?.let {
                        previousItemsHeight += it.size
                    }
                }
                val totalOffset = previousItemsHeight - firstItemOffset
                // 关键：只在偏移量变化超过阈值时更新（过滤微小抖动）
                if (kotlin.math.abs(totalOffset - totalScrollOffsetPx) > 2) {
                    totalScrollOffsetPx = totalOffset
                }
            }
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFFF7F4EF)),
    )

    PersonAppBar(totalScrollOffsetPx,modifier=modifier)

    LazyColumn(
        modifier=modifier
            .fillMaxSize()
            .padding(top = 40.dp),
        state = lazyListState
    ) {
        item{Tops(modifier)}
        item{DaMaiVip()}
        item{TopTool(modifier,navController)}
        item{MiddleTools()}
        item{LazyTopTool()}
        item{CarouselBannerAtPerson(modifier = modifier)}
        item{Bottom()}
        item{Bottom()}
        item{Bottom()}
        item{Bottom()}
    }
}

val paddingHorizional = 10.dp
val isVIP = false

var subscription:Int=0
var fans:Int=0
var like:Int=0

@Preview
@Composable
fun Tops(modifier:Modifier=Modifier){

    Box(modifier=modifier
        .fillMaxWidth()
        .height(80.dp)
        .background(color = Color(0xFFF7F4EF))
    ){
        Row(modifier = Modifier
            .align(Alignment.CenterStart),

            horizontalArrangement = Arrangement.Center

        ) {
            Image(
                modifier = modifier
                    .padding(horizontal = paddingHorizional)
                    .clip(CircleShape)
                    .size(66.dp)
                    .border(1.dp, Color(0xFFFFFFFF), CircleShape),
                painter = painterResource(id = R.drawable.top_test),
                contentDescription = null,
            )

            Column(
                modifier = Modifier
                    .padding(vertical = 10.dp)
            ) {
                Text(text = "帅气的麦子",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = Bold,
                    ),
                    color = if (isVIP) Color(0xFFFE231A) else Color(0xFF000000),
                )

                Row(horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically){
                    ShitText(subscription,"关注")
                    ShitText(fans,"粉丝")
                    ShitText(like,"获赞和想看")
                }
            }
        }

    }
}

@Composable
fun ShitText(data:Int,type:String) {
    val TextSize = 16.sp

    Row(horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    )
    {
        Text(
            text = "$data  ",
            style = TextStyle(
                fontSize = TextSize,
                fontWeight = Bold,
            )
        )
        Text(
            text = type+"  |    ",
            style = TextStyle(
                fontSize = TextSize,
            ),
            color = Color.Gray
        )
    }
}

@Preview
@Composable
fun DaMaiVip(modifier:Modifier= Modifier){
    val TopTextSize = 16.sp
    val TopVIPTextSize = 18.sp
    val TopRightSize = 14.sp

    val linearGradient1 = Brush.linearGradient(
        colors = listOf(Color(0xFFEAAC85),Color(0xFFF7E5CC)),
        end = Offset(x=0f,y=Float.POSITIVE_INFINITY),
        start = Offset(x= Float.POSITIVE_INFINITY,y=0f)
    )

    Card(modifier=modifier
        .fillMaxWidth()
        .padding(horizontal = allHorizonPadding+6.dp)
        .height(100.dp)
        .clip(RoundedCornerShape(ClipSize.dp))
        .background(brush = linearGradient1),

        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
    )
    {
        Column(){
            Row(modifier=modifier
                .fillMaxWidth()
                .padding(start = paddingHorizional, end = paddingHorizional,top = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically,) {
                    Text(
                        text = "升级",
                        style = TextStyle(
                            fontSize = TopTextSize,
                            fontWeight = Bold,
                        ),
                        color = Color(0xFF5F2E13)
                    )
                    Text(
                        text = "大麦VIP",
                        style = TextStyle(
                            fontSize = TopVIPTextSize,
                            fontWeight = Bold,
                            fontFamily = FontFamily.Monospace
                        ),
                        color = Color(0xFF5F2E13)
                    )
                    Text(
                        text = "可享权益",
                        style = TextStyle(
                            fontSize = TopTextSize,
                            fontWeight = Bold,
                        ),
                        color = Color(0xFF5F2E13)
                    )
                }

                Text(
                    text = "全部权益 >",
                    style = TextStyle(
                        fontSize = TopRightSize,
                    ),
                    color = Color(0xFF5F2E13),
                )

            }
            StrollVIP(modifier=modifier)
        }

    }
}


@Composable
fun StrollVIP(modifier:Modifier=Modifier){
    var currentIndex by remember { mutableStateOf(0) } // 当前轮播索引
    val scope = rememberCoroutineScope()
    val listState = rememberLazyListState(
        initialFirstVisibleItemIndex = currentIndex // 初始显示第0项
    )
    // 自动轮播逻辑：每秒切换一次
    LaunchedEffect(Unit) {
        scope.launch {
            while(true) {
                delay(3000) // 轮播间隔（1.5秒）
                currentIndex = (currentIndex + 1) % StrollVipInfos.size
                listState.animateScrollToItem(
                    index = currentIndex,
                    scrollOffset = 0,
                )
            }
        }
    }
    LazyColumn(
        state = listState,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        userScrollEnabled = false
    ){items(StrollVipInfos.size) { index ->
        Row(modifier = modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(horizontal = 6.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Row(
                modifier=modifier
                    .padding(start = 6.dp)
                ,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ){
                Image(painter = painterResource(id=R.drawable.damai_vip),
                null,
                    modifier=modifier
                        .size(38.dp)
                        .clip(RoundedCornerShape(12.dp))
                )
                Text(text = StrollVipInfos[index].test,
                    style = TextStyle(
                        fontSize = 14.sp,
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier=modifier
                        .width(200.dp)
                )
            }
            Box(modifier = Modifier
                .padding(end = 6.dp)
            ) {
                Button(
                    onClick = { /* 搜索逻辑 */ },
                    modifier = Modifier
                        .height(30.dp)
                        .width(80.dp)
                    ,
                    shape = RoundedCornerShape(20.dp), // 按钮圆角
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFF7F4EF) // 按钮粉色（接近截图）
                    )
                ) {}
                Text(
                    text = "去看看",
                    color = Color.Black,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
            }
        }
    }
}




@Composable
fun TopTool(modifier:Modifier=Modifier,navController: NavController){
    val cardHeight=90
    Card(modifier=modifier
        .fillMaxWidth()
        .padding(horizontal = allHorizonPadding)
        .height(cardHeight.dp)
        .clip(RoundedCornerShape(ClipSize.dp)),
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ){
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(cardHeight.dp)
            .padding(horizontal = 15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            TopTools(modifier=modifier,TopTools,cardHeight, navController)
        }
    }
}
@Composable
fun TopTools(modifier:Modifier=Modifier, items: List<TopToolsExample>, cardHeight:Int, navController: NavController) {
    items.forEach { items ->
        Box(
            modifier= modifier
                .clickable{navController.navigate(items.navGo)}
        ){
        Column(
            modifier = Modifier
                .width(cardHeight.dp)
        ) {
            Image(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .size(32.dp),
                painter = painterResource(id = items.iconId),
                contentDescription = null
            )
            Spacer(modifier = Modifier.size(2.dp))
            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                text = items.test,
                maxLines = 1, // 禁止换行，只显示1行
                //overflow = TextOverflow.Ellipsis // 超出部分显示省略号

                style = TextStyle(
                    fontSize = 14.sp
                )
            )
        }
        }
    }
}

@Preview
@Composable
fun MiddleTools(modifier:Modifier=Modifier){

    // 获取屏幕宽度（dp）
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    // 组件宽度 = 屏幕宽度的一半
    val componentWidth = screenWidth / 2

    val cardHeight=70
    val paddingHorizion=15.dp

    Box(modifier=modifier
        .fillMaxWidth()
        .padding(horizontal = allHorizonPadding)
        .height(cardHeight.dp)
        .clip(RoundedCornerShape(ClipSize.dp))
        .background(Color(0xFFFFFFFF))
    ) {

        Row(modifier = modifier
            .align(Alignment.Center),
        ) {
            Row(
                modifier = Modifier
                    .width(componentWidth)
                    .padding(horizontal = paddingHorizion, vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "抢票预约",
                    maxLines = 1, // 禁止换行，只显示1行
                    //overflow = TextOverflow.Ellipsis // 超出部分显示省略号
                    style = TextStyle(
                        fontSize = 14.sp
                    ),
                )
                Text(
                    text = ">",
                    maxLines = 1, // 禁止换行，只显示1行
                    //overflow = TextOverflow.Ellipsis // 超出部分显示省略号
                    style = TextStyle(
                        fontSize = 14.sp
                    )
                )
            }
            Row(
                modifier = Modifier
                    .width(componentWidth)
                    .padding(horizontal = paddingHorizion, vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "想看",
                    maxLines = 1, // 禁止换行，只显示1行
                    //overflow = TextOverflow.Ellipsis // 超出部分显示省略号
                    style = TextStyle(
                        fontSize = 14.sp
                    ),
                )
                Text(
                    text = ">",
                    maxLines = 1, // 禁止换行，只显示1行
                    //overflow = TextOverflow.Ellipsis // 超出部分显示省略号
                    style = TextStyle(
                        fontSize = 14.sp
                    )
                )

            }
        }
    }

}


@Composable
fun CarouselBannerAtPerson(modifier: Modifier = Modifier) {
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
                currentIndex = (currentIndex + 1) % personBannerList.size
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
            items(personBannerList.size) { index ->
                // 轮播项：图片
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
                        painter = painterResource(id = personBannerList[index].imageRes),
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
            personBannerList.forEachIndexed { index, _ ->
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
fun Bottom(modifier:Modifier=Modifier){
    Box(modifier=modifier
        .padding(vertical = 10.dp)
    )
    Column(modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(text = "动态",
            style = TextStyle(
                fontSize = 20.sp,
            ),
            modifier = Modifier
                .offset(10.dp,0.dp)
        )
        Card(modifier=modifier
            .fillMaxWidth()
            .padding(horizontal = allHorizonPadding)
            .height(110.dp)
            .clip(RoundedCornerShape(ClipSize.dp))
            .align(Alignment.Start),
            colors = CardDefaults.cardColors(containerColor = Color.White),
        ){
            Column(modifier = modifier
                .padding(horizontal = 10.dp)
                .offset(0.dp,6.dp),
            )
            {
                Text(text = "0元观看好演出",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = Bold,
                    ),
                )
                Text(text = "观演团活动招募中",
                    style = TextStyle(
                        fontSize = 18.sp,
                    ),
                )
            }

        }
    }
}


@Composable
@Preview
fun LazyTopTool(modifier:Modifier=Modifier){
    LazyRowTool(modifier = Modifier,LazyTopTools)
}

@Composable
fun LazyRowTool(modifier:Modifier=Modifier,items: List<TopToolsExample>){
    val cardHeight=90
    // 获取屏幕宽度（dp）
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val componentWidth = screenWidth / 4
    Card(modifier=modifier
        .fillMaxWidth()
        .padding(horizontal = allHorizonPadding)
        .height(cardHeight.dp)
        .clip(RoundedCornerShape(ClipSize.dp)),
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ){
        LazyRow(modifier = Modifier
            .fillMaxWidth()
            .height(cardHeight.dp),
            verticalAlignment = Alignment.CenterVertically,
        ){
            items(items) { items ->
                Column(
                    modifier = Modifier
                        .width(componentWidth)
                        .height(cardHeight.dp)
                ) {
                    Image(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .size(40.dp),
                        painter = painterResource(id =items.iconId),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.size(2.dp))
                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally),
                        text = items.test,
                        maxLines = 1, // 禁止换行，只显示1行
                        //overflow = TextOverflow.Ellipsis // 超出部分显示省略号

                        style = TextStyle(
                            fontSize = 14.sp
                        )
                    )
                }
            }
        }
    }
}