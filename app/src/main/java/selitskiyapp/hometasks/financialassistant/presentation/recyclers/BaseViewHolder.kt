package selitskiyapp.hometasks.financialassistant.presentation.recyclers

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import selitskiyapp.hometasks.financialassistant.domain.models.BaseItem

abstract class BaseViewHolder(view: View): RecyclerView.ViewHolder(view) {

    abstract fun bindViewHolder(item: BaseItem)

}