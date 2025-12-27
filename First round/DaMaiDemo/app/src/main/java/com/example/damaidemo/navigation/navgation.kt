package com.example.wechatdemo4.navigation


//导入页面
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.damaidemo.R
import com.example.damaidemo.screens.Home
import com.example.damaidemo.screens.Live
import com.example.damaidemo.screens.MyOrders
import com.example.damaidemo.screens.PersonHome
import com.example.damaidemo.screens.SearchScreen
import com.example.damaidemo.screens.Ticket
import com.example.damaidemo.screens.VIP
import kotlinx.coroutines.delay


//导航信息管理
object NavRoutes {   //导航常数

    const val HOME = "home"

    const val LIVE = "live"

    const val VIP = "vip"

    const val TICKET = "ticket"
    const val PERSON_HOME = "personHome"

    const val My_ORDER = "myOrder"

    const val SEARCH_SCREEN = "searchScreen"
}

//// 2. 导航项数据模型（每个底部按钮的配置）
data class NavItem(
    val route: String, // 对应哪个路由
    val label: String, // 文字标签（资源ID）
    val icon : Int, // 图标资源
    val selectIcon:Int //被选择后图标资源
)

val navItems = listOf(
    NavItem(
        route = NavRoutes.HOME,
        label = "精选",
        icon = R.drawable.bottom_1,// 或使用Icons.Default.$#%@
        selectIcon = R.drawable.bottom_select_1
    ),
    NavItem(
        route = NavRoutes.SEARCH_SCREEN,
        label = "现场",
        icon = R.drawable.bottom_2,// 或使用Icons.Default.$#%@
        selectIcon = R.drawable.bottom_select_2
    ),
    NavItem(
        route = NavRoutes.My_ORDER,
        label = "大麦 VIP",
        icon = R.drawable.bottom_5,// 或使用Icons.Default.$#%@
        selectIcon =  R.drawable.bottom_select_5
    ),
    NavItem(
        route = NavRoutes.TICKET,
        label = "票夹",
        icon = R.drawable.bottom_3,// 或使用Icons.Default.$#%@
        selectIcon = R.drawable.bottom_select_3
    ),
    NavItem(
        route = NavRoutes.PERSON_HOME,
        label = "我的",
        icon = R.drawable.bottom_4,// 或使用Icons.Default.$#%@
        selectIcon =  R.drawable.bottom_select_4
    ),

)

//导航容器层：
@Composable  //导航控制
fun MyNavHost(navController: NavHostController,modifier: Modifier){ Scaffold(
        bottomBar = {
            CustomBottomNavigation(
                navController = navController,
                items = navItems,
                modifier = modifier,
            )
        }
    ) { innerPadding -> //innerPadding内部作用域于
        NavHost(
            navController = navController,
            startDestination = NavRoutes.HOME, //主页面
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(NavRoutes.HOME) {
                Home(navController)  //目标地址：主界面
            }
            composable(NavRoutes.LIVE) {
                Live(navController)  //目标地址：主界面
            }
            composable(NavRoutes.VIP) {
                VIP(navController)  //目标地址：主界面
            }
            composable(NavRoutes.TICKET) {
                Ticket(navController)  //目标地址：主界面
            }
            composable(NavRoutes.PERSON_HOME) {
                PersonHome(navController)  //目标地址：主界面
            }
            composable(NavRoutes.My_ORDER) {
                MyOrders(modifier=modifier)  //目标地址：主界面
            }
            composable(NavRoutes.SEARCH_SCREEN) {
                SearchScreen(modifier=modifier)  //目标地址：主界面
            }
        }
    }
}

//导航ui层
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomBottomNavigation(
    navController: NavController,
    items: List<NavItem>,
    modifier: Modifier
) {




    // 获取当前导航状态
    val navBackStackEntry by navController.currentBackStackEntryAsState() //监听导航控制器的回退栈状态，返回当前显示页面的导航条目
    val currentRoute = navBackStackEntry?.destination?.route //获取当前页面的路由（如 "home" 或 "pas"），用于判断哪个导航项应该被选中

    // 2. 核心判断：当前路由是否在items的路由列表中
    val isCurrentRouteInList = items.any { it.route == currentRoute }
    if (isCurrentRouteInList){
    NavigationBar(
        modifier = Modifier.height(80.dp),
        containerColor = Color.White.copy(alpha = 0.2f),  //NavigationBar：Material3 提供的底部导航容器组件

        //containerColor：设置导航栏的背景色，这里使用白色 推荐用符合 Material 设计规范 MaterialTheme.colorScheme.surface,

    ) {   //遍历 items 列表，为每个导航项创建一个 NavigationBarItem（底部导航按钮）
        items.forEach { item ->
            val isSelected = currentRoute == item.route
            //isSelected:是否选中的bool  ：当前路由==导航路由

            val size by animateDpAsState(
                targetValue = if (isSelected) 33.dp else 30.dp,
                animationSpec = keyframes {
                    durationMillis = 600
                    35.dp at 300
                    30.dp at 600
                }
            )

            // 用 Box 自定义导航项
            Box(
                modifier = Modifier
                    .weight(0.1f) // 平均分配宽度
                    .padding(3.dp)
                    .clickable {
                        // 导航逻辑（与之前保持一致）
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                //.padding(vertical = 10.dp),
                 contentAlignment = Alignment.Center,

            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                    Icon(
                        painter = painterResource(id = if (!isSelected) item.icon else item.selectIcon),
                        null,
                        modifier = Modifier.size(size)
                            .offset(x = 0.dp, y = (-2).dp)
                            .padding(3.dp)
                    )

                    Text(
                        text = item.label,
                        color = if (isSelected) Color(0xFFFA677D) else Color.Gray,
                        fontSize = 12.sp,
                        modifier=Modifier
                        .offset(x = 0.dp, y = (-2).dp)
                    )

                }
            }
                }
            }
        }
    }







