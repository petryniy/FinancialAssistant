package selitskiyapp.hometasks.financialassistant.domain.models

data class MoneyHolder(
    var id: Int = 0,
    val name: String,
    val type: Int?,
    val balance: Long
) {
    override fun toString(): String {
        return name
    }
}
