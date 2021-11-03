package com.globallogic.global_logic_test.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.globallogic.global_logic_test.R;
import com.globallogic.global_logic_test.databinding.ActivityDataBinding;

public class DataActivity extends AppCompatActivity {
    private ActivityDataBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
    }
}