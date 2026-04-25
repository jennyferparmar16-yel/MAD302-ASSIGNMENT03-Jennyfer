/**
 * Course: MAD302-01
 * Assignment: 3
 * Name: Jennyfer Parmar
 * Student ID: A00201240
 * Date: 24.04.26
 * Description: Handles async loading, camera permission, captures image, and navigates to DetailsActivity.
 */

package com.example.assignment_3nevinjames

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.example.assignment_3nevinjames.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * MainActivity handles permission checking, async data simulation, and camera capture.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    /**
     * Launcher to receive camera result.
     */
    private val cameraLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->

            if (result.resultCode == RESULT_OK) {

                // Retrieve thumbnail bitmap from camera intent
                val bitmap = result.data?.extras?.get("data") as? Bitmap

                // Validate captured image
                if (bitmap == null) {
                    Toast.makeText(this, "Capture failed", Toast.LENGTH_SHORT).show()
                    return@registerForActivityResult
                }

                // Send image to DetailsActivity
                val intent = Intent(this, DetailsActivity::class.java)
                intent.putExtra("captured_image", bitmap)
                startActivity(intent)
            }
        }

    /**
     * Launcher to request camera permission.
     */
    private val permissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->

            if (granted) {
                openCamera() // Open camera immediately after permission granted
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
            }
        }

    /**
     * Called when activity is created.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Show loading state using ProgressBar
        binding.progressBar.visibility = View.VISIBLE

        // Hide button to prevent interaction during loading
        binding.cameraButton.visibility = View.INVISIBLE

        // Update text to indicate loading
        binding.descTextView.text = "Loading..."

        lifecycleScope.launch {
            // Simulate data fetching using coroutine
            delay(4000)

            // Hide ProgressBar after loading completes
            binding.progressBar.visibility = View.GONE

            // Show button again after loading
            binding.cameraButton.visibility = View.VISIBLE

            // Restore original description text
            binding.descTextView.text =
                "Click the button below to fetch secure data and scan your utility."
        }

        // Button click triggers permission check and camera
        binding.cameraButton.setOnClickListener {
            checkPermissionAndOpenCamera()
        }
    }

    /**
     * Checks camera permission and proceeds accordingly.
     */
    private fun checkPermissionAndOpenCamera() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED -> {

                openCamera() // Permission already granted
            }

            else -> {
                // Request camera permission
                permissionLauncher.launch(Manifest.permission.CAMERA)
            }
        }
    }

    /**
     * Opens device camera using intent.
     */
    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraLauncher.launch(intent)
    }
}