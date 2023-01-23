package com.udacity.shoestore.screens

import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
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
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shoe_list, container, false
        )

        viewModel = ViewModelProvider(this).get(ShoeListViewModel::class.java)
        setListeners()

        viewModel.list.observe(viewLifecycleOwner, Observer { shoeList ->
            //make sure it's empty
            binding.linearLayout.removeAllViews()
            for (shoe in shoeList) {
                binding.linearLayout.addView(changeToTextView(shoe))
            }

        })
        addShoe(args.shoe)

        return binding.root
    }

    private fun setListeners() {
        binding.logOut.setOnClickListener {
            findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToLoginFragment())
        }

        binding.fab.setOnClickListener {
            findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToDetailsFragment())
        }
    }

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