package selitskiyapp.hometasks.financialassistant.presentation.recyclers.operations

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import selitskiyapp.hometasks.financialassistant.R
import selitskiyapp.hometasks.financialassistant.data.storage.models.OperationWithMoneyHolderEntity
import selitskiyapp.hometasks.financialassistant.databinding.ItemOperationBinding

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

    fun bindView(item: OperationWithMoneyHolderEntity) = with(binding) {
        itemCategory.text = item.operationEntity.category
        itemTypeOfValue.text = item.moneyHolderEntity.name
        itemValue.text = item.operationEntity.value.toString()
        itemImageId.setImageResource(
            when (item.operationEntity.categoryImageId) {
                1 -> R.drawable.ic_car
                2 -> R.drawable.ic_products
                else -> R.drawable.ic_add
            }
        )

        itemDebit.setOnClickListener {
            item.operationEntity.id.let { it1 -> itemClickListenerOperations.onItemClickListener(it1) }
        }
    }
}