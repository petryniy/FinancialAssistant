package selitskiyapp.hometasks.financialassistant.presentation.recyclers.moneyholder

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import selitskiyapp.hometasks.financialassistant.R
import selitskiyapp.hometasks.financialassistant.databinding.ItemMoneyHolderAutocompleteBinding
import selitskiyapp.hometasks.financialassistant.domain.models.MoneyHolder

class MoneyHolderArrayAdapter(

    private val mContext: Context,
    val list: List<MoneyHolder?>,

    ) :
    ArrayAdapter<MoneyHolder>(mContext, 0, list) {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = LayoutInflater.from(mContext)
            .inflate(R.layout.item_money_holder_autocomplete, parent,false)
        val dto = list[position]


        return ItemMoneyHolderAutocompleteBinding.bind(view).apply {
            if (dto != null) {
                dto.type?.let { imageView.setImageResource(it) }
            }
            if (dto != null) {
                textView.text = dto.name
            }
        }.root
    }
}