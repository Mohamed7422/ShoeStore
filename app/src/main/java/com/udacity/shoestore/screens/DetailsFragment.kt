package com.udacity.shoestore.screens

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentDetailsBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.models.ShoeListViewModel
import kotlinx.android.synthetic.main.fragment_details.*


class DetailsFragment : Fragment() {

    lateinit var binding: FragmentDetailsBinding
    lateinit var viewModel: ShoeListViewModel
    private  var shoe: Shoe = Shoe("",0.0,"","", mutableListOf())
    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel =ViewModelProvider(this).get(ShoeListViewModel::class.java)
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)

            binding.saveButton.setOnClickListener {
                binding.shoe?.name = binding.shoeNameEdit.text.toString()
                binding.shoe?.company =binding.shoeCompanyEdit.text.toString()
                binding.shoe?.size =binding.shoeSizeEdit.text.toString().toDouble()
                binding.shoe?.description = binding.shoeDescriptionEdit.text.toString()
                    Toast.makeText(context, "Enter All Fields To Make New Shoe", Toast.LENGTH_SHORT)
                        .show()
                val action =
                    DetailsFragmentDirections.actionDetailsFragmentToShoeListFragment(shoe)

                requireView().findNavController().navigate(action)
                    return@setOnClickListener
                }

        viewModel.eventCancel.observe(viewLifecycleOwner, Observer {
            cancel -> if (cancel){
                onCancel()
                viewModel.onCancelComplete()
            }

        })

        binding.shoeListVieuModel = viewModel
        binding.shoe = shoe
        binding.lifecycleOwner=this

            return binding.root

        }

    private fun onCancel( ) {
           findNavController().navigate(DetailsFragmentDirections.actionDetailsFragmentToShoeListFragment())
    }


}


