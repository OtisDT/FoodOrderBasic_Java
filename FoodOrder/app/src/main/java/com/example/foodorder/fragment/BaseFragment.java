package com.example.foodorder.fragment;

import androidx.fragment.app.Fragment;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.foodorder.R;

public abstract class BaseFragment extends Fragment {

    @Override
    public void onResume() {
        super.onResume();
        initToolbar();
    }

    protected abstract void initToolbar();
}
