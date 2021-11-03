package com.globallogic.global_logic_test.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.globallogic.global_logic_test.R;
import com.globallogic.global_logic_test.adapter.DataAdapter;
import com.globallogic.global_logic_test.databinding.ActivityDataBinding;
import com.globallogic.global_logic_test.model.Data;
import com.globallogic.global_logic_test.view.base.BaseActivity;
import com.globallogic.global_logic_test.viewModel.DataViewModel;

import java.util.ArrayList;

public class DataActivity extends BaseActivity {
    private ActivityDataBinding binding;
    private DataViewModel viewModel;
    private DataAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_data);
        binding.setLifecycleOwner(this);
        viewModel = new ViewModelProvider(this).get(DataViewModel.class);
        binding.setViewModel(viewModel);
        fetchData();
        observeData();
    }

    private void fetchData() {
        showActivityOverlay();
        viewModel.fetchData();
    }

    private void observeData() {
        viewModel.getOnDataSuccessSingleLiveEvent().observe(this, onDataSuccess -> {
            setupRecycler(onDataSuccess.getDataResponse().getDataArrayList());
            hideActivityOverlay();
        });
        viewModel.getOnDataFailSingleLiveEvent().observe(this , errorResponse -> {
            hideActivityOverlay();
            makeMessage(errorResponse.getErrorDescription());
        });
    }

    private void setupRecycler(ArrayList<Data> data) {
        adapter = new DataAdapter(data);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.recycler.setAdapter(adapter);
        binding.recycler.setLayoutManager(linearLayoutManager);
    }
}