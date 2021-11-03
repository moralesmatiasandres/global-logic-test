package com.globallogic.global_logic_test.network;

import com.globallogic.global_logic_test.network.response.DataResponse;
import com.globallogic.global_logic_test.util.Constants;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface Api {
    @GET(Constants.FETCH_DATA)
    Single<DataResponse> fetchData();

}
