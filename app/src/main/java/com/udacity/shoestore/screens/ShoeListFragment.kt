package com.udacity.shoestore.screens

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.TypedValue
import android.view.*
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.models.ShoeListViewModel
import kotlinx.android.synthetic.main.fragment_shoe_list.view.*


class ShoeListFragment : Fragment() {

    private val args: ShoeListFragmentArgs by navArgs()
    lateinit var viewModel: ShoeListViewModel
    lateinit var binding: FragmentShoeListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)

        viewModel = ViewModelProvider(this).get(ShoeListViewModel::class.java)
        setListeners()

        viewModel.list.observe(viewLifecycleOwner, Observer { shoeList ->
            //make sure it's empty
            binding.linearLayout.removeAllViews()
            //take each shoe object from shoe list
            //and convert it to text view
            //then add it to linear layout
            for (shoe in shoeList) {
                binding.linearLayout.addView(changeToTextView(shoe))
            }
        })
        addShoe(args.shoe)


        setHasOptionsMenu(true)
        return binding.root
    }

    //inflate the menu on the UI
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
         inflater.inflate(R.menu.overflow_menu,menu)
    }

    //navigate to the distination selected in the view binding in
    //the menu item id
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item,requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }

    private fun setListeners() {


        binding.fab.setOnClickListener {
            findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToDetailsFragment())
        }
    }

    @SuppressLint("SuspiciousIndentation")
    private fun changeToTextView(shoe: Shoe): View {
        val textView = TextView(context)

        textView.text =
            "Shoe name is ${shoe.name}, Shoe size is ${shoe.size},\n Shoe company is${shoe.company},Shoe description is ${shoe.description}\n\n"
          textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20F)

        return textView
    }

    private fun addShoe(shoe: Shoe?) {
        //if this object is not null do the follou
        shoe?.let {
             viewModel.addShoe(shoe)
        }
    }



}