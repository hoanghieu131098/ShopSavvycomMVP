package com.example.shopsavvycommvp.custom

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.example.shopsavvycommvp.R

import kotlinx.android.synthetic.main.item_linner_profile_user.view.*

class ItemProfileUser : LinearLayout {
    constructor(context: Context) : super(context) {
        init(attrs = null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(
        context,
        attrs,
        defStyleAttr,
        defStyleRes
    ) {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.ItemProfileUser, 0, 0)
        val icon =
            typeArray.getResourceId(R.styleable.ItemProfileUser_iconView, R.drawable.ic_delete)
        val title = typeArray.getString(R.styleable.ItemProfileUser_titleView)

        typeArray.recycle()
        View.inflate(context, R.layout.item_linner_profile_user, this)
        tv_item_profile.text = title
        im_item_profile.setImageResource(icon)

    }

}
