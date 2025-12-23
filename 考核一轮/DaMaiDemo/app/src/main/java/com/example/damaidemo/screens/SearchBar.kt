package com.example.damaidemo.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.snap
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextOverflow.Companion.Ellipsis
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val roundClipInSearchScreen = 12.dp

data class ReSouExample(
    val ReSou: List<ReSouListExample>,
    val nameOfList: String,
    val linerBrush: Brush,
    val textBrush: Brush,
)

data class ReSouListExample(
    val num:Int,
    val name:String,
)

val QuanGuoReSouList=listOf<ReSouListExample>(
    ReSouListExample(1,"梓俞"),
    ReSouListExample(2,"王力宏"),
    ReSouListExample(3,"汪苏泷"),
    ReSouListExample(4,"马嘉祺"),
    ReSouListExample(5,"邓紫棋"),
    ReSouListExample(6,"张杰"),
    ReSouListExample(7,"薛之谦"),
    ReSouListExample(8,"张韶涵"),
    ReSouListExample(9,"王源"),
    ReSouListExample(10,"蔡徐坤")
)

val TongChengWanREList=listOf<ReSouListExample>(
    ReSouListExample(1,"十个勤天 一起开麦吧"),
    ReSouListExample(2,"周传健 福州"),
    ReSouListExample(3,"张信哲 张韶涵"),
    ReSouListExample(4,"夏日入侵企画 福州小剧场"),
    ReSouListExample(5,"《疯狂动物城2》"),
    ReSouListExample(6,"二狗个人互动演出"),
    ReSouListExample(7,"一生必看现象级演出"),
    ReSouListExample(8,"ACG 直到世界尽头"),
    ReSouListExample(9,"秦朝觉醒XR大型沉浸式"),
    ReSouListExample(10,"福州永泰欧乐堡")
)

val DianYinReSouList=listOf<ReSouListExample>(
    ReSouListExample(1,"梓俞"),
    ReSouListExample(2,"王力宏"),
    ReSouListExample(3,"汪苏泷"),
    ReSouListExample(4,"马嘉祺"),
    ReSouListExample(5,"邓紫棋"),
    ReSouListExample(6,"张杰"),
    ReSouListExample(7,"薛之谦"),
    ReSouListExample(8,"张韶涵"),
    ReSouListExample(9,"王源"),
    ReSouListExample(10,"蔡徐坤")
)

val ReSou = listOf<ReSouExample>(
    ReSouExample(
        QuanGuoReSouList,
        "全国热搜榜",
        Brush.linearGradient(
        colors = listOf(Color(0xFFFFEDEC),Color(0xFFFFFFFF)),
        start = Offset(x=0.5f,y=0f),
        end = Offset(x= 0f,y=Float.POSITIVE_INFINITY)
),
        Brush.linearGradient(
        colors = listOf(Color(0xFFFE6382),Color(0xFFFF9140)),
        start = Offset(x=0f,y=0f),
        end = Offset(x= Float.POSITIVE_INFINITY,y=Float.POSITIVE_INFINITY)
)
        ),
    ReSouExample(
        TongChengWanREList,
        "同城玩乐榜",
        Brush.linearGradient(
            colors = listOf(Color(0xFFEEEBFC),Color(0xFFFFFFFF)),
            start = Offset(x=0.5f,y=0f),
            end = Offset(x= 0f,y=Float.POSITIVE_INFINITY)
        ),
        Brush.linearGradient(
            colors = listOf(Color(0xFF877EEA),Color(0xFFBD9CE9)),
            start = Offset(x=0f,y=0f),
            end = Offset(x= Float.POSITIVE_INFINITY,y=Float.POSITIVE_INFINITY)
        )
    ),
    ReSouExample(
        DianYinReSouList,
        "电影热搜榜",
        Brush.linearGradient(
            colors = listOf(Color(0xFFFEEBF5),Color(0xFFFFFFFF)),
            start = Offset(x=0.5f,y=0f),
            end = Offset(x= 0f,y=Float.POSITIVE_INFINITY)
        ),
        Brush.linearGradient(
            colors = listOf(Color(0xFFF1669E),Color(0xFFF88AC8)),
            start = Offset(x=0f,y=0f),
            end = Offset(x= Float.POSITIVE_INFINITY,y=Float.POSITIVE_INFINITY)
        )
    ),
)




@Preview
@Composable
fun SearchScreen(modifier:Modifier=Modifier){
    var searchText by remember { mutableStateOf("") }

    Box(modifier=modifier
        .fillMaxSize()
        .background(Color.White)
    ){
        Column(
            modifier=modifier
                .fillMaxSize()
        ){
            SearchBar(
                searchText = searchText,
                onSearchTextChange = { searchText = it },
                onSearch = { query ->
                    // 这里处理搜索逻辑
                    println("执行搜索: $query")
                },
                onClear = { searchText = "" },
                onHistoryItemClick = { text ->
                    // 历史标签点击回调，更新搜索文本
                    searchText = text
                },
            )
            MiddleLazy(modifier =modifier,ReSou)
        }
    }
}


@Composable
fun SearchBar(
    searchText: String,
    onSearchTextChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    onClear: () -> Unit,
    onHistoryItemClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }
    var historyItem = remember {
        mutableStateListOf<String>()
    }

Column(modifier=modifier
    .fillMaxWidth()
) {

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .height(36.dp)
            .clip(RoundedCornerShape(18.dp))
            .background(Color.White),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 搜索图标
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "搜索",
                tint = Color.Gray,
                modifier = Modifier
                    .size(20.dp)
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null
                    ) {
                        if (searchText.isNotEmpty()) {
                            onSearch(searchText)
                            focusManager.clearFocus()
                        }
                    }
            )

            // 输入框
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp)
            ) {
                BasicTextField(
                    value = searchText,
                    onValueChange = onSearchTextChange,
                    textStyle = TextStyle(
                        fontSize = 16.sp,
                        color = Color.Black
                    ),
                    singleLine = true,
                    cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            if (searchText.isNotBlank() && !historyItem.contains(searchText)) { //避免重复标签和空
                                historyItem.add(0, searchText) // 新增记录插入到首位
                            }

                            if (searchText.isNotEmpty()) {
                                onSearch(searchText)
                                focusManager.clearFocus()
                            }
                        }
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                // 占位文本
                if (searchText.isEmpty()) {
                    Text(
                        text = "搜索影视剧",
                        color = Color.Gray,
                        fontSize = 16.sp
                    )
                }
            }

            // 清除按钮
            if (searchText.isNotEmpty()) {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = "清除",
                    tint = Color.Gray,
                    modifier = Modifier
                        .size(20.dp)
                        .clickable(
                            interactionSource = interactionSource,
                            indication = null
                        ) {
                            onClear()
                        }
                )
            }
        }
    }
    if(historyItem.isNotEmpty()){
        Row(modifier=modifier
            .fillMaxWidth()
            .height(30.dp)
            .padding(horizontal = allHorizonPadding),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(text = "搜索历史",
                style = TextStyle(
                    fontSize = 17.sp
                )
            )
            Icon(
                imageVector = Icons.Default.Clear,
                contentDescription = "清除",
                tint = Color.Gray,
                modifier = Modifier
                    .size(20.dp)
                    .clickable{historyItem.clear()}
            )
        }

        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier=modifier
                .padding(horizontal = allHorizonPadding, vertical = 13.dp)
        ) {
            historyItem.forEach { items ->
                Box(modifier = Modifier
                        .height(30.dp)
                    .clickable{ onHistoryItemClick(items) }
                    .background(Color(0xFFF6F6F6),RoundedCornerShape(12.dp))
                    .clip(RoundedCornerShape(12.dp))
                    )
                 {
                        Text(
                            text = items,
                            color = Color(0xFF000000),
                            fontSize = 14.sp,
                            modifier = Modifier
                                .align(Alignment.Center)
                                .padding(horizontal = 10.dp)
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun SearchBarInSearchHome(modifier: Modifier =Modifier){
    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    var historyItem = remember {
        mutableStateListOf<String>("S","AAAAAAAA","BBBBBBBBB",
                "ccccccc")
    }

    val transitionState = remember {
        MutableTransitionState(false).apply {
            targetState = active // 与 active 状态同步
        }
    }

//点击时active为true
             SearchBar(
                modifier=modifier
                    .fillMaxWidth()
                    .height(60.dp),

                query = text,

                onQueryChange = {
                    text = it
                },

                onSearch = {
                    if (it.isNotBlank() && !historyItem.contains(it)) { //避免重复标签和空
                        historyItem.add(0, it) // 新增记录插入到首位
                    }
                    text = "" // 清空搜索框
                    active = false // 收起搜索栏
                },

                active = active,

                onActiveChange = {
                    active = it
                },

                placeholder = {
                    Text("搜索")
                },//搜索栏状态

                // 可选：添加左侧搜索图标（更实用）
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "搜索图标",
                        tint = Color.Gray,
                        modifier = Modifier.size(24.dp)
                    )
                },

                // 可选：添加右侧清空图标（更实用）
                trailingIcon = {
                    if (text.isNotBlank()) {
                        IconButton(onClick = { text = "" }) {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = "清空输入",
                                tint = Color.Gray,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                },
                shape = RoundedCornerShape(30.dp),
                colors = SearchBarDefaults.colors(
                    containerColor = Color.Green,
                ),
                enabled = true

            )



            {
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier=modifier
                ) {
                historyItem.forEach{ items ->
                    Button(
                        onClick = {text = items
                            // 可选：自动触发搜索
                        },
                        modifier = Modifier
                            .height(30.dp),
                        shape = RoundedCornerShape(20.dp), // 按钮圆角
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFBFC9D8)// 按钮粉色（接近截图）
                        )
                    ) {}
                    Text(
                        text = items,
                        color = Color(0xFFFFFFFF),
                        fontSize = 14.sp,
                        modifier = Modifier,
                    )
            }
        }

    }
}



@Composable
fun MiddleLazy(modifier: Modifier =Modifier, items: List<ReSouExample>){
    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = allHorizonPadding),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ){
        items(items) { items->
            MiddleLazyQuanGuo(modifier =modifier,
                items.nameOfList,
                items.ReSou,
            items.linerBrush,
                items.textBrush
            )
        }
    }
}

@Composable
fun MiddleLazyQuanGuo(
    modifier: Modifier =Modifier,
    name:String,
    items: List<ReSouListExample>,
    linerBrush: Brush,
    textBrush: Brush
){

    val textMeasurer = rememberTextMeasurer()

    Box(
        modifier=modifier
            .height(420.dp)
            .width(150.dp)
            .background(linerBrush, RoundedCornerShape(roundClipInSearchScreen))
            .border(1.dp, Color(0xFFFDE0DF), RoundedCornerShape(roundClipInSearchScreen))
            .clip(RoundedCornerShape(roundClipInSearchScreen))
    ){
        Column(
            modifier=modifier
                .fillMaxSize()
        ){
            Canvas(
                modifier = Modifier
                    .wrapContentSize() // 自适应文字尺寸，不额外占空间
                    .fillMaxWidth()
                    .height(40.dp)
                    .padding(horizontal = allHorizonPadding, vertical = 8.dp)
            ) {
                // 3. 测量“同城热点”文字的尺寸和布局
                val textLayoutResult: TextLayoutResult = textMeasurer.measure(
                    text = name,
                    style = TextStyle(
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif,
//                        fontStyle = FontStyle.Italic, // 斜体
                    )
                )

                // 4. 绘制文字：直接用渐变作为文字画笔
                drawText(
                    textLayoutResult = textLayoutResult,
                    brush = textBrush// 关键：文字颜色用渐变，而非纯色
                )
            }
            Column(
                modifier=modifier
                    .fillMaxWidth()
                    .padding(horizontal = 6.dp)
                    .background(Color.White, RoundedCornerShape(6.dp))
                    .clip(RoundedCornerShape(6.dp))
            )
            {
                items.forEach {items->
                    Row(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text(text = "${items.num}")
                        Text(text = items.name,
                            style = TextStyle(
                                    fontSize=15.sp
                                ),
                            maxLines = 1,
                            overflow = Ellipsis,
                            modifier = modifier
                                .width(100.dp)
                            )
                    }
                }
            }
        }
    }
}



@Preview
@Composable
fun MiddleLazyTongCheng(){

}

@Preview
@Composable
fun MiddleLazyDianYin(){

}
