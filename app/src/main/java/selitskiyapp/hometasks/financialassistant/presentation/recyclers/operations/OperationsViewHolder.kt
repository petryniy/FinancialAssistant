package selitskiyapp.hometasks.financialassistant.presentation.recyclers.operations

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import selitskiyapp.hometasks.financialassistant.R
import selitskiyapp.hometasks.financialassistant.data.storage.models.OperationWithMoneyHolderEntity
import selitskiyapp.hometasks.financialassistant.databinding.ItemOperationBinding
import selitskiyapp.hometasks.financialassistant.domain.models.OperationWithMoneyHolder

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

    fun bindView(item: OperationWithMoneyHolder) = with(binding) {
        itemCategory.text = item.operationEntity.category
        itemTypeOfValue.text = item.moneyHolderEntity.name
        itemValue.text = root.context.getString(R.string.msg_currency_byn_amount_format, item.operationEntity.value / 100f)
        itemImageId.setImageResource(
            when (item.operationEntity.categoryImageId) {
                1 -> R.drawable.ic_car
                2 -> R.drawable.ic_products
                3 -> R.drawable.ic_pets
                4 -> R.drawable.ic_child
                5 -> R.drawable.ic_house
                6 -> R.drawable.ic_coffee
                else -> R.drawable.ic_add
            }
        )

        itemDebit.setOnClickListener {
            item.operationEntity.id.let { it1 -> itemClickListenerOperations.onItemClickListener(it1) }
        }
    }
}