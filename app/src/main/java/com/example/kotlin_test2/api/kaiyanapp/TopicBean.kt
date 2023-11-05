package com.example.kotlin_test2.api.kaiyanapp

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

/**
 * Created by ddup on 2020/11/13.
 * 首页推荐数据bean
 */

data class TopicBean(
    var nextPageUrl: String?, var nextPublishTime: Long,
    var newestIssueType: String?, var dialog: Any?,
    var issueList: List<IssueListBean>?
) {

    data class IssueListBean(
        var releaseTime: Long, var type: String?,
        var date: Long, var publishTime: Long, var count: Int,
        var itemList: List<ItemListBean>?
    ) {
        @Entity()
        data class ItemListBean(var type: String?, var data: DataBean?, var tag: Any?) {
            @PrimaryKey(autoGenerate = true)
            var uid = 0
            @TypeConverters
            data class DataBean(
                var dataType: String?,
                var id: Int,
                var title: String?,
                var description: String?,
                var image: String?,
                var actionUrl: String?,
                var adTrack: Any?,
                var isShade: Boolean,
                var label: Any?,
                var labelList: Any?,
                var header: Any?,
                var category: String?,
                var duration: Long?,
                var playUrl: String,
                var cover: CoverBean?,
                var author: AuthorBean?,
                var releaseTime: Long?,
                var consumption: ConsumptionBean?
            ) {
                data class CoverBean(
                    var feed: String?, var detail: String?,
                    var blurred: String?, var sharing: String?, var homepage: String?
                ) {}

                data class ConsumptionBean(
                    var collectionCount: Int,
                    var shareCount: Int,
                    var replyCount: Int
                ) {
                }

                data class AuthorBean(var icon: String) {}
            }
        }
    }
}