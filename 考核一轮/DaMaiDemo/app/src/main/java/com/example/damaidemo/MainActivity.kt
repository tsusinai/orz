package com.example.damaidemo

import android.annotation.SuppressLint
import android.os.Bundle
import android.service.autofill.OnClickAction
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.ui.Alignment
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
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
//                val navController = rememberNavController()
//                MyNavHost(navController = navController,modifier= Modifier)
                Home()
                }
            }
        }


var Padding =3
var TypesOnMainScreenOnTopHeight=160 //顶部集群高度
var ClipSize = 10 //卡片切割角

var CardLR1Weight = 190
var CardLR1Height = 100
////第一行卡片规格
//data class CardLR1(
//    val weight:Int = 60,
//    val height:Int = 70,
//)

var CardMWeight = 125
var CardMHeight = 70
//data class CardLR2(
//    val weight:Int
//)


@Composable
@Preview
fun Home(){

    val textFieldState = rememberTextFieldState()

    // 2. 管理搜索结果（可通过状态变量动态更新）
    var results by remember { mutableStateOf(emptyList<String>()) }

    // 3. 搜索逻辑（例如过滤本地数据或请求网络）
    val onSearch: (String) -> Unit = { query ->
        // 模拟搜索逻辑
        results = listOf("结果1：$query", "结果2：$query")
    }

    Box(
        modifier = Modifier.fillMaxSize().
        background(color = Color(242,242,242))
        ,){
 LazyColumn(
     modifier = Modifier.
     fillMaxSize()
 )
 {
     //LazyColumn需要在items的作用域中❗
//     item {
//         simpleSearchBar(textFieldState, onSearch, results)
//         //测试区
//         typesOnMainScreenOnTop()
//         CardLR1_MainGroupsOnMainScreen()
//         MiddleGroups(MiddleGroups)
//         CarouselBanner()
//         MiddleLazyRow()
//         MiddleLazyRow1()
//         BottomColumn()
//     }
        item{   simpleSearchBar(textFieldState, onSearch, results)}
        item{typesOnMainScreenOnTop(modifier = Modifier)}
        item{MiddleGroups(MiddleGroups)}
        item{CardLR1_MainGroupsOnMainScreen(modifier = Modifier)}
        item{CarouselBanner(modifier = Modifier) }
        item{MiddleLazyRow(modifier = Modifier)}
        item{MiddleLazyRow1(modifier = Modifier)}
        item{BottomColumn(modifier = Modifier)}
        }
    }

}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun simpleSearchBar(
    textFieldState: TextFieldState,
    onSearch: (String) -> Unit,
    searchResults: List<String>,
    modifier: Modifier = Modifier
) {
    // Controls expansion state of the search bar
    var expanded by rememberSaveable { mutableStateOf(false) }

    Box(
        modifier
            .fillMaxWidth()
            .semantics { isTraversalGroup = true }
    ) {
        SearchBar(
            modifier = Modifier
                .align(Alignment.TopCenter),
//                .background(
//                    color = Color(255,255,255), // 使用主题颜色，而不是硬编码
//                    shape = RectangleShape
//                ),

            //笔记attention❗
            colors = SearchBarDefaults.colors(
                containerColor = Color(255,255,255),
                dividerColor = MaterialTheme.colorScheme.outline,
            ),

            inputField = {
                SearchBarDefaults.InputField(

                    query = textFieldState.text.toString(), // 当前输入的文本
                    onQueryChange = { textFieldState.edit { replace(0, length, it) } },//文本变化时更新状态
                    onSearch = { // 触发搜索（如点击搜索按钮、按回车）
                        onSearch(textFieldState.text.toString())
                        expanded = false // 搜索后折叠搜索栏
                    },
                    expanded = expanded,
                    onExpandedChange = { expanded = it },
                    placeholder = { Text("Search") } // 占位提示文本
                )
            },
            expanded = expanded,
            onExpandedChange = { expanded = it },
        ) {
            // Display search results in a scrollable column
            Column(Modifier.verticalScroll(rememberScrollState())) {
                searchResults.forEach { result ->
                    ListItem(
                        headlineContent = { Text(result) },
                        modifier = Modifier
                            .clickable {
                                textFieldState.edit { replace(0, length, result) }
                                expanded = false
                            }
                            .fillMaxWidth()
                    )
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    Search: String,
    onSearchQueryChange: (String) -> Unit,

    ){
    TopAppBar(
        navigationIcon ={},//左侧导航栏区域
        title = {
            TextField(
                value=Search,//绑定文本内容
                onValueChange = onSearchQueryChange,
                singleLine = true,//单行搜索
                modifier = Modifier.fillMaxWidth(),
            )

        },//顶部标题区域
        actions={},//右侧按钮操作区域
    )
}

@Preview
@Composable
fun typesOnMainScreenOnTop(modifier: Modifier = Modifier){
    Box(
        modifier = Modifier.
        padding(5.dp).
        fillMaxWidth().
        clip(RoundedCornerShape(size = ClipSize.dp)).
        background(Color(255,255,255))
        ,
    )
    {
        Column {
            TopCardLine(TopCardsL1)
            TopCardLine(TopCardsL1)
        }
    }

}

data class TopCards(
    val image: Int,
    val text: String,
    val isClick : OnClickAction?= null
)

val TopCardsL1=listOf<TopCards>(
    TopCards(
        image = R.drawable.ic_launcher_background,
        text = "演唱会"
    ),
    TopCards(
        image = R.drawable.ic_launcher_background,
        text = "音乐节"
    ),
    TopCards(
        image = R.drawable.ic_launcher_background,
        text = "Livehouse"
    ),
    TopCards(
        image = R.drawable.ic_launcher_background,
        text = "话剧音乐剧"
    ),
    TopCards(
        image = R.drawable.ic_launcher_background,
        text = "脱口秀"
    ),



)

@Composable
fun TopCardLine(
    items:List<TopCards>
){
    Row(modifier = Modifier
        .padding(horizontal = 8.dp)
        .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween


    ) {
        items.forEach { items ->
            Column(
                modifier = Modifier
                    .padding(10.dp)
            )
            {
                Image(
                    modifier = Modifier
                        .size(34.dp),
                    painter = painterResource(id = items.image),
                    contentDescription = null
                )

                Text(items.text)
            }
        }
    }
}


@Preview
@Composable
fun CardLR1_MainGroupsOnMainScreen( modifier: Modifier = Modifier){
    Row(
        modifier = Modifier.
        fillMaxWidth().
        padding(8.dp)
        ,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    )
    {
        Card(
            modifier = Modifier.
            height(CardLR1Height.dp).
            width(CardLR1Weight.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White, // 默认背景色（容器色）
            )
        )
        {
            Image(painter= painterResource(id = R.drawable.ic_launcher_background),
                null,
                modifier=Modifier.offset(20.dp,10.dp).
                clip(RoundedCornerShape(ClipSize.dp))
                    .size(50.dp)
                )
            Text(text = "我喜欢廖伦哲",
                fontWeight = FontWeight(400),
                modifier = Modifier.offset(100.dp,-5.dp)
                )

        }

        Spacer(modifier = Modifier.size(9.dp))

        Card(
            modifier = Modifier.
            height(CardLR1Height.dp).
            width(CardLR1Weight.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White, // 默认背景色（容器色）
            )
        )
        {
            Image(painter= painterResource(id = R.drawable.ic_launcher_background),
                null,
                modifier=Modifier.offset(20.dp,10.dp).
                clip(RoundedCornerShape(ClipSize.dp))
                    .size(50.dp)
            )

        }
    }
}




data class MiddleGroup(
    val text1: String,
    val text2: String,
    val icon : Int // 图标资源
)


val MiddleGroups = listOf(
    MiddleGroup(
        text1 = "演出日历",
        text2 = "按时间选演出",
        icon = R.drawable.ic_launcher_background
    ),
    MiddleGroup(
        text1 = "大麦演出榜",
        text2 = "你的观影指南",
        icon = R.drawable.ic_launcher_background
    ),
    MiddleGroup(
        text1 = "大麦团购",
        text2 = "超低价随时退",
        icon = R.drawable.ic_launcher_background
    ),
)


fun MiddleGroupsTest(): List<MiddleGroup> {
    return listOf<MiddleGroup>(
        MiddleGroup(
            text1 = "演出日历",
            text2 = "按时间选演出",
            icon = R.drawable.ic_launcher_background
        ),
        MiddleGroup(
            text1 = "大麦演出榜",
            text2 = "你的观影指南",
            icon = R.drawable.ic_launcher_background
        ),
        MiddleGroup(
            text1 = "大麦团购",
            text2 = "超低价随时退",
            icon = R.drawable.ic_launcher_background
        ),
    )
}

@Composable
//@Preview
fun MiddleGroups(
    items: List<MiddleGroup>,
){

        Row(
            modifier = Modifier.
            fillMaxWidth().
            padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween

        )
        {
            items.forEach { item ->
            Card(
                modifier = Modifier.
                height(CardMHeight.dp).
                width(CardMWeight.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White, // 默认背景色（容器色）
                )
            )
            {
                Text(
                    text = item.text1,
                    fontWeight = FontWeight(400),
                    modifier = Modifier.offset(8.dp, 0.dp)
                )
                Text(
                    text = item.text2,
                    fontWeight = FontWeight(400),
                    color = Color(214,214,214),
                    modifier = Modifier.offset(8.dp, 10.dp)
                )
                Image(
                    painter = painterResource(id = item.icon),
                    null,
                    modifier = Modifier.offset(90.dp, -4.dp).clip(RoundedCornerShape(ClipSize.dp))
                        .size(30.dp)
                )

            }
        }
    }
}


//Lazy型可用函数返回items方法
@Composable
//@Preview
fun MiddleGroups2(
    MiddleGroupsTest:List<MiddleGroup>,
    modifier: Modifier = Modifier
){

    LazyRow(
        modifier = Modifier.
        fillMaxWidth().
        padding(3.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    )
    {
        items(MiddleGroupsTest) { MiddleGroups ->
            Card(
                modifier = Modifier.
                height(CardMHeight.dp).
                width(CardMWeight.dp).
                background(Color(255,255,255)),
            )
            {
                Text(
                    text = MiddleGroups.text1,
                    fontWeight = FontWeight(400),
                    modifier = Modifier.offset(8.dp, 0.dp)
                )
                Text(
                    text = MiddleGroups.text2,
                    fontWeight = FontWeight(400),
                    color = Color(214,214,214),
                    modifier = Modifier.offset(8.dp, 10.dp)
                )
                Image(
                    painter = painterResource(id = MiddleGroups.icon),
                    null,
                    modifier = Modifier.offset(90.dp, -4.dp).clip(RoundedCornerShape(ClipSize.dp))
                        .size(30.dp)
                )

            }
        }
    }
}


//轮播图还没做
data class BannerItem(
    val id: Int,
    val imageRes: Int,
    val text: String
)

// 示例数据（替换为自己的图片资源）
val bannerList = listOf(
    BannerItem(1, R.drawable.ic_launcher_background, "1"),
    BannerItem(2, R.drawable.ic_launcher_background, "2"),
    BannerItem(3, R.drawable.ic_launcher_background, "3"),
)


//还需要设置监听器：❗监听所在index
@Composable
fun CarouselBanner(modifier: Modifier = Modifier) {
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
                    delay(3000) // 轮播间隔（3秒）
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
                .height(100.dp),
            horizontalArrangement = Arrangement.Center,
            userScrollEnabled = true // 允许手动滑动

        ) {
            items(bannerList.size) { index ->
                // 轮播项：图片 + 文字
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .width(screenWidth)
                        .padding(4.dp)
                        .clip(RoundedCornerShape(ClipSize.dp))
                        .clickable
                        {

                        },
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = bannerList[index].imageRes),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                    Text(
                        text = bannerList[index].text,
                        color = Color.White,
                        modifier = Modifier
                            .background(Color.Black.copy(alpha = 0.5f))
                            .padding(8.dp)
                    )
                }
            }
        }

        // 指示器（小圆点）
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .offset(0.dp,80.dp)
            ,
            horizontalArrangement = Arrangement.Center

        ) {
            bannerList.forEachIndexed { index, _ ->
                val isSelected = index == currentIndex
                val lineSize by animateDpAsState(if (isSelected) 40.dp else 41.dp)
                val lineColor = if (isSelected) Color.White else Color.Gray

                Box(
                    modifier = Modifier
                        .width(lineSize)
                        .height(6.dp)
                        .background(lineColor)
                        .alpha(0.5F)
                   //     .clip(RoundedCornerShape(6.dp))
                        .clickable { currentIndex = index }
                )
            }
        }
    }
}


@Composable
@Preview
fun MiddleLazyRow(modifier: Modifier = Modifier){
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(ClipSize.dp))
                .height(250.dp),

            colors = CardDefaults.cardColors(
                containerColor = Color.White, // 默认背景色（容器色）
            )
        )

        {
            Column(
                modifier= Modifier
                    .padding(horizontal = 10.dp, vertical = 10.dp)
            ) {
                Text("必看演出",
                    fontSize = 20.sp,
                    fontWeight = FontWeight(500)
                    )

                Spacer(modifier = Modifier.size(10.dp))

                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(ClipSize.dp))
                        .height(210.dp),
                    horizontalArrangement = Arrangement.spacedBy(3.dp), // 卡片之间的间距
                    contentPadding = PaddingValues(vertical = 8.dp) // 列表上下内边距
                )
                {
                    items(MustToSeeShow()) { item ->
                        MustToSeeShows(modifier,item)
                    }
                }
            }
        }
    }






data class MustToSeePicture(
    val text1: String,
    val cost: Int,
    val imageID : Int // 图标资源
)


fun MustToSeeShow(): List<MustToSeePicture> {
    return listOf<MustToSeePicture>(
        MustToSeePicture(
            text1 = "我真的好喜欢廖伦哲啊",
            cost = 233,
            imageID = R.drawable.ic_launcher_background,
        ),
        MustToSeePicture(
            text1 = "我也好喜欢",
            cost = 190,
            imageID = R.drawable.ic_launcher_background,
        ),
        MustToSeePicture(
            text1 = "我也好喜欢马嘉祺啊",
            cost = 120,
            imageID = R.drawable.ic_launcher_background,
        ),
        MustToSeePicture(
            text1 = "我也好喜欢刘耀文啊",
            cost = 180,
            imageID = R.drawable.ic_launcher_background,
        ),
        MustToSeePicture(
            text1 = "我也好喜欢lxx啊",
            cost = 170,
            imageID = R.drawable.ic_launcher_background,
        ),
        )
}

@Composable
fun MustToSeeShows(
    modifier: Modifier = Modifier,
    items:MustToSeePicture
){
    Card(
        modifier = Modifier
            .width(100.dp)
            .height(220.dp),
                colors = CardDefaults.cardColors(
                containerColor = Color.White, // 默认背景色（容器色）
    )

    ){
        Column(
            modifier = Modifier.height(150.dp)
        ) {
            Image(
                painter = painterResource(id = items.imageID),
                contentDescription = null,
                modifier = Modifier
                    .clip(RoundedCornerShape(ClipSize.dp))
                    .fillMaxWidth()
            )
            //不要打太多字球球了
            Text(text=items.text1)

        }
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,

            ){
            Text(text = "￥${items.cost}",
                color = Color(255,0,0),
                fontWeight = FontWeight(400)
            )
            Text(text = " 起",
                color = Color.Gray
            )

        }
    }
}


data class SameCityPicture(
    val text1: String,
    val cost: Int,
    val imageID: Int, // 图标资源
    val isClick : OnClickAction?= null
)


fun  SameCityPictures(): List< SameCityPicture> {
    return listOf< SameCityPicture>(
        SameCityPicture(
            text1 = "我真的好喜欢廖伦哲啊",
            cost = 233,
            imageID = R.drawable.ic_launcher_background,
        ),
        SameCityPicture(
            text1 = "我也好喜欢刘耀国啊",
            cost = 190,
            imageID = R.drawable.ic_launcher_background,
        ),
        SameCityPicture(
            text1 = "我也好喜欢马嘉祺啊",
            cost = 120,
            imageID = R.drawable.ic_launcher_background,
        ),
        SameCityPicture(
            text1 = "我也好喜欢刘耀文啊",
            cost = 180,
            imageID = R.drawable.ic_launcher_background,
        ),
        SameCityPicture(
            text1 = "我也好喜欢lxx啊",
            cost = 170,
            imageID = R.drawable.ic_launcher_background,
        ),
    )
}

@Composable
fun SameCityShows(
    modifier: Modifier = Modifier,
    items: SameCityPicture
){
    Card(
        modifier = Modifier
            .width(100.dp)
            .height(220.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White, // 默认背景色（容器色）
        )

    ){
        Column(
            modifier = Modifier.height(150.dp)
        ) {
            Image(
                painter = painterResource(id = items.imageID),
                contentDescription = null,
                modifier = Modifier
                    .clip(RoundedCornerShape(ClipSize.dp))
                    .fillMaxWidth()
            )
            //不要打太多字球球了
            Text(text=items.text1)
            Spacer(modifier = Modifier.size(10.dp))

        }
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,

            ){
            Text(text = "￥${items.cost}",
                color = Color(255,0,0),
                fontWeight = FontWeight(400)
            )
            Text(text = " 起",
                color = Color.Gray
            )

        }
    }
}

@Composable
@Preview
fun MiddleLazyRow1(modifier: Modifier = Modifier){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(ClipSize.dp))
            .height(250.dp),

        colors = CardDefaults.cardColors(
            containerColor = Color.White, // 默认背景色（容器色）
        )
    )

    {
        Column(
            modifier= Modifier
                .padding(horizontal = 10.dp, vertical = 10.dp)
        ) {
            Text("同城热点，本周热门推荐",
                fontSize = 20.sp,
                fontWeight = FontWeight(500)
            )

            Spacer(modifier = Modifier.size(10.dp))

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(ClipSize.dp))
                    .height(210.dp),
                horizontalArrangement = Arrangement.spacedBy(3.dp), // 卡片之间的间距
                contentPadding = PaddingValues(vertical = 8.dp) // 列表上下内边距
            )
            {
                items(SameCityPictures()) { item ->
                    SameCityShows(modifier,item)
                }
            }
        }
    }
}

data class BottomShow(
    val image:Int,
    val boxText:String,
    val title:String,
    val date:String,
    val cost:Int,
    val isClick : OnClickAction?= null
)

val BottomShowsL =listOf<BottomShow>(
    BottomShow(
        image = R.drawable.ic_launcher_background,
        boxText = "弹唱会",
        title = "【佛山】“追”刘耀国巡回演唱会-佛山站",
        date = "2025.11.29",
        cost =480,
    ),
    BottomShow(
        image = R.drawable.ic_launcher_background,
        boxText = "弹唱会",
        title = "【佛山】“追”刘耀国巡回演唱会-佛山站",
        date = "2025.11.29",
        cost =480,
    ),
    BottomShow(
        image = R.drawable.ic_launcher_background,
        boxText = "弹唱会",
        title = "【佛山】“追”刘耀国巡回演唱会-佛山站",
        date = "2025.11.29",
        cost =480,
    )




    )
val BottomShowsR =listOf<BottomShow>(
    BottomShow(
        image = R.drawable.ic_launcher_background,
        boxText = "弹唱会",
        title = "【佛山】“追”刘耀国巡回演唱会-佛山站",
        date = "2025.11.29",
        cost =480,
    ),
    BottomShow(
        image = R.drawable.ic_launcher_background,
        boxText = "弹唱会",
        title = "【佛山】“追”刘耀国巡回演唱会-佛山站",
        date = "2025.11.29",
        cost =480,
    ),
    BottomShow(
        image = R.drawable.ic_launcher_background,
        boxText = "弹唱会",
        title = "【佛山】“追”刘耀国巡回演唱会-佛山站",
        date = "2025.11.29",
        cost =480,
    ),
)


@Composable
@Preview
fun BottomColumn(modifier: Modifier = Modifier) {
    Row(modifier = Modifier
        .fillMaxWidth())
    {
        BottomShowLR(modifier,BottomShowsL)
        BottomShowLR1(modifier,BottomShowsR)
    }
}

@SuppressLint("SuspiciousIndentation")
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











        
        
        


