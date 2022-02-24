package selitskiyapp.hometasks.financialassistant.presentation.recyclers.operations

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import selitskiyapp.hometasks.financialassistant.domain.models.OperationWithMoneyHolder

class OperationsAdapter(
    private val itemClickListenerOperations: OperationsOnItemListener
) : RecyclerView.Adapter<OperationsViewHolder>() {
    private var items: List<OperationWithMoneyHolder> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OperationsViewHolder =
        OperationsViewHolder.fromParent(parent, itemClickListenerOperations)

    override fun onBindViewHolder(holder: OperationsViewHolder, position: Int) {
        holder.bindView(items[position])
    }

    override fun getItemCount() = items.size

    fun submitList(data: List<OperationWithMoneyHolder>) {
        items = data
        notifyDataSetChanged()
    }
}