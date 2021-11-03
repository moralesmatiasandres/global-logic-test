package com.globallogic.global_logic_test.network.response;

import com.globallogic.global_logic_test.model.Data;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DataResponse {
    @SerializedName("data")
    private ArrayList<Data> data;

    public ArrayList<Data> getDataArrayList() {
        return data;
    }
}
