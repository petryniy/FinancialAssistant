package selitskiyapp.hometasks.financialassistant.utilsselickiy.fragments

class OperationsFragmentU

{
//
//    : Fragment(R.layout.fragment_operations) {
//
//    private val binding: FragmentOperationsBinding by viewBinding(FragmentOperationsBinding::bind)
//    private val adapter by lazy { OperationsAdapter(itemClickListener) }
//    private val viewModel by viewModel<OperationsFragmentViewModel>()
//
//    private val itemClickListener: OnItemListener = object : OnItemListener {
//        override fun onItemClickListener(id: Int) {
//            viewModel.onItemClicked(id)
//        }
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        initObservers()
//
//        initRecycler()
//
//        initFloatingButtons()
//
//        viewModel.getAllOperations()
//    }
//
//    private fun initObservers() {
//        viewModel.operationsLiveData.observe(viewLifecycleOwner) { operations ->
//            adapter.submitList(operations)
//        }
//    }
//
//    private fun initRecycler() {
//        binding.apply {
//            recycler.adapter = adapter
//            recycler.layoutManager = LinearLayoutManager(context)
//        }
//    }
//
//    private fun initFloatingButtons() = with(binding) {
//        floatingPlus.setOnClickListener {
//            onClickAddButton()
//        }
//
//        floatingAddCredit.setOnClickListener {
//            findNavController().navigate(R.id.to_addDebitFragment)
//        }
//
//        floatingAddDebit.setOnClickListener {
//            findNavController().navigate(R.id.to_addDebitFragment)
//        }
//    }
//
//    private fun onClickAddButton() {
//        setVisibility(clicked)
//
//        setAnimation(clicked)
//
//        clicked = !clicked
//    }
//
//    private fun setAnimation(clicked: Boolean) = with(binding) {
//        if (!clicked) {
//            floatingAddDebit.startAnimation(fromBottom)
//            floatingAddCredit.startAnimation(fromBottom)
//            floatingPlus.startAnimation(rotateOpen)
//        } else {
//            floatingAddDebit.startAnimation(toBottom)
//            floatingAddCredit.startAnimation(toBottom)
//            floatingPlus.startAnimation(rotateClose)
//        }
//    }
//
//    private fun setVisibility(clicked: Boolean) {
//        if (!clicked) {
//            with(binding) {
//                floatingAddDebit.visibility = View.VISIBLE
//                floatingAddCredit.visibility = View.VISIBLE
//            }
//        } else {
//            with(binding) {
//                floatingAddDebit.visibility = View.GONE
//                floatingAddCredit.visibility = View.GONE
//            }
//        }
//    }
//
//    private var clicked = false
//    private val rotateOpen: Animation by lazy {
//        AnimationUtils.loadAnimation(
//            activity?.applicationContext,
//            R.anim.rotate_open_anim
//        )
//    }
//    private val rotateClose: Animation by lazy {
//        AnimationUtils.loadAnimation(
//            activity?.applicationContext,
//            R.anim.rotate_close_anim
//        )
//    }
//    private val fromBottom: Animation by lazy {
//        AnimationUtils.loadAnimation(
//            activity?.applicationContext,
//            R.anim.from_bottom_anim
//        )
//    }
//    private val toBottom: Animation by lazy {
//        AnimationUtils.loadAnimation(
//            activity?.applicationContext,
//            R.anim.to_bottom_anim
//        )
//    }
}