package com.example.whiskyhunter

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.whiskyhunter.adapters.DistilleriesInfoViewAdapter
import com.example.whiskyhunter.models.DistilleriesInfo

class AddDistilleryActivity : AppCompatActivity() {

    lateinit var adapter : DistilleriesInfoViewAdapter

    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_distillery)
        adapter = DistilleriesInfoViewAdapter(this, ArrayList())

        val placeOfInsertion = intent.getStringExtra("placeOfInsertion")
        if(placeOfInsertion == "at_the_beginning"){
            findViewById<TextView>(R.id.title_add_distillery).text =
                "Add a new distillery at the beginning of the list"
        }else if(placeOfInsertion == "at_the_end"){
            findViewById<TextView>(R.id.title_add_distillery).text =
                "Add a new distillery at the end of the list"
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onBackPressed() {
        val name = findViewById<EditText>(R.id.add_item_name).text.toString()
        val country = findViewById<EditText>(R.id.add_item_country).text.toString()
        val whiskybase_whiskies = findViewById<EditText>(R.id.add_item_whiskybase_whiskies).text.toString()
        val whiskybase_votes = findViewById<EditText>(R.id.add_item_whiskybase_votes).text.toString()
        val whiskybase_rating = findViewById<EditText>(R.id.add_item_whiskybase_rating).text.toString()

        val nameError = findViewById<TextView>(R.id.name_error)
        val countryError = findViewById<TextView>(R.id.country_error)
        val whiskybase_whiskiesError = findViewById<TextView>(R.id.whiskybase_whiskies_error)
        val whiskybase_votesError = findViewById<TextView>(R.id.whiskybase_votes_error)
        val whiskybase_ratingError = findViewById<TextView>(R.id.whiskybase_rating_error)

        nameError.text = ""
        countryError.text = ""
        whiskybase_whiskiesError.text = ""
        whiskybase_votesError.text = ""
        whiskybase_ratingError.text = ""

        if (name.isEmpty()){
            nameError.text = getString(R.string.empty_name)
        }
        if (country.isEmpty()){
            countryError.text = getString(R.string.empty_country)
        }
        if (whiskybase_whiskies.isEmpty()){
            whiskybase_whiskiesError.text = getString(R.string.empty_whiskybase_whiskies)
        }else if(whiskybase_whiskies.toBigIntegerOrNull() == null){
            whiskybase_whiskiesError.text = getString(R.string.invalid_whiskybase_whiskies)
        }
        if (whiskybase_votes.isEmpty()){
            whiskybase_votesError.text = getString(R.string.empty_whiskybase_votes)
        }else if(whiskybase_votes.toBigIntegerOrNull() == null){
            whiskybase_votesError.text = getString(R.string.invalid_whiskybase_votes)
        }
        if (whiskybase_rating.isEmpty()){
            whiskybase_ratingError.text = getString(R.string.empty_whiskybase_rating)
        }else if(whiskybase_rating.toFloatOrNull() == null){
            whiskybase_ratingError.text = getString(R.string.invalid_whiskybase_rating)
        }
        if(nameError.text.toString() == "" &&
            countryError.text.toString() == "" &&
            whiskybase_whiskiesError.text.toString() == "" &&
            whiskybase_votesError.text.toString() == "" &&
            whiskybase_ratingError.text.toString() == ""){
            val distilleryInfo = DistilleriesInfo (
                name = name,
                slug = "",
                country = country,
                whiskybase_whiskies = whiskybase_whiskies,
                whiskybase_votes = whiskybase_votes,
                whiskybase_rating = whiskybase_rating
            )

            val builder = AlertDialog.Builder ( this )
            builder.setTitle ( "Confirmation" )
                .setMessage ( "Are you sure you want to add this distillery?" )
                .setPositiveButton ( "Confirm") { _, _ ->
                    val returnIntent = Intent()
                    val placeOfInsertion = intent.getStringExtra("placeOfInsertion")
                    returnIntent.putExtra("addedDistillery", distilleryInfo)
                    returnIntent.putExtra("placeOfInsertion", placeOfInsertion)
                    setResult(Activity.RESULT_OK, returnIntent)
                    Toast.makeText(applicationContext, "Item successfully added", Toast.LENGTH_SHORT).show()
                    finish()
                }
                .setNegativeButton ( "Cancel") { _, _ ->
                    val returnIntent = Intent()
                    setResult(Activity.RESULT_OK, returnIntent)
                    finish()
                }
            builder.create().show()
        }else{
            val builder = AlertDialog.Builder ( this )
            builder.setTitle ( "Confirmation" )
                .setMessage ( "Some fields are missing or you have invalid values. Do you want to dismiss adding a new distillery?" )
                .setPositiveButton ( "Confirm") { _, _ ->
                    val returnIntent = Intent()
                    setResult(Activity.RESULT_OK, returnIntent)
                    finish()
                }
                .setNegativeButton ( "Cancel") { _, _ -> }
            builder.create().show()
        }
    }

}