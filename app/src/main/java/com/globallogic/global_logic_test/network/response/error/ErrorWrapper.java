package com.globallogic.global_logic_test.network.response.error;

public class ErrorWrapper {
    private ErrorResponse errorResponse;

    public ErrorWrapper(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }

    public void setErrorResponse(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }
}
