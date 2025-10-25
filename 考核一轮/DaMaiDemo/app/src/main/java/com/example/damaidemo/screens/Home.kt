package com.example.damaidemo.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.damaidemo.CardLR1Height
import com.example.damaidemo.CardLR1Weight
import com.example.damaidemo.CardMHeight
import com.example.damaidemo.CardMWeight
import com.example.damaidemo.ClipSize
import com.example.damaidemo.R
import com.example.damaidemo.TypesOnMainScreenOnTopHeight


@Composable
fun Home(navController: NavController){
    val searchQuery = remember { mutableStateOf("") }
    val onSearchQueryChange: (String) -> Unit = { newQuery ->
        searchQuery.value = newQuery}


    Box(
        modifier = Modifier.fillMaxSize().
        background(color = Color(242,242,242))
        ,)

    Column()
    {
        TopBar("?",onSearchQueryChange)
        Spacer(modifier = Modifier.size(7.dp))
        MainScreen()
        Spacer(modifier = Modifier.size(7.dp))
        CardLR1_MainGroupsOnMainScreen()
        MiddleGroups(MiddleGroups)
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
fun MainScreen(){
    LazyColumn(
        modifier = Modifier
    ) {
        item { // 添加 item 作用域
            TypesOnMainScreenOnTop()
        }
    }
}


@Preview
@Composable
fun TypesOnMainScreenOnTop(){
    Box(
        modifier = Modifier.
        height(TypesOnMainScreenOnTopHeight.dp).
        padding(horizontal = 5.dp).
        width(800.dp).
        clip(RoundedCornerShape(size = ClipSize.dp)).
        background(Color(255,255,255))
        ,
    )

}


@Preview
@Composable
fun CardLR1_MainGroupsOnMainScreen(){
    Row(
        modifier = Modifier.
        fillMaxWidth().
        padding(3.dp)
        ,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    )
    {
        Card(
            modifier = Modifier.
            height(CardLR1Height.dp).
            width(CardLR1Weight.dp)
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

        Card(
            modifier = Modifier.
            height(CardLR1Height.dp).
            width(CardLR1Weight.dp)
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
@Composable
//@Preview
fun MiddleGroups(
    items: List<MiddleGroup>,
){

    Row(
        modifier = Modifier.
        fillMaxWidth().
        padding(3.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    )
    {
        items.forEach { item ->
            Card(
                modifier = Modifier.
                height(CardMHeight.dp).
                width(CardMWeight.dp).
                background(Color(255,255,255))
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

