package com.jung.ktdemo.ui.delegate

import kotlin.reflect.KProperty

class DelegateString {


    /***
     * @thisRef 參數表示委託對象的引用
     * @property 參數表示委託的屬性
     * @value 參數表示為屬性賦值
     */
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "$thisRef, delegating ${property.name} to me !"
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("$value, has been assigned to ${property.name} in $thisRef.")
    }

}