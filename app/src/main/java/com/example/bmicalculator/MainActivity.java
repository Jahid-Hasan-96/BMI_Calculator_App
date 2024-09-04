package com.example.bmicalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;

import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.bmicalculator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    boolean isMenSelected = true; // Default to men

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setupButtonClick();
        setSupportActionBar(binding.toolbar);
    }

    private void setupButtonClick() {
        binding.CalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int weight, heightFt, heightInc;
                    weight = Integer.parseInt(binding.Weight.getText().toString());
                    heightFt = Integer.parseInt(binding.HeightFT.getText().toString());
                    heightInc = Integer.parseInt(binding.HeightInc.getText().toString());

                    if (isMenSelected) {
                        calculateBMIForMen(weight, heightFt, heightInc);
                    } else {
                        calculateBMIForWomen(weight, heightFt, heightInc);
                    }
                } catch (NumberFormatException e) {
                    // Handle the case where input fields contain non-numeric data
                    // Set an error message or perform appropriate action
                    // For example:
                    binding.BMIResult.setText("Error");
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.topmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        CharSequence title = item.getTitle();
        if ("Men".contentEquals(title)) {
            isMenSelected = true;
        } else if ("Women".equals(title)) {
            isMenSelected = false;
        }

        return true;
    }

    private void calculateBMIForMen(int weight, int heightFt, int heightInc) {
        int TotalInches = heightFt * 12 + heightInc;
        double totalCM = TotalInches * 2.54;
        double TotalMeter = totalCM / 100;
        double BMI = weight / (TotalMeter * TotalMeter);
        displayBMIResult(BMI);
    }

    private void calculateBMIForWomen(int weight, int heightFt, int heightInc) {
        // Implement BMI calculation for women
        // You can use similar logic as in calculateBMIForMen method
        int TotalInches = heightFt * 12 + heightInc;
        double totalCM = TotalInches * 2.54;
        double TotalMeter = totalCM / 100;
        double BMI = weight / (TotalMeter * TotalMeter);
        displayBMIResultWo(BMI);
    }

    private void displayBMIResult(double BMI) {
        if (BMI > 25) {
            Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.scale);
            binding.Result.startAnimation(animation);
            binding.Result.setCardBackgroundColor(getResources().getColor(R.color.Red));
            binding.BMIResult.setText("OverWeight");
        } else if (BMI < 18) {
            Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.scale);
            binding.Result.startAnimation(animation);
            binding.Result.setCardBackgroundColor(getResources().getColor(R.color.Yellow));
            binding.BMIResult.setText("UnderWeight");
        } else {
            Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.scale);
            binding.Result.startAnimation(animation);
            binding.Result.setCardBackgroundColor(getResources().getColor(R.color.Green));
            binding.BMIResult.setText("Healthy");
        }
    }
    private void displayBMIResultWo(double BMI) {
        if (BMI > 25) {
            Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.scale);
            binding.Result.startAnimation(animation);
            binding.Result.setCardBackgroundColor(getResources().getColor(R.color.Red));
            binding.BMIResult.setText("OverWeight");
        } else if (BMI < 18.5) {
            Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.scale);
            binding.Result.startAnimation(animation);
            binding.Result.setCardBackgroundColor(getResources().getColor(R.color.Yellow));
            binding.BMIResult.setText("UnderWeight");
        } else {
            Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.scale);
            binding.Result.startAnimation(animation);
            binding.Result.setCardBackgroundColor(getResources().getColor(R.color.Green));
            binding.BMIResult.setText("Healthy");
        }
    }

}