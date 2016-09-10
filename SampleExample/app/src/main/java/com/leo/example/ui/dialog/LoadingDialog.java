package com.leo.example.ui.dialog;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;

import com.leo.example.R;
import com.leo.example.databinding.ItemLoadingBinding;
import com.leolibrary.ui.base.dialog.BaseDialog;

/**
 * Created by leo on 16/5/8.
 * Loading Dialog
 */
public class LoadingDialog extends BaseDialog<ItemLoadingBinding> {
    public static LoadingDialog loadingDialog;

    public LoadingDialog(Context context) {
        super(context);
    }

    @Override
    public ItemLoadingBinding beforInitView() {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.item_loading, null, false);
        setContentView(binding.getRoot());
        setCancelable(false);
        return binding;
    }

    @Override
    public void initView() {
    }

    @Override
    public void initData() {
    }

    @Override
    public void initListener() {

    }

    /**
     * 显示loadding
     */
    public static void showLoadding(Context context) {
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(context);
        }
        loadingDialog.show();
    }


    /**
     * 隐藏loadding
     */
    public static void hideLoadding() {
        if (loadingDialog == null || !loadingDialog.isShowing()) {
            return;
        }
        loadingDialog.dismiss();
        loadingDialog = null;
    }
}
