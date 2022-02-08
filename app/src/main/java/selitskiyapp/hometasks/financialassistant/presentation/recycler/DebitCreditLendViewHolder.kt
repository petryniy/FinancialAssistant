package selitskiyapp.hometasks.financialassistant.presentation.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import selitskiyapp.hometasks.financialassistant.databinding.ItemDebitBinding
import selitskiyapp.hometasks.financialassistant.domain.models.PositiveAssets

class DebitCreditLendViewHolder(
    private val binding: ItemDebitBinding,
    private val itemClickListener: OnItemListener
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun fromParent(parent: ViewGroup,
                       itemClickListener: OnItemListener
        ):
                DebitCreditLendViewHolder {
            return DebitCreditLendViewHolder(
                ItemDebitBinding.inflate(LayoutInflater.from(parent.context),
                    parent, false),
                itemClickListener
            )
        }
    }

    fun bindView(item: PositiveAssets) = with(binding) {
        debitCategory.text = item.category
        debitImageId.text = item.imageId
        debitTypeOfValue.text = item.typeOfValue
        debitValue.text = item.value.toString()

        itemDebit.setOnClickListener {
            itemClickListener.onItemClickListener(bindingAdapterPosition)
        }
    }
}