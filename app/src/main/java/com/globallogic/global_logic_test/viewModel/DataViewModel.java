package com.globallogic.global_logic_test.viewModel;

import com.globallogic.global_logic_test.network.repository.DataRepository;
import com.globallogic.global_logic_test.network.response.error.ErrorResponse;
import com.globallogic.global_logic_test.util.SingleLiveEvent;
import com.globallogic.global_logic_test.viewModel.base.BaseViewModel;

public class DataViewModel extends BaseViewModel {
    private DataRepository dataRepository;
    private SingleLiveEvent<DataRepository.OnDataSuccess> onDataSuccessSingleLiveEvent = new SingleLiveEvent<>();
    private SingleLiveEvent<ErrorResponse> onDataFailSingleLiveEvent = new SingleLiveEvent<>();

    public void fetchData() {
        getDataRepository().fetchData();
    }

    private DataRepository getDataRepository() {
        if (dataRepository == null) {
            dataRepository = new DataRepository();
            observeDataRepositorySuccess();
            observeDataRepositoryFail();
        }
        return dataRepository;
    }

    private void observeDataRepositoryFail() {
        getDisposables().add(getDataRepository().getOnDataFail().subscribe(onDataFail -> {
            getOnDataFailSingleLiveEvent().setValue(onDataFail.getErrorResponse());
        }));
    }

    private void observeDataRepositorySuccess() {
        getDisposables().add(getDataRepository().getOnDataSuccess().subscribe(onDataSuccess -> {
            getOnDataSuccessSingleLiveEvent().setValue(onDataSuccess);
        }));
    }

    public SingleLiveEvent<DataRepository.OnDataSuccess> getOnDataSuccessSingleLiveEvent() {
        return onDataSuccessSingleLiveEvent;
    }

    public SingleLiveEvent<ErrorResponse> getOnDataFailSingleLiveEvent() {
        return onDataFailSingleLiveEvent;
    }
}
