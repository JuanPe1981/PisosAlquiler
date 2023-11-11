package com.svalero.pisosalquiler.presenter;

import com.svalero.pisosalquiler.adapter.MenuAdapterView;
import com.svalero.pisosalquiler.contract.MenuAdapterContract;
import com.svalero.pisosalquiler.model.MenuAdapterModel;

public class MenuAdapterPresenter implements MenuAdapterContract.Presenter {

    private MenuAdapterModel model;
    private MenuAdapterView view;

    public MenuAdapterPresenter (MenuAdapterView view) {
        this.model = new MenuAdapterModel();
        this.view = view;
    }
}
