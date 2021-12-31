package com.kale.compose.mvi.demo.ext

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable

//只会在周期中启动一次
@Composable
inline fun OneShotEffect(
    //第一次执行的判断值
    predicate: Boolean = true,
    //是否强制执行
    isForceRun: Boolean = false,
    //执行函数
    block: () -> Unit
) {
    val isFirst = rememberSaveable {
        mutableStateOf(true)
    }
    if (isForceRun || (isFirst.value && predicate)) {
        block()
        isFirst.value = false
    }
}
