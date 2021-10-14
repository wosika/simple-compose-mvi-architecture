package com.kale.compose.mvi.demo.ext

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun RowScope.Gap(width: Dp) {
    Spacer(Modifier.width(width))
}

@Composable
fun ColumnScope.Gap(height: Dp) {
    Spacer(Modifier.height(height))
}