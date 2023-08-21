package com.example.sldesignpro;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sldesignpro.databinding.ActivityMainBinding;
import com.example.sldesignpro.models.DataSource;
import com.example.sldesignpro.models.Validation;

import java.util.Calendar;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    ActivityMainBinding binding;
    DataSource dataSource;
    DatePickerDialog datePicker;
    String plan = "";
    String planType = "";
    String[] monthlyPlanArray = {"Custom Printing", "Pro Tools"};
    String[] yearlyPlanArray = {"Material Designs", "Premium Tools"};
    ArrayAdapter monthlyPlanType;
    ArrayAdapter yearlyPlanType;
    String[] designTypeArray = {"Labels", "Cards", "Badges"};
    String designType = designTypeArray[0];
    ArrayAdapter designTypeAdapter;
    boolean isDesignAdditional;
    boolean isUpdateAdditional;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View rootView = binding.getRoot();
        setContentView(rootView);

        // set data source
        dataSource = new DataSource(this);
        // set adaptors
        SetAdaptors();
        // set listeners
        SetListeners();
    }

    private void SetAdaptors() {
        monthlyPlanType = dataSource.GenerateAdapter(monthlyPlanArray);
        yearlyPlanType = dataSource.GenerateAdapter(yearlyPlanArray);
        designTypeAdapter = dataSource.GenerateAdapter(designTypeArray);
        binding.spnDesignTypes.setAdapter(designTypeAdapter);
    }

    public void testToast(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    private void SetListeners() {
        // date picker
        binding.edtDate.setFocusable(false);
        binding.edtDate.setInputType(InputType.TYPE_NULL);
        binding.edtDate.setOnClickListener(this);
        // plan
        binding.rgSubsPlan.setOnCheckedChangeListener(this);
        // plan type
        binding.spnPlanType.setOnItemSelectedListener(this);
        // design type
        binding.spnDesignTypes.setOnItemSelectedListener(this);
        // design checkbox
        binding.chkDesigns.setOnClickListener(this);
        // update checkbox
        binding.chkUpdates.setOnClickListener(this);
        // submit button
        binding.btnSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // datePicker's click event
        if (v.getId() == binding.edtDate.getId()) {
            Calendar cal = Calendar.getInstance();
            int saleDay = cal.get(Calendar.DAY_OF_MONTH);
            int saleMonth = cal.get(Calendar.MONTH);
            int saleYear = cal.get(Calendar.YEAR);
            datePicker = new DatePickerDialog(this, (view, year, month, dayOfMonth) -> binding.edtDate.setText(String.format("%d/%d/%d", year, month + 1, dayOfMonth)), saleYear, saleMonth, saleDay);
            datePicker.show();
        }
        // the checkbox of checking design has been clicked
        else if (v.getId() == binding.chkDesigns.getId()) {
            isDesignAdditional = binding.chkDesigns.isChecked();
        }
        // the checkbox of checking update has been clicked
        else if (v.getId() == binding.chkUpdates.getId()) {
            isUpdateAdditional = binding.chkUpdates.isChecked();
        }
        // submit button click event
        else if (v.getId() == binding.btnSubmit.getId()) {
            Validation.validate(binding, plan, planType, designType, isDesignAdditional, isUpdateAdditional);
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        // click the subscription plan
        if (group.getId() == binding.rgSubsPlan.getId()) {
            if (checkedId == binding.rdMonthly.getId()) {
                plan = "Monthly";
                binding.spnPlanType.setAdapter(monthlyPlanType);
            } else if (checkedId == binding.rdYearly.getId()) {
                plan = "Yearly";
                binding.spnPlanType.setAdapter(yearlyPlanType);
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // the plan type spinner item selected event
        if (parent.getId() == binding.spnPlanType.getId()) {
            if (Objects.equals(plan, "Monthly")) {
                planType = monthlyPlanArray[position];
            } else if (Objects.equals(plan, "Yearly")) {
                planType = yearlyPlanArray[position];
            }
        }
        // the design type spinner item selected event
        else if (parent.getId() == binding.spnDesignTypes.getId()) {
            designType = designTypeArray[position];
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}