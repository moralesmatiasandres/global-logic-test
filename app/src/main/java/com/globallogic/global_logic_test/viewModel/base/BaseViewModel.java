package com.globallogic.global_logic_test.viewModel.base;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import io.reactivex.disposables.Disposable;

public class BaseViewModel extends ViewModel {
    private ArrayList<Disposable> disposables = new ArrayList<>();

    protected ArrayList<Disposable> getDisposables() {
        return disposables;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        for (Disposable disposable : disposables) {
            if (disposable != null) {
                disposable.dispose();
            }
        }
    }
}
