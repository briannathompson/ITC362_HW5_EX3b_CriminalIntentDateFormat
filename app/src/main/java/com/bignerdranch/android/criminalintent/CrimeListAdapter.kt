package com.bignerdranch.android.criminalintent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.criminalintent.databinding.ListItemCrimeBinding
import java.text.DateFormat


class CrimeHolder(
    private val binding: ListItemCrimeBinding

) : RecyclerView.ViewHolder(binding.root) {

    fun bind(crime: Crime) {
        binding.crimeTitle.text = crime.title
        //binding.crimeDate.text = crime.date.toString()

        // TO DO: Create an instance of DateFormat called longDateFormat    https://developer.android.com/reference/kotlin/android/icu/text/DateFormat
        val longDateFormat = DateFormat.getDateInstance(DateFormat.LONG)
        // TO DO: Access the longDateFormat instance of Date Format and use .format() to format crime.date
        // and apply the date (with its formatting) to the crime_date TextView
        binding.crimeDate.text = longDateFormat.format(crime.date)


        binding.root.setOnClickListener {
            Toast.makeText(
                binding.root.context,
                "${crime.title} clicked!",
                Toast.LENGTH_SHORT
            ).show()
        }

        // If the crime is solved, make the crimeSolved ImageView visible; else make it gone
        binding.crimeSolved.visibility = if (crime.isSolved) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }
}

class CrimeListAdapter(
    private val crimes: List<Crime>
) : RecyclerView.Adapter<CrimeHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) : CrimeHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemCrimeBinding.inflate(inflater, parent, false)
        return CrimeHolder(binding)
    }

    override fun onBindViewHolder(holder: CrimeHolder, position: Int) {
        val crime = crimes[position]

        holder.bind(crime)
    }

    override fun getItemCount() = crimes.size
}
