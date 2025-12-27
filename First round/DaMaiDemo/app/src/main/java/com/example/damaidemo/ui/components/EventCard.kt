package com.example.damaidemo.ui.components

import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.damaidemo.R
import com.example.damaidemo.data.data_source.SameCityPictures
import com.example.damaidemo.data.data_source.TopCardsLine1
import com.example.damaidemo.data.data_source.TopCardsLine2
import com.example.damaidemo.data.data_source.mustToSeeShow
import com.example.damaidemo.data.model.MustToSeePicture
import com.example.damaidemo.data.model.SameCityPicture
import com.example.damaidemo.data.model.TopCards
import com.example.damaidemo.screens.allHorizonPadding
import kotlinx.coroutines.delay
import kotlin.collections.forEach


var allHorizonPadding =8.dp
var ClipSize = 10 //卡片切割角

var CardLR1Weight = 196
var CardLR1Height = 100
//此文件用于存放各个组件🤔

//顶部卡片群中的各个小玩意
@Composable
fun TopCard(
    items: List<TopCards>,
    AnimationSize: Dp
) {
    items.forEach { items ->
        Column(
            modifier = Modifier
                .width(70.dp)
        ) {
            Image(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .height(34.dp)
                        then (Modifier.size(if (items.text == "电影") AnimationSize else 32.dp)),
                painter = painterResource(id = items.imageId),
                contentDescription = null
            )
            //Spacer(modifier = Modifier.size(2.dp))
            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                text = items.text,
                maxLines = 1, // 禁止换行，只显示1行
                //overflow = TextOverflow.Ellipsis // 超出部分显示省略号

                style = TextStyle(
                    fontSize = 14.sp
                )
            )
        }
    }
}

//--------------------顶部卡片组----------------------------

//放缩动画状态
private enum class Animation{ //创建枚举类，记录box两个状态
    Small,
    Large,
}




@Preview
@Composable
fun typesOnMainScreenOnTop(modifier: Modifier = Modifier){
    var AnimationState by remember { mutableStateOf( Animation.Small) } //创建box状态变量

    val transition = updateTransition(
        targetState = AnimationState
    )

    val size by transition.animateDp {state -> //尺寸
        when(state){
            Animation.Small ->27.dp
            Animation.Large -> 30.dp
        }
    }
    //动画协程
    LaunchedEffect(Unit) {
        while (true) {
            delay(1000)
            AnimationState = when ( AnimationState) {
                Animation.Small ->  Animation.Large
                Animation.Large ->  Animation.Small
            }
        }
    }
    Box(
        modifier = Modifier
            .padding(horizontal = allHorizonPadding)
            .fillMaxWidth()
            .clip(RoundedCornerShape(size = ClipSize.dp))
            .background(Color(255, 255, 255))
        ,
    )
    {
        Column {
            TopCardLine(TopCardsLine1,size)
            TopCardLine(TopCardsLine2,size)
        }
    }

}
@Composable
fun TopCardLine(
    Line: List<TopCards>,
    size: Dp
){
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(70.dp)
        .padding(horizontal = 15.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TopCard(Line,size)
    }
}


//--------------------必看演出----------------------------
@Composable
@Preview
fun middleLazyRow(modifier: Modifier = Modifier){
    val cardHeight_in_middleLazyRow = 220.dp
    val lazyrowHeight_in_middleLazyRow = 220.dp
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(ClipSize.dp))
            .height(cardHeight_in_middleLazyRow)
            .padding(horizontal = allHorizonPadding),

        colors = CardDefaults.cardColors(
            containerColor = Color.White, // 默认背景色（容器色）
        )
    )

    {
        Column(
            modifier= Modifier
                .padding(horizontal = 10.dp, vertical = 8.dp)
        ) {
            Text("必看演出",
                fontSize = 20.sp,
                style = TextStyle(
                    fontWeight = FontWeight(500),
                ),
            )

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(ClipSize.dp))
                    .height(lazyrowHeight_in_middleLazyRow),
                horizontalArrangement = Arrangement.spacedBy(7.dp), // 卡片之间的间距
                contentPadding = PaddingValues(vertical = 8.dp) // 列表上下内边距
            )
            {
                items(mustToSeeShow()) { item ->
                    MustToSeeShows(modifier,item)
                }
            }
        }
    }
}

@Composable
fun MustToSeeShows(
    modifier: Modifier = Modifier,
    items:MustToSeePicture
){
    val mustToSeeShowHeight = 240.dp
    val imageHeight = 100.dp
    Card(
        modifier = Modifier
            .width(75.dp)
            .height(mustToSeeShowHeight),
        colors = CardDefaults.cardColors(
            containerColor = Color.White, // 默认背景色（容器色）
        )

    ) {
        Column(
            modifier = Modifier.height(140.dp)
                .width(75.dp),
            verticalArrangement = Arrangement.spacedBy(0.dp)
        )
        {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(ClipSize.dp))
                    .fillMaxWidth()
                    .height(imageHeight)
            ){
                
                Image(
                    painter = painterResource(id = items.imageID),
                    contentDescription = items.text1,
                    modifier = Modifier
                        .clip(RoundedCornerShape(ClipSize.dp))
                        .fillMaxWidth()
                        .height(imageHeight)
                )
                Box(modifier = Modifier
                    .align(Alignment.TopEnd)
                    .offset(-5.dp,5.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFF000000))
                ){
                    Text(
                        text = items.type,
                        style = TextStyle(
                            fontSize = 9.sp,
                        ),
                        color = Color(0xFFFFFFFF),
                        modifier = Modifier
                            .padding(start = 4.dp, end = 4.dp,top =1.dp, bottom = 2.dp)
                    )
                }
            }

            //不要打太多字球球了
            Text(
                text = items.text1,
                maxLines = 2, // 禁止换行，只显示2行
                overflow = TextOverflow.Ellipsis, // 超出部分显示省略号
                style = TextStyle(
                    fontWeight = Bold,
                    fontSize = 12.sp
                ),
                modifier=Modifier
                    .padding(top = 3.dp)
            )
         }
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
            ) {
            Text(
                text = "￥${items.cost}",
                color = Color(0xFFFA677D),
                style = TextStyle(
                    fontWeight = Bold,
                    fontSize = 16.sp
                    ),
                modifier = Modifier
                    .offset(0.dp,-3.dp)
                )
            Text(
                text = " 起",
                color = Color.Gray,
                style = TextStyle(
                    fontSize = 13.sp
                ),
            )
             }
        }
    }


@Composable
@Preview
fun middleLazyRow1(modifier: Modifier = Modifier){

    val linearGradient1 = Brush.linearGradient(
        colors = listOf(Color(0xFFFFFFFF), Color(0xFFDCE1FB)), // 紫色 → 青色
        end = Offset(200f, 0f),
        start = Offset(200f, Float.POSITIVE_INFINITY), // 水平渐变
    )

    val cardHeight_in_middleLazyRow = 230.dp
    val lazyrowHeight_in_middleLazyRow = 230.dp

    // 1:定义渐变（线性渐变：从左到右）
    val linearGradient = Brush.linearGradient(
        colors = listOf(Color(0xFF2C9FF8), Color(0xFFBA90F3)), // 紫色 → 青色
        start = Offset(0f, 0.5f),
        end = Offset(Float.POSITIVE_INFINITY, 0.5f), // 水平渐变
    )

    val textMeasurer = rememberTextMeasurer() // 2. 文字测量工具（用于获取文字尺寸和布局）

    Card(
        colors = CardDefaults.cardColors(
         containerColor = Color.Transparent),

        modifier = Modifier
            .fillMaxWidth()
            .height(cardHeight_in_middleLazyRow)
            .padding(horizontal = allHorizonPadding)
            .background(linearGradient1,RoundedCornerShape(ClipSize.dp))
            .clip(RoundedCornerShape(ClipSize.dp))
    )

    {
        Column(
            modifier= Modifier
            .padding(horizontal = 10.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(vertical = 2.dp),
                horizontalArrangement = Arrangement.Center

            ){ Canvas(
                modifier = Modifier
                    .wrapContentSize() // 自适应文字尺寸，不额外占空间

            ) {
                // 3. 测量“同城热点”文字的尺寸和布局
                val textLayoutResult: TextLayoutResult = textMeasurer.measure(
                    text = "同城热点",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif,
                        fontStyle = FontStyle.Italic, // 斜体
                    )
                )

                // 4. 绘制文字：直接用渐变作为文字画笔
                drawText(
                    textLayoutResult = textLayoutResult,
                    brush = linearGradient // 关键：文字颜色用渐变，而非纯色
                )
            }

                Text("本周热点推荐",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = Bold
                    ),
                    modifier=Modifier
                        .offset(90.dp,0.dp)
                )
            }
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(ClipSize.dp))
                    .height(lazyrowHeight_in_middleLazyRow),
                horizontalArrangement = Arrangement.spacedBy(7.dp), // 卡片之间的间距
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

@Composable
fun SameCityShows(
    modifier: Modifier = Modifier,
    items: SameCityPicture
){
    val mustToSeeShowHeight = 250.dp
    val imageHeight = 100.dp
    Card(
        modifier = Modifier
            .width(75.dp)
            .height(mustToSeeShowHeight),
        colors = CardDefaults.cardColors(
            containerColor = Color.White, // 默认背景色（容器色）
        )

    ){
        Column(
            modifier = Modifier.height(300.dp)
                .width(75.dp),
            verticalArrangement = Arrangement.spacedBy(0.dp)
        ) {
           Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(ClipSize.dp))
                    .fillMaxWidth()
                    .height(imageHeight)
            ){

                Image(
                    painter = painterResource(id = items.imageID),
                    contentDescription = items.text1,
                    modifier = Modifier
                        .clip(RoundedCornerShape(ClipSize.dp))
                        .fillMaxWidth()
                        .height(imageHeight)
                )
                Box(modifier = Modifier
                    .align(Alignment.TopEnd)
                    .offset(-5.dp,5.dp)
                    .clip(RoundedCornerShape(3.dp))
                    .background(Color(0xFF000000))
                ){
                    Text(
                        text = items.type,
                        style = TextStyle(
                            fontSize = 9.sp,
                        ),
                        color = Color(0xFFFFFFFF),
                        modifier = Modifier
                            .padding(start = 4.dp, end = 4.dp,top =1.dp, bottom = 2.dp)
                    )
                }
            }

            Text(
                text = items.text1,
                maxLines = 2, // 禁止换行，只显示2行
                overflow = TextOverflow.Ellipsis, // 超出部分显示省略号
                style = TextStyle(
                    fontWeight = Bold,
                    fontSize = 12.sp
                ),
                modifier=Modifier
                    .padding(top = 3.dp)
            )


            Text(text=items.text2,
                fontWeight = FontWeight(200),
                maxLines = 1, // 禁止换行，只显示2行
                overflow = TextOverflow.Ellipsis, // 超出部分显示省略号
                color = Color.Gray,

                style = TextStyle(
                    fontWeight = Bold,
                    fontSize = 12.sp
                ),
            )

            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "￥${items.cost}",
                    color = Color(0xFFFA677D),
                    style = TextStyle(
                        fontWeight = Bold,
                        fontSize = 16.sp
                    ),
                    modifier = Modifier
                        .offset(0.dp,-1.dp)
                )
                Text(
                    text = " 起",
                    color = Color.Gray,
                    style = TextStyle(
                        fontSize = 13.sp
                    ),
                )
            }
        }
    }
}


//==============两个卡片=========================
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
                            .border(1.dp,Color.Red,CircleShape)
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

        Spacer(modifier = Modifier.size(6.dp))

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
                        Pictures(imageID =  R.drawable.si_hai, type = "演唱会")
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
                                    text = "   ￥380起   ",
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
                        Pictures(imageID =  R.drawable.yu_mao_qiu, type = "演唱会")
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
                                    text = "    ￥380起    ",
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
fun Pictures(imageHeight: Dp =100.dp,imageID:Int,type:String){
Box(
modifier = Modifier
.fillMaxSize()
.height(imageHeight)
){

    Image(
        painter = painterResource(id = imageID),
        contentDescription = null,
        modifier = Modifier
            .fillMaxSize()
            .height(imageHeight)
    )
    Box(modifier = Modifier
        .align(Alignment.TopEnd)
        .offset(-5.dp,5.dp)
        .clip(RoundedCornerShape(3.dp))
        .background(Color(0xFF000000))
    ){
        Text(
            text = type,
            style = TextStyle(
                fontSize = 9.sp,
            ),
            color = Color(0xFFFFFFFF),
            modifier = Modifier
                .padding(1.dp)

        )
    }
}
    }