package selitskiyapp.hometasks.financialassistant.presentation.recyclers.operations

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import selitskiyapp.hometasks.financialassistant.domain.models.BaseItem
import selitskiyapp.hometasks.financialassistant.domain.models.HeadItem
import selitskiyapp.hometasks.financialassistant.domain.models.OperationWithMoneyHolder
import selitskiyapp.hometasks.financialassistant.presentation.recyclers.BaseViewHolder
import selitskiyapp.hometasks.financialassistant.presentation.recyclers.HeadViewHolder

class OperationsAdapter(

    private val itemClickListenerOperations: OperationsOnItemListener

) : RecyclerView.Adapter<BaseViewHolder>() {

    private var items: List<BaseItem?> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder =
        if (viewType == HeadViewHolder.VIEW_TYPE_HEAD) HeadViewHolder.createViewHolder(parent)
        else OperationsViewHolder.fromParent(parent, itemClickListenerOperations)


    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bindViewHolder(items[position])
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is HeadItem -> HeadViewHolder.VIEW_TYPE_HEAD
            is OperationWithMoneyHolder -> OperationsViewHolder.VIEW_TYPE_OPERATION
            else -> {0}
        }
    }

    fun submitList(data: List<BaseItem?>) {
        items = data
        notifyDataSetChanged()
    }
}