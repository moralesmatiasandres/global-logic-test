package com.globallogic.global_logic_test.customComponent;

import com.globallogic.global_logic_test.network.response.error.ErrorHelper;
import com.globallogic.global_logic_test.network.response.error.ErrorResponse;

import io.reactivex.observers.DisposableSingleObserver;

public abstract  class CustomDisposableSingleObserver<T> extends DisposableSingleObserver<T> {
    public abstract void onError(ErrorResponse errorResponse);

    @Override
    public void onError(Throwable e) {
        onError(ErrorHelper.getError(e));
    }

}
