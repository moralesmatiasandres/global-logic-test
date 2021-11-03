package com.globallogic.global_logic_test.view.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.globallogic.global_logic_test.R;
import com.globallogic.global_logic_test.view.base.OverlayHelper;

public class BaseActivity extends AppCompatActivity {

    private OverlayHelper overlayHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prepareView();
    }

    protected void showActivityOverlay() {
        runOnUiThread(() -> getOverlayHelper().showActivityOverlay());
    }

    protected void hideActivityOverlay() {
        runOnUiThread(() -> getOverlayHelper().hideActivityOverlay());
    }


    public void prepareView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        } else {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.gray));
    }


    public OverlayHelper getOverlayHelper() {
        if (overlayHelper == null)
            overlayHelper = createOverlayHelper();
        return overlayHelper;
    }

    private OverlayHelper createOverlayHelper() {
        return new OverlayHelper(this, getLayoutInflater(), getWindow().getDecorView());
    }


    public void makeMessage(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

}