/**
 * Course: MAD302-01
 * Assignment: 3
 * Name: Jennyfer Parmar
 * Student ID: A00201240
 * Date: 24.04.26
 * Description: Displays captured image and scan result.
 */

package com.example.assignment_3nevinjames

import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment_3nevinjames.databinding.ActivityDetailsBinding

/**
 * Displays the captured image and result.
 */
class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    /**
     * Called when activity is created.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Retrieve image safely
        val image = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra("captured_image", Bitmap::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Bitmap>("captured_image")
        }

        // Display image if available
        image?.let {
            binding.capturedImage.setImageBitmap(it)
        }

        // Display result
        binding.resultTextView.text = "Scan Completed Successfully"
    }
}