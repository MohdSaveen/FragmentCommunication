package com.example.fragmentcommunication

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

class Fragment2 : Fragment() {

    private var sharedViewModelInstance: SharedViewModel? = null

    private var editTextFromFragment2: EditText? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_2, container, false)

        val sendDataButton: Button = view.findViewById(R.id.send_button_fragment_2)
        editTextFromFragment2 = view.findViewById(R.id.edit_text_from_fragment_2)

        // as soon as the button is clicked
        // send the data to ViewModel
        // and the Live data will take care of
        // updating the data inside another Fragment
        sendDataButton.setOnClickListener {
            sharedViewModelInstance?.setData(editTextFromFragment2!!.text)
        }

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // create instances of the shared view model
        // when the activity is created
        sharedViewModelInstance = ViewModelProviders.of(requireActivity()).get(SharedViewModel::class.java)

        // observe the data inside the view model that is mutable
        // live of type CharSequence and set the data for edit text
        sharedViewModelInstance!!.getData().observe(viewLifecycleOwner, Observer {
            editTextFromFragment2!!.text = it as Editable?
        })
    }
}