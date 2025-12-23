package com.example.damaidemo.data.model

import android.service.autofill.OnClickAction
import java.util.Date

//此文件用于存放各组件的数据模型🤔

//顶部多个card小组件
data class TopCards(
    val imageId: Int,

    val text: String,

    val onClick: (() -> Unit)? = null
)

//中上部三连卡片
data class MiddleGroup(
    val text1: String,

    val text2: String,

    val imageId : Int,

    val onClick: (() -> Unit)? = null
)

//轮播图资源
data class BannerItem(
    val imageId: Int,

    val imageRes: Int, //测试用的顺序标记

    val onClick: (() -> Unit)? = null,

    var date: Date?=null,

    var singer:String?=null,

    var location:String?=null,

    var isOpen: Boolean?=null,
)

//必看演出
data class MustToSeePicture(
    val text1: String,

    val cost: String,

    val imageID : Int, // 图标资源

    val onClick: (() -> Unit)? = null,

    val type:String,

    var date: Date?=null,

    var singer:String?=null,

    var location:String?=null,

    var isOpen: Boolean?=null,

)

//同城热点
data class SameCityPicture(
    val text1: String,

    val text2: String,
    val cost: String,

    val imageID: Int, // 图标资源

    val onClick: (() -> Unit)? = null,

    val type:String,

    var date: Date?=null,

    var singer:String?=null,

    var location:String?=null,

    var isOpen: Boolean?=null,
)

//底部演出栏
data class BottomShow(
    val image:Int,

    val boxText:String,

    val title:String,

    val date:String,

    val cost:String,

    val type: String,

    val onClick: (() -> Unit)? = null,

    var singer:String?=null,

    var location:String?=null,

    var isOpen: Boolean?=null,
)
