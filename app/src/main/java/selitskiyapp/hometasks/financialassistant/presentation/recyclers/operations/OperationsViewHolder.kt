package selitskiyapp.hometasks.financialassistant.presentation.recyclers.operations

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import selitskiyapp.hometasks.financialassistant.databinding.ItemOperationBinding
import selitskiyapp.hometasks.financialassistant.domain.models.Operations

class OperationsViewHolder(
    private val binding: ItemOperationBinding,
    private val itemClickListenerOperations: OperationsOnItemListener
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun fromParent(
            parent: ViewGroup,
            itemClickListenerOperations: OperationsOnItemListener
        ):
                OperationsViewHolder {
            return OperationsViewHolder(
                ItemOperationBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                ),
                itemClickListenerOperations
            )
        }
    }

    fun bindView(item: Operations) = with(binding) {
//        debitCategory.text = item.category
//        debitImageId.text = item.imageId
//        debitTypeOfValue.text = item.typeOfValue
//        debitValue.text = item.value.toString()

        itemDebit.setOnClickListener {
            itemClickListenerOperations.onItemClickListener(item.id)
        }
    }
}