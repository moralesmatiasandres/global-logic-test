package com.globallogic.global_logic_test.network.repository;

import com.globallogic.global_logic_test.customComponent.CustomDisposableSingleObserver;
import com.globallogic.global_logic_test.network.RestClient;
import com.globallogic.global_logic_test.network.response.DataResponse;
import com.globallogic.global_logic_test.network.response.error.ErrorResponse;
import com.globallogic.global_logic_test.network.response.error.ErrorWrapper;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

public class DataRepository {
    private PublishSubject<OnDataSuccess> onDataSuccess = PublishSubject.create();
    private PublishSubject<OnDataFail> onDataFail = PublishSubject.create();

    public PublishSubject<OnDataSuccess> getOnDataSuccess() {
        return onDataSuccess;
    }

    public PublishSubject<OnDataFail> getOnDataFail() {
        return onDataFail;
    }

    public CustomDisposableSingleObserver<DataResponse> fetchData() {
        return RestClient.getInstance().fetchData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getDataObserver());
    }

    private CustomDisposableSingleObserver<DataResponse> getDataObserver() {
        return new CustomDisposableSingleObserver<DataResponse>() {
            @Override
            public void onError(ErrorResponse errorResponse) {
                onDataFail.onNext(new OnDataFail(errorResponse));
            }

            @Override
            public void onSuccess(@NonNull DataResponse dataResponse) {
                onDataSuccess.onNext(new OnDataSuccess(dataResponse));
            }
        };
    }

    public class OnDataSuccess {
        private DataResponse dataResponse;

        public OnDataSuccess(DataResponse dataResponse) {
            this.dataResponse = dataResponse;
        }

        public DataResponse getDataResponse() {
            return dataResponse;
        }
    }

    public class OnDataFail extends ErrorWrapper {
        public OnDataFail(ErrorResponse errorResponse) {
            super(errorResponse);
        }
    }
}
