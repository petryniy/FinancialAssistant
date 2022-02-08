package selitskiyapp.hometasks.financialassistant.presentation.recycler

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import selitskiyapp.hometasks.financialassistant.domain.models.PositiveAssets

class DebitCreditLendAdapter(
    private val itemClickListener: OnItemListener
) : RecyclerView.Adapter<DebitCreditLendViewHolder>() {
    private var items: List<PositiveAssets> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DebitCreditLendViewHolder =
        DebitCreditLendViewHolder.fromParent(parent, itemClickListener)


    override fun onBindViewHolder(holder: DebitCreditLendViewHolder, position: Int) {
        holder.bindView(items[position])
    }

    override fun getItemCount() = items.size

    fun submitList(data: List<PositiveAssets>) {
        items = data
        notifyDataSetChanged()
    }
}