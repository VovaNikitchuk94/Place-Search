package com.vnykyt.placesearch.presentation.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.vnykyt.placesearch.presentation.R
import com.vnykyt.placesearch.presentation.databinding.LayoutEmptyStateBinding

class NothingFoundStateView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val viewBinding: LayoutEmptyStateBinding by viewBinding(CreateMethod.BIND)

    init {
        LayoutInflater.from(context).inflate(R.layout.layout_empty_state, this, true)
        with(viewBinding) {
            emptyStateImageView.setImageResource(R.drawable.ic_search_state)
            emptyStateTitleView.text = context.getString(R.string.main_search_nothing_found)
        }
    }
}