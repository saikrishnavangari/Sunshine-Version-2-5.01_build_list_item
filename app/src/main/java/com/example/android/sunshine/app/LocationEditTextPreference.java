package com.example.android.sunshine.app;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by krrish on 9/12/2016.
 */

public class LocationEditTextPreference extends EditTextPreference {
    static final private int DEFAULT_MIN_LOCATION_LENGTH = 2;
    private int mMinLength;

    public LocationEditTextPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.locationEditTextPrefernce, 0, 0);

        try {
            mMinLength = a.getInteger(R.styleable.locationEditTextPrefernce_minLength, DEFAULT_MIN_LOCATION_LENGTH);
            Log.d("setting activity", String.valueOf(mMinLength));
        } finally {
            a.recycle();
        }
    }

    @Override
    protected void showDialog(Bundle state) {
        super.showDialog(state);
        EditText et = getEditText();

        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Dialog d = getDialog();
                if (d instanceof AlertDialog) {
                    AlertDialog alertDialog = (AlertDialog) d;
                    Button positiveButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                    if (editable.length() < mMinLength)
                        positiveButton.setEnabled(false);
                    else
                        positiveButton.setEnabled(true);
                }
            }
        });
    }
}
