package com.globallogic.global_logic_test.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.globallogic.global_logic_test.BR;
import com.globallogic.global_logic_test.R;
import com.globallogic.global_logic_test.model.Data;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ListViewHolder> {
    private ArrayList<Data> dataArrayList;

    public DataAdapter(ArrayList<Data> dataArrayList) {
        this.dataArrayList = dataArrayList;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding viewDataBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                viewType, parent, false);
        return new ListViewHolder(viewDataBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        Data data = dataArrayList.get(position);
        holder.bind(data, BR.data);
    }

    @Override
    public int getItemCount() {
        return dataArrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.item_data;
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        ViewDataBinding itemRowBinding;


        public ListViewHolder(ViewDataBinding itemRowBinding) {
            super(itemRowBinding.getRoot());
            this.itemRowBinding = itemRowBinding;
        }

        void bind(Data data, int variableName) {
            itemRowBinding.setVariable(variableName, data);
            itemRowBinding.executePendingBindings();
        }
    }
}
