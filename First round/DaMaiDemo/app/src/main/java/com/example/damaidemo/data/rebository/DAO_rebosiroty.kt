package com.example.damaidemo.data.rebository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.damaidemo.data.data_source.AppDatabase
import com.example.damaidemo.data.model.SearchHistory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import android.app.Application
import android.content.Context
import androidx.room.OnConflictStrategy
import com.example.damaidemo.MyApp


@Dao
interface SearchHistoryDao{
    //增加历史
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg history: SearchHistory) //vararg可变长操作：批量删除

    // 查询所有，返回Flow（自动感知数据变化，适配Compose重组）
    @Query("SELECT * FROM search_history ORDER BY id DESC")
    fun getAll(): Flow<List<SearchHistory>>

    /**
     * 删除search_history中的所有数据
     * suspend关键字：适配协程，避免主线程阻塞
     * SQL说明：DELETE FROM 表名 直接清空表数据，表结构保留
     */

    @Query("DELETE FROM search_history")
    suspend fun deleteAll()

}





class SearchHistoryModel : ViewModel() {
    // 从全局上下文获取数据库实例（官方推荐：ViewModel不直接持有Context）
    private val searchHistoryDao = AppDatabase.getInstance(MyApp.appContext).searchHistoryDao()

    // 暴露历史列表Flow（供Compose观察）
    val allHistory = searchHistoryDao.getAll()

    // 插入搜索历史
    fun insertHistory(historyText: String) {
        viewModelScope.launch {
            searchHistoryDao.insert(SearchHistory(history = historyText))
        }
    }

    // 删除所有历史（无参，符合DAO定义）
    fun deleteAllHistory() {
        viewModelScope.launch {
            searchHistoryDao.deleteAll()
        }
    }

}