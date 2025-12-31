package com.example.damaidemo.screens

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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextOverflow.Companion.Ellipsis
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.damaidemo.data.data_source.ReSou
import com.example.damaidemo.data.model.ReSouExample
import com.example.damaidemo.data.model.ReSouListExample
import com.example.damaidemo.data.rebository.SearchHistoryModel
import com.example.damaidemo.ui.theme.allHorizonPadding

val roundClipInSearchScreen = 12.dp


@Composable
fun SearchScreen(modifier:Modifier=Modifier, navController: NavController){
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
                navController=navController
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
    modifier: Modifier = Modifier,

            navController: NavController
) {

    // 获取密度对象，用于dp与px的正确转换
    val density = LocalDensity.current

    val barWidth = with(density) { 320.dp.toPx() }
    val barHeight = with(density) { 18.dp.toPx() }


    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }

//    var historyItem = remember {
//        mutableStateListOf<String>()
//    }

    // 直接获取无参ViewModel（符合官方规范，无崩溃）
    val historyViewModel: SearchHistoryModel = viewModel()
    // 观察历史数据（官方推荐：collectAsState监听Flow）
    val historyList by historyViewModel.allHistory.collectAsState(initial = emptyList())


Column(modifier=modifier
    .fillMaxWidth()
) {

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .height(36.dp),
        contentAlignment = Alignment.CenterStart
    ) {
            Box(
                modifier = Modifier
            ) {

                val linearBrush = Brush.linearGradient(
                    colors = listOf(Color(0xFFFBF0EA), Color(0xFFFAE8F4), Color(0xFFF1EFFC)),
                    start = Offset(0f, 0f),
                    end = Offset(barWidth + barHeight, 0f)
                )

                Canvas(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(42.dp)
                ) {
                    val trianglePath = Path().apply {
                        moveTo(0f, barHeight)

                        quadraticTo(
                            x1 = 0f,
                            y1 = 0f,
                            x2 = barHeight,
                            y2 = 0f
                        )

                        lineTo(barWidth, 0f)

                        quadraticTo(
                            x1 = barWidth + barHeight,
                            y1 = 0f,
                            x2 = barWidth + barHeight,
                            y2 = barHeight
                        )

                        quadraticTo(
                            x1 = barWidth + barHeight,
                            y1 = barHeight * 2,
                            x2 = barWidth,
                            y2 = barHeight * 2
                        )

                        lineTo(0f, barHeight * 2)

                        close()
                    }

                    drawPath(
                        path = trianglePath,
                        brush = linearBrush,
                        // color = Color.White,
                        style = Fill
                    )
                }
        }

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
                            historyViewModel.insertHistory(searchText)
                            focusManager.clearFocus()
                        }
                    }
            )

            // 输入框
            Box(
                modifier = Modifier
                    .width(300.dp)
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
//                    cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            onSearch(searchText)
                            // 插入搜索历史到数据库
                            historyViewModel.insertHistory(searchText)
                            focusManager.clearFocus()
                        }
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                if (searchText.isEmpty()) {
                    Text(
                        text = "2026年跨年演唱会",
                        color = Color.Gray,
                        fontSize = 16.sp
                    )
                }
            }

                Text(
                    text = "取消",
                    style = TextStyle(
                        fontSize = 17.sp
                    ),
                    modifier = Modifier
                        .padding(start = 14.dp)
                        .clickable(
                            interactionSource = interactionSource,
                            indication = null
                        ) {
                            onClear()
                            //跳转逻辑
                            navController.popBackStack()
                        }
                )
        }
    }

    if(historyList.isNotEmpty()){
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
                    .clickable{  historyViewModel.deleteAllHistory()}
            )
        }

        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier=modifier
                .padding(horizontal = allHorizonPadding, vertical = 13.dp)
        ) {
            historyList.forEach { item ->
                Box(modifier = Modifier
                        .height(30.dp)
                    .clickable{ onHistoryItemClick(item.history) }
                    .background(Color(0xFFF6F6F6),RoundedCornerShape(12.dp))
                    .clip(RoundedCornerShape(12.dp))
                    )
                 {
                        Text(
                            text = item.history,
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


@Composable
fun SearchBarT(
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

    // 直接获取无参ViewModel（符合官方规范，无崩溃）
    val historyViewModel: SearchHistoryModel = viewModel()
    // 观察历史数据（官方推荐：collectAsState监听Flow）
    val historyList by historyViewModel.allHistory.collectAsState(initial = emptyList())

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
                                historyViewModel.insertHistory(searchText)
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
                                onSearch(searchText)
                                // 插入搜索历史到数据库
                                historyViewModel.insertHistory(searchText)
                                focusManager.clearFocus()
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
                if (historyList.isNotEmpty()) {
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
        if(historyList.isNotEmpty()){
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
                        .clickable{  historyViewModel.deleteAllHistory()}
                )
            }

            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier=modifier
                    .padding(horizontal = allHorizonPadding, vertical = 13.dp)
            ) {
                historyList.forEach { item ->
                    Box(modifier = Modifier
                        .height(30.dp)
                        .clickable{ onHistoryItemClick(item.history) }
                        .background(Color(0xFFF6F6F6),RoundedCornerShape(12.dp))
                        .clip(RoundedCornerShape(12.dp))
                    )
                    {
                        Text(
                            text = item.history,
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
