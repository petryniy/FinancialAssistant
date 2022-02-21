package selitskiyapp.hometasks.financialassistant.presentation.recyclers.moneyholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import selitskiyapp.hometasks.financialassistant.databinding.ItemMoneyHolderBinding
import selitskiyapp.hometasks.financialassistant.domain.models.MoneyHolder

class MoneyHolderViewHolder(
    private val binding: ItemMoneyHolderBinding,
    private val itemClickListenerMoneyHolder: MoneyHolderOnItemListener
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun fromParent(
            parent: ViewGroup,
            itemClickListenerMoneyHolder: MoneyHolderOnItemListener
        ):
                MoneyHolderViewHolder {
            return MoneyHolderViewHolder(
                ItemMoneyHolderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                ),
                itemClickListenerMoneyHolder
            )
        }
    }

    fun bindView(item: MoneyHolder) = with(binding) {
        textViewName.text = item.name
        textViewBalance.text = item.balance.toString()

        itemMoneyHolder.setOnClickListener {
            item.id.let { it1 -> itemClickListenerMoneyHolder.onItemClickListener(it1) }
        }
    }
}