package com.udacity.shoestore.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentDetailsBinding
import com.udacity.shoestore.models.Shoe
import kotlinx.android.synthetic.main.fragment_details.*


class DetailsFragment : Fragment() {

    lateinit var binding: FragmentDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)

        binding.apply {
            saveButton.setOnClickListener {

                val name = shoeNameEdit.text.toString()
                val company = shoeCompanyEdit.text.toString()
                val shoeSize = shoeSizeEdit.text.toString()
                val description = shoeDescriptionEdit.text.toString()
                if (name.isNullOrEmpty() || company.isNullOrEmpty()
                    || shoeSize.isNullOrEmpty() || description.isNullOrEmpty()
                ) {
                    Toast.makeText(context, "Enter All Fields To Make New Shoe", Toast.LENGTH_SHORT)
                        .show()
                    return@setOnClickListener
                }

                val shoe = Shoe(
                    name = name,
                    size = shoeSize.toDouble(),
                    company = company,
                    description = description
                )

                val action =
                    DetailsFragmentDirections.actionDetailsFragmentToShoeListFragment(shoe)

                requireView().findNavController().navigate(action)
            }

            cancelButton.setOnClickListener {

                val action =
                    DetailsFragmentDirections.actionDetailsFragmentToShoeListFragment()
                requireView().findNavController().navigate(action)
            }

        }



        return binding.root
    }


}