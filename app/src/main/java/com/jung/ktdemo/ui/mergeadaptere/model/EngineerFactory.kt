package com.jung.ktdemo.ui.mergeadaptere.model

import com.jung.ktdemo.ui.mergeadaptere.wrapper.IWhatsNew.Companion.ONE
import com.jung.ktdemo.ui.mergeadaptere.wrapper.IWhatsNew.Companion.THREE
import com.jung.ktdemo.ui.mergeadaptere.wrapper.IWhatsNew.Companion.TWO
import com.jung.ktdemo.ui.mergeadaptere.wrapper.WhatsNewWrapper


fun getEngineers(): List<Engineer> {
    return listOf(
        Engineer(
            "Joe Birch",
            "Android Engineer",
            image = "https://upload.wikimedia.org/wikipedia/commons/9/9a/Gull_portrait_ca_usa.jpg"
        ),
        Engineer(
            "Jordan Morgan",
            "iOS Engineer",
            image = "https://avatars1.githubusercontent.com/u/5566805?s=400&u=02f9d1900ccaa1bd7129094a359c399314cd54c9&v=4"
        ),
        Engineer(
            "Ann Tai",
            "Designer",
            image = "https://avatars1.githubusercontent.com/u/13378873?s=460&u=094d69ba59bd58b1159a00dc5516053710efe3fb&v=4"
        ),
        Engineer(
            "Prateek Prasad",
            "Android Engineer",
            image = "https://avatars0.githubusercontent.com/u/5685314?s=460&u=fb9fe7fe2ec98b7041e2bfed11505a3daac80166&v=4"
        ),
        Engineer(
            "Marcus Wermuth",
            "Engineering Manager",
            image = "https://avatars3.githubusercontent.com/u/1415538?s=460&u=6454f3651419e4c703c67b009979375d4430f48a&v=4"
        ),
        Engineer(
            "Andy Yates",
            "iOS Engineer",
            image = "https://avatars1.githubusercontent.com/u/1058408?s=460&v=4"
        )
    )
}

fun getWhatsNew(): List<WhatsNewWrapper> {
    return listOf(
        WhatsNewWrapper(WhatsNew("Clicking the dismiss button will make this card disappear with an animation!"), ONE),
        WhatsNewWrapper(WhatsNew("Clicking the dismiss button will make this card disappear with an animation!!"), TWO),
        WhatsNewWrapper(WhatsNew("Clicking the dismiss button will make this card disappear with an animation!!!"), THREE)
    )
}