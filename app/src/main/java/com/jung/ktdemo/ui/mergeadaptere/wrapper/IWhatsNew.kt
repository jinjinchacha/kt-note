package com.jung.ktdemo.ui.mergeadaptere.wrapper

import com.jung.ktdemo.R


interface IWhatsNew {
    companion object {
        const val ONE = R.layout.item_whats_new_1
        const val TWO = R.layout.item_whats_new_2
        const val THREE = R.layout.item_whats_new_3
    }
    val itemViewType: Int
}