package com.jung.ktdemo.ui.mergeadaptere.wrapper

import com.jung.ktdemo.ui.mergeadaptere.model.WhatsNew


class WhatsNewWrapper(whatsNew: WhatsNew, override val itemViewType: Int): IWhatsNew {

    val description = whatsNew.description
}