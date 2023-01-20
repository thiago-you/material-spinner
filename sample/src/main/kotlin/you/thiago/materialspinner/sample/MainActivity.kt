package you.thiago.materialspinner.sample

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import you.thiago.materialspinner.MaterialSpinner
import you.thiago.materialspinner.sample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val listener by lazy {
        object : MaterialSpinner.OnItemSelectedListener {
            override fun onItemSelected(parent: MaterialSpinner, view: View?, position: Int, id: Long) {
                Log.v("MaterialSpinner", "onItemSelected parent=${parent.id}, position=$position")
                parent.focusSearch(View.FOCUS_UP)?.requestFocus()
            }

            override fun onNothingSelected(parent: MaterialSpinner) {
                Log.v("MaterialSpinner", "onNothingSelected parent=${parent.id}")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setupAdapter()
        setupInterface()
    }

    private fun setupAdapter() {
        val arrayAdapter = ArrayAdapter.createFromResource(
            this, R.array.planets_array,
            android.R.layout.simple_spinner_item
        )

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        with(binding) {
            materialSpinner1.apply {
                adapter = arrayAdapter
                onItemSelectedListener = listener
                onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
                    Log.v("MaterialSpinner", "onFocusChange hasFocus=$hasFocus")
                }
            }

            materialSpinner2.apply {
                adapter = arrayAdapter
                onItemSelectedListener = listener
            }

            materialSpinner3.apply {
                adapter = arrayAdapter
                onItemSelectedListener = listener
                selection = 3
                setDrawable(ResourcesCompat.getDrawable(resources, R.drawable.ic_arrow_downward, theme))
            }

            spinner.adapter = arrayAdapter
            appCompatSpinner.adapter = arrayAdapter
        }
    }

    private fun setupInterface() {
        with(binding) {
            b1Clear.setOnClickListener {
                materialSpinner1.selection = ListView.INVALID_POSITION
                materialSpinner1.requestFocus()
            }

            b1Error.setOnClickListener {
                materialSpinner1.onClick()
            }

            b1Click.setOnClickListener {
                materialSpinner1.performClick()
            }

            b2Clear.setOnClickListener {
                materialSpinner2.selection = ListView.INVALID_POSITION
                materialSpinner2.requestFocus()

            }

            b2Error.setOnClickListener {
                materialSpinner2.onClick()
            }

            b2Click.setOnClickListener {
                materialSpinner2.performClick()
            }

            b3Clear.setOnClickListener {
                materialSpinner3.selection = ListView.INVALID_POSITION
                materialSpinner3.requestFocus()

            }

            b3Error.setOnClickListener {
                materialSpinner3.onClick()
            }

            b3Click.setOnClickListener {
                materialSpinner3.performClick()
            }
        }
    }

    private fun MaterialSpinner.onClick() {
        error = if (error.isNullOrEmpty()) resources.getText(R.string.error) else null
    }
}
