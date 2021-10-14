package com.kale.compose.mvi.demo.model

import androidx.paging.PagingSource
import androidx.paging.PagingState

data class HomeItemModel(
    val apkLink: String,
    val audit: Int,
    val author: String,
    val canEdit: Boolean,
    val chapterId: Int,
    val chapterName: String,
    val collect: Boolean,
    val courseId: Int,
    val desc: String,
    val descMd: String,
    val envelopePic: String,
    val fresh: Boolean,
    val host: String,
    val id: Int,
    val link: String,
    val niceDate: String,
    val niceShareDate: String,
    val origin: String,
    val prefix: String,
    val projectLink: String,
    val publishTime: Long,
    val realSuperChapterId: Int,
    val selfVisible: Int,
    val shareDate: Long,
    val shareUser: String,
    val superChapterId: Int,
    val superChapterName: String,
    val tags: List<Any>,
    val title: String,
    val type: Int,
    val userId: Int,
    val visible: Int,
    val zan: Int
) {
    override fun toString(): String {
        return "HomeItemModel(apkLink='$apkLink', audit=$audit, author='$author', canEdit=$canEdit, chapterId=$chapterId, chapterName='$chapterName', collect=$collect, courseId=$courseId, desc='$desc', descMd='$descMd', envelopePic='$envelopePic', fresh=$fresh, host='$host', id=$id, link='$link', niceDate='$niceDate', niceShareDate='$niceShareDate', origin='$origin', prefix='$prefix', projectLink='$projectLink', publishTime=$publishTime, realSuperChapterId=$realSuperChapterId, selfVisible=$selfVisible, shareDate=$shareDate, shareUser='$shareUser', superChapterId=$superChapterId, superChapterName='$superChapterName', tags=$tags, title='$title', type=$type, userId=$userId, visible=$visible, zan=$zan)"
    }
}

class HomeItemSource : PagingSource<Int, HomeItemModel>() {


    override fun getRefreshKey(state: PagingState<Int, HomeItemModel>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, HomeItemModel> {

        return try {

            val nextPage = params.key ?: 1
            val homePageModel = Repository.RemoteRepository.wanAndroid.getHomePageModel(nextPage)
            LoadResult.Page(
                data = homePageModel.data.datas,
                prevKey = homePageModel.data.curPage - 1,
                nextKey = homePageModel.data.curPage + 1
            )

        } catch (e: Throwable) {
            return LoadResult.Error(e)
        }
    }

}