package selitskiyapp.hometasks.financialassistant.presentation.recyclers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import selitskiyapp.hometasks.financialassistant.R


class HeadViewHolder(view: View) : BaseViewHolder(view) {

    companion object {
        const val VIEW_TYPE_HEAD = 1

        fun createViewHolder(viewGroup: ViewGroup): HeadViewHolder {
            return HeadViewHolder(
                LayoutInflater.from(viewGroup.context)
                    .inflate(
                        R.layout.item_head_data,
                        viewGroup,
                        false
                    )
            )
        }
    }

    private val headTextView by lazy { view.findViewById<TextView>(R.id.textViewHead) }

    override fun bindViewHolder(item: BaseItem) {
        val headItem = item as HeadItem

        headTextView.text = headItem.date
    }


}