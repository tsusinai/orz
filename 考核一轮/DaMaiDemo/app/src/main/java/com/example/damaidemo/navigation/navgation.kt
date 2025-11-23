package com.example.wechatdemo4.navigation


//导入页面
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
import com.example.damaidemo.screens.PersonHome
import com.example.damaidemo.screens.Ticket
import com.example.damaidemo.screens.VIP


//导航信息管理
object NavRoutes {   //导航常数

    const val HOME = "home"

    const val LIVE = "live"

    const val VIP = "vip"

    const val TICKET = "ticket"
    const val PERSON_HOME = "personHome"
}

//// 2. 导航项数据模型（每个底部按钮的配置）
data class NavItem(
    val route: String, // 对应哪个路由
    val label: String, // 文字标签（资源ID）
    val icon : Int // 图标资源
)

val navItems = listOf(
    NavItem(
        route = NavRoutes.HOME,
        label = "精选",
        icon = R.drawable.jing_xan// 或使用Icons.Default.$#%@
    ),
    NavItem(
        route = NavRoutes.LIVE,
        label = "现场",
        icon = R.drawable.xian_chang// 或使用Icons.Default.$#%@
    ),
    NavItem(
        route = NavRoutes.VIP,
        label = "大麦 VIP",
        icon = R.drawable.vip// 或使用Icons.Default.$#%@
    ),
    NavItem(
        route = NavRoutes.TICKET,
        label = "票夹",
        icon = R.drawable.piao_jia// 或使用Icons.Default.$#%@
    ),
    NavItem(
        route = NavRoutes.PERSON_HOME,
        label = "我的",
        icon = R.drawable.wo_de// 或使用Icons.Default.$#%@
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

    NavigationBar(
        modifier = Modifier.height(80.dp),
        containerColor = Color.White.copy(alpha = 0.2f),  //NavigationBar：Material3 提供的底部导航容器组件

        //containerColor：设置导航栏的背景色，这里使用白色 推荐用符合 Material 设计规范 MaterialTheme.colorScheme.surface,

    ) {   //遍历 items 列表，为每个导航项创建一个 NavigationBarItem（底部导航按钮）
        items.forEach { item ->
            val isSelected = currentRoute == item.route
            //isSelected:是否选中的bool  ：当前路由==导航路由

            // 用 Box 自定义导航项，无默认动画
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
                    }
                //.padding(vertical = 10.dp),
                , contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    // 关键：给图标套 BadgedBox，添加徽章
                    Icon(
                        painter = painterResource(id = item.icon),
                        null,
                        tint = if (isSelected) Color(0xFFFA677D) else Color.Gray,
                        modifier = Modifier.size(36.dp) // 固定图标大小，不弹跳
                            .offset(x = 0.dp, y = (-2).dp)
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







