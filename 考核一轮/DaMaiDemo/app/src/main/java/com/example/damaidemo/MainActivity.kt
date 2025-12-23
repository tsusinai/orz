package com.example.damaidemo

import android.os.Bundle
import android.view.View
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.damaidemo.data.data_source.personBannerList
import com.example.damaidemo.screens.ClipSize
import com.example.damaidemo.screens.allHorizonPadding
import com.example.wechatdemo4.navigation.MyNavHost
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
                val navController = rememberNavController()
                MyNavHost(navController = navController, modifier = Modifier)
//                Home()
//            PersonHomeT()
                }
            }
        }


@Composable
@Preview
fun PersonHomeT(modifier: Modifier=Modifier){
    // 1. 创建 LazyListState 管理列表状态
    val lazyListState = rememberLazyListState()

    var totalScrollOffsetPx by remember { mutableStateOf(0) } // 总滚动像素（从顶部开始）

    LaunchedEffect(key1 = lazyListState) {
        snapshotFlow { lazyListState.layoutInfo }
            .distinctUntilChanged()
            .collect { layoutInfo ->
                if (layoutInfo.visibleItemsInfo.isEmpty()) {
                    totalScrollOffsetPx = 0
                    return@collect
                }
                // 第一个可见项的信息
                val firstVisibleItem = layoutInfo.visibleItemsInfo.first()

                // 总偏移量 = 第一个可见项的位置偏移（从列表顶部开始计算）
                totalScrollOffsetPx = -firstVisibleItem.offset
            }
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFFF7F4EF)),
    )

    TopBarInPersonHome(modifier,totalScrollOffsetPx)

    LazyColumn(
        modifier=modifier
            .fillMaxSize()
            .padding(top = 74.dp),
        state = lazyListState
    ) {
        item{Tops(modifier)}
        item{DaMaiVip()}
        item{TopTool(modifier)}
        item{MiddleTools()}
        item{LazyTopTool()}
        item{carouselBannerAtPerson(modifier = modifier)}
        item{Bottom()}
        item{DaMaiVip()}
        item{DaMaiVip()}

    }

}

val paddingHorizion = 10.dp
val isVIP = false


var subscription:Int=0
var fans:Int=0
var like:Int=0

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarInPersonHome(modifier: Modifier=Modifier,totalScrollOffsetPx:Int){


    TopAppBar(
        navigationIcon = {
            if(totalScrollOffsetPx>40){
                Row(horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) { Image(
                    modifier = modifier
                        .padding(horizontal = paddingHorizion)
                        .clip(CircleShape)
                        .size(34.dp)
                        .border(1.dp, Color(0xFFFFFFFF), CircleShape),
                    painter = painterResource(id = R.drawable.top_test),
                    contentDescription = null,
                )
                    Text(text = "帅气的麦子",
                        style = TextStyle(
                            fontSize = 19.sp,
                            fontWeight = Bold,
                        ),
                        color = if (isVIP) Color(0xFFFE231A) else Color(0xFF000000),
                    )
                }
            }
        },
        title = {},
        actions = {
            Row(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
            ) {
                Icon(painter = painterResource(R.drawable.xian_chang),
                    null,
                    modifier=modifier.size(32.dp)
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
                            painter = painterResource(R.drawable.chat),
                            contentDescription = "消息",
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }
            }
        },

        modifier=modifier,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor=Color.Transparent
        )
    )
}


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
                    .padding(horizontal = paddingHorizion)
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
    val TopTextSize = 18.sp
    val TopVIPTextSize = 20.sp

    val linearGradient1 = Brush.linearGradient(
        colors = listOf(Color(0xFFEAAC85),Color(0xFFF7E5CC)),
        end = Offset(x=0f,y=Float.POSITIVE_INFINITY),
        start = Offset(x= Float.POSITIVE_INFINITY,y=0f)
    )

    Card(modifier=modifier
        .fillMaxWidth()
        .height(140.dp)
        .clip(RoundedCornerShape(ClipSize.dp))
        .background(brush = linearGradient1),

        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
    )
    {
        Column(){
            Row(modifier=modifier
                .fillMaxWidth()
                .padding(horizontal = paddingHorizion, vertical = 6.dp),
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
                        fontSize = TopTextSize,
                    ),
                    color = Color(0xFF5F2E13),
                )

            }
        }

    }
}

data class TopToolsExample(
    val iconId:Int,
    val test:String,
)

val TopTools = listOf<TopToolsExample>(
    TopToolsExample(R.drawable.wo_de,"我的订单"),
    TopToolsExample(R.drawable.wo_de,"优惠券"),
    TopToolsExample(R.drawable.wo_de,"观演人"),
    TopToolsExample(R.drawable.wo_de,"收货地址"),
)

@Composable
@Preview
fun TopTool(modifier:Modifier=Modifier,){
    val cardHeight=90

    Card(modifier=modifier
        .fillMaxWidth()
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
            TopTools(TopTools,cardHeight)
        }
    }
}

@Composable
fun TopTools(items: List<TopToolsExample>,cardHeight:Int) {
    items.forEach { items ->
        Column(
            modifier = Modifier
                .width(cardHeight.dp)
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
                    color = Color.Gray

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
                    color = Color.Gray
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
fun carouselBannerAtPerson(modifier: Modifier = Modifier) {
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


val LazyTopTools = listOf<TopToolsExample>(
    TopToolsExample(R.drawable.wo_de,"我的订单"),
    TopToolsExample(R.drawable.wo_de,"优惠券"),
    TopToolsExample(R.drawable.wo_de,"观演人"),
    TopToolsExample(R.drawable.wo_de,"收货地址"),
    TopToolsExample(R.drawable.wo_de,"收货地址"),
    TopToolsExample(R.drawable.wo_de,"收货地址"),
    TopToolsExample(R.drawable.wo_de,"收货地址"),
    TopToolsExample(R.drawable.wo_de,"收货地址"),

    )
@Composable
@Preview
fun LazyTopTool(modifier:Modifier=Modifier){
    LazyRowTool(modifier = Modifier,LazyTopTools,120)
}

@Composable
fun LazyRowTool(modifier:Modifier=Modifier,items: List<TopToolsExample>,cardHeight:Int){
    val cardHeight=90
    // 获取屏幕宽度（dp）
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    val componentWidth = screenWidth / 4

    Card(modifier=modifier
        .fillMaxWidth()
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








        
        
        


