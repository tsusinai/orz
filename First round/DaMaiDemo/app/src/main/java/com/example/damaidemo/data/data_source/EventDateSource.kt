package com.example.damaidemo.data.data_source

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.damaidemo.R
import com.example.damaidemo.data.model.BannerItem
import com.example.damaidemo.data.model.BottomShow
import com.example.damaidemo.data.model.MiddleGroup
import com.example.damaidemo.data.model.MustToSeePicture
import com.example.damaidemo.data.model.SameCityPicture
import com.example.damaidemo.data.model.TopCards
import com.example.damaidemo.data.rebository.SearchHistoryDao
import com.example.damaidemo.data.model.SearchHistory
import com.example.damaidemo.data.model.TopToolsExample
import com.example.wechatdemo4.navigation.NavRoutes


//此处用于存放数据模型中的静态数据🤔

//顶部小组件数据（第一行）
val TopCardsLine1=listOf<TopCards>(
    TopCards(imageId = R.drawable.main_top_0, text = "演唱会"),
    TopCards(imageId = R.drawable.main_top_1, text = "音乐节"),
    TopCards(imageId = R.drawable.main_top_2, text = "LiveHouse"),
    TopCards(imageId = R.drawable.main_top_3, text = "话剧音乐剧"),
    TopCards(imageId = R.drawable.main_top_4, text = "脱口秀"),
)

val TopCardsLine2=listOf<TopCards>(
    TopCards(imageId = R.drawable.main_top_5, text = "展览"),
    TopCards(imageId = R.drawable.main_top_6, text = "旅游"),
    TopCards(imageId = R.drawable.main_top_7, text = "电影"),
    TopCards(imageId = R.drawable.main_top_9, text = "舞蹈舞剧"),
    TopCards(imageId = R.drawable.main_top_8, text = "全部"),
)

//中上部三连卡片数据
val MiddleGroups = listOf(
    MiddleGroup(text1 = "演出日历", text2 = "按时间选演出", imageId = R.drawable.ri_li_test),
    MiddleGroup(text1 = "大麦演出榜", text2 = "你的观影指南", imageId = R.drawable.top_test),
    MiddleGroup(text1 = "大麦团购", text2 = "超低价随时退", imageId = R.drawable.enter),)

//轮播图资源
val bannerList = listOf(
    BannerItem(1, R.drawable.luo_bo_1, ),
    BannerItem(2, R.drawable.luo_bo_2, ),
    BannerItem(3, R.drawable.luo_bo_3, ),
    BannerItem(4, R.drawable.luo_bo_4, ),
    BannerItem(5, R.drawable.luo_bo_1, ),
    BannerItem(6, R.drawable.luo_bo_2, ),
    BannerItem(7, R.drawable.luo_bo_3, ),
    BannerItem(8, R.drawable.luo_bo_4, ),
    BannerItem(9, R.drawable.luo_bo_3, ),
    BannerItem(10, R.drawable.luo_bo_4, ),

)

val personBannerList = listOf(
    BannerItem(1, R.drawable.luo_bo_1, ),
    BannerItem(2, R.drawable.luo_bo_2, ),
)


//必看演出资源 函数实现❗
fun mustToSeeShow(): List<MustToSeePicture> {
    return listOf<MustToSeePicture>(
        MustToSeePicture(text1 = "周柏豪「CHAPTER IV」粉丝见面会巡演-福州站", cost = "380", imageID = R.drawable.zou_bo_hao,null,"见面会"
        ),
        MustToSeePicture(text1 = "林忆莲《回响 Resonance》2025 巡回演唱会-福州站", cost = "280", imageID = R.drawable.lin_yi_lian,null,"演唱会"
        ),
        MustToSeePicture(text1 = "2025张韶涵觅光巡回演唱会-泉州站", cost = "388", imageID = R.drawable.zhang_sao_han,null,"演唱会"
        ),
        MustToSeePicture(text1 = "2026跨年演唱会「最美好的相遇」—福州站", cost = "128", imageID = R.drawable.kuanian,null,"演唱会"
        ),
        MustToSeePicture(text1 = "舞蹈诗剧《只此青绿》——舞绘《千里江山图》", cost = "50", imageID = R.drawable.zhi_ci_qing_lv,null,"见面会"
        ),
        MustToSeePicture(text1 = "开心麻花首部原创爆笑新喜剧《整个喜剧》", cost = "69", imageID = R.drawable.kai_xin_ma_hua,null,"歌舞剧"
        ),
        MustToSeePicture(text1 = "四海福临2025德云社高峰·栾云平相声专场演出-福州站", cost = "180", imageID = R.drawable.gao_feng,null,"脱口秀"
        ),
        MustToSeePicture(text1 = "菊次郎的夏天—久石让轻音乐之旅钢琴音乐会", cost = "50", imageID = R.drawable.jiu_shi_rang,null,"音乐会"
        ),
        MustToSeePicture(text1 = "2026《黄西有梗脱口秀》让你一次笑个够", cost = "100", imageID = R.drawable.huang_xi,null,"脱口秀"
        ),
    )
}

//同城演出 函数实现❗
fun SameCityPictures(): List<SameCityPicture> {
    return listOf< SameCityPicture>(
        SameCityPicture(text1 = "毛豆、翟佳宁、思宇、雷哥、佺儿、宗宝 | 汇笑喜剧巡演 | 三个火呛手和他的朋友们·福州站", text2 = "人气脱口秀" ,cost="180", imageID = R.drawable.huo_qiang_shou,null,"见面会"
        ),
        SameCityPicture(text1 = "高杨、张会芳主演·外百老汇经典音乐剧《长腿叔叔》中文版", text2 = "抢手音乐剧" ,cost="180", imageID = R.drawable.gao_yang,null,"见面会"
        ),
        SameCityPicture(text1 = "会说笑喜剧脱口秀拼盘-南昌站（刘仁铖、小奇、真勇、张灏喆、李梦杰）", text2 = "抢手脱口秀" ,cost="162", imageID = R.drawable.hui_shuo_xiao,null,"见面会"
        ),
        SameCityPicture(text1 = "Z纪元巅峰音乐节", text2 = "火爆音乐节" ,cost="199", imageID = R.drawable.z_jiyuan,null,"见面会"
        ),
        SameCityPicture(text1 = "2025不止音乐节", text2 = "人气音乐节" ,cost="299", imageID = R.drawable.bu_zhi,null,"见面会"
        ),
        SameCityPicture(text1 = "中国羽毛球俱乐部超级联赛", text2 = "火爆羽毛球" ,cost="58", imageID = R.drawable.yu_mao_qiu,null,"见面会"
        ),
        SameCityPicture(text1 = "中国羽毛球俱乐部超级联赛", text2 = "火爆羽毛球" ,cost="58", imageID = R.drawable.yu_mao_qiu,null,"见面会"
        ),
        SameCityPicture(text1 = "2025 打扰一下乐团「ELEVEN」全国巡演-杭州站", text2 = "热门LiveHouse" ,cost="228", imageID = R.drawable.da_rao_yi_xia,null,"见面会"
        ),



    )
}

//底部演出（左半区域

val BottomShowsL =listOf<BottomShow>(
    BottomShow(image = R.drawable.gao_yang, boxText = "弹唱会", title = "【佛山】“追”巡回演唱会-佛山站",
        date = "2025.11.29", cost ="480","演唱会"
    ),
    BottomShow(image = R.drawable.yu_mao_qiu, boxText = "弹唱会", title = "【佛山】“追”巡回演唱会-佛山站",
        date = "2025.11.29", cost ="480","演唱会"
    ),
    BottomShow(image = R.drawable.gao_yang, boxText = "弹唱会", title = "【佛山】“追”巡回演唱会-佛山站",
        date = "2025.11.29", cost ="480","演唱会"
    )
)


val BottomShowsR =listOf<BottomShow>(
    BottomShow(
        image = R.drawable.zou_can_xong, boxText = "弹唱会", title = "【佛山】“追”巡回演唱会-佛山站",
        date = "2025.11.29", cost ="480","演唱会"
    ),
    BottomShow(
        image = R.drawable.zhi_ci_qing_lv, boxText = "弹唱会", title = "【佛山】“追”巡回演唱会-佛山站",
        date = "2025.11.29", cost ="480","演唱会"
    ),
    BottomShow(
        image = R.drawable.si_hai, boxText = "弹唱会", title = "【佛山】“追”巡回演唱会-佛山站",
        date = "2025.11.29", cost ="480","演唱会"
    ),
)

//我的上部工具栏数据
val TopTools = listOf<TopToolsExample>(
    TopToolsExample(R.drawable.person_top_1,"我的订单", NavRoutes.My_ORDER),
    TopToolsExample(R.drawable.person_top_2,"优惠券",NavRoutes.My_ORDER),
    TopToolsExample(R.drawable.person_top_3,"观演人",NavRoutes.My_ORDER),
    TopToolsExample(R.drawable.person_top_4,"收货地址",NavRoutes.My_ORDER),
)


@Database(entities = [SearchHistory::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun searchHistoryDao(): SearchHistoryDao

    companion object {
        // 官方推荐：volatile保证多线程可见性
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, // 官方强调：用ApplicationContext避免内存泄漏
                    AppDatabase::class.java,
                    "app_database" // 数据库文件名
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}