package com.example.sldesignpro.models;

import android.view.View;
import android.widget.TextView;

import com.example.sldesignpro.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.time.LocalDate;
import java.util.regex.Pattern;

public class Validation {

    public static boolean validate(ActivityMainBinding binding, String plan, String planType, String designType, boolean isDesignAdditional, boolean isUpdateAdditional) {
        // Customer Name
        String customer_name = binding.edtName.getText().toString();
        if (customer_name.isEmpty()) {
            DisplayMessage(binding, "Customer Name is Empty");
            binding.edtName.setError("Customer Name is Empty");
            return false;
        }

        // Email Address
        String email_address = binding.edtEmail.getText().toString();
        Pattern email_regex = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
        if (!email_regex.matcher(email_address).matches()) {
            DisplayMessage(binding, "Email Address Format is Incorrect");
            binding.edtEmail.setError("Email Address Format is Incorrect");
            return false;
        }

        // Subsctiption Plan
        if (!plan.equals("Yearly") && !plan.equals("Monthly")) {
            DisplayMessage(binding, "Subscription Plan Should be Selected");
            return false;
        }

        // Plan Type
        if (planType.isEmpty()) {
            DisplayMessage(binding, "Plan Type Should be Selected");
            return false;
        }

        // Design Type
        if (designType.isEmpty()) {
            DisplayMessage(binding, "Type of Design Should be Selected");
            return false;
        }

        // Subscription Date
        String subscription_Date = binding.edtDate.getText().toString();
        if (subscription_Date.isEmpty()) {
            DisplayMessage(binding, "Subscription Date Should not be Empty");
            binding.edtDate.setError("Subscription Date Should not be Empty");
            return false;
        } else {
            String[] date_array = subscription_Date.split("/");
            int year = Integer.parseInt(date_array[0]);
            int month = Integer.parseInt(date_array[1]);
            int day = Integer.parseInt(date_array[2]);
            LocalDate current_date = LocalDate.now();
            LocalDate picked_date = LocalDate.of(year, month, day);
            if (current_date.isBefore(picked_date)) {
                binding.edtDate.setError("Subscription Date Should Before the Current Date");
                DisplayMessage(binding, "Subscription Date Should Before the Current Date");
                return false;
            }
            binding.edtDate.setError(null);
        }
        // If the fields pass the validation, call the OrderGenerator class
        OrderGenerator orderGenerator = new OrderGenerator(customer_name, email_address, plan, planType, isDesignAdditional, isUpdateAdditional, designType, subscription_Date);
        DisplayMessage(binding, orderGenerator.Generate());
        return true;
    }

    public static void DisplayMessage(ActivityMainBinding binding, String message) {
        Snackbar snackbar = Snackbar.make(binding.root, message, BaseTransientBottomBar.LENGTH_INDEFINITE);
        View snackBarView = snackbar.getView();
        TextView tv = snackBarView.findViewById(com.google.android.material.R.id.snackbar_text);
        tv.setMaxLines(99);
        snackbar.setAction("OK", v -> {
        }).show();
    }
}
