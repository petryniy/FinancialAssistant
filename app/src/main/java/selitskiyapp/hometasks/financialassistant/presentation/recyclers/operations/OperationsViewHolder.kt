package selitskiyapp.hometasks.financialassistant.presentation.recyclers.operations

import android.view.LayoutInflater
import android.view.ViewGroup
import selitskiyapp.hometasks.financialassistant.R
import selitskiyapp.hometasks.financialassistant.databinding.ItemOperationBinding
import selitskiyapp.hometasks.financialassistant.domain.models.BaseItem
import selitskiyapp.hometasks.financialassistant.domain.models.OperationWithMoneyHolder
import selitskiyapp.hometasks.financialassistant.presentation.recyclers.BaseViewHolder

class OperationsViewHolder(

    private val binding: ItemOperationBinding,
    private val itemClickListenerOperations: OperationsOnItemListener

) : BaseViewHolder(binding.root) {

    companion object {

        const val VIEW_TYPE_OPERATION = 2

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

    override fun bindViewHolder(item: BaseItem?) {
        val operationItem = item as OperationWithMoneyHolder
        with(binding) {

            itemCategory.text = operationItem.operationEntity.category
            itemTypeOfValue.text = operationItem.moneyHolderEntity.name
            itemValue.text = root.context.getString(
                R.string.msg_currency_byn_amount_format,
                item.operationEntity.value / 100f
            )
            itemImageId.setImageResource(operationItem.operationEntity.categoryDrawable)

            itemOperation.setOnClickListener {
                item.operationEntity.id.let { it1 ->
                    itemClickListenerOperations.onItemClickListener(it1)
                }
            }
        }
    }
}