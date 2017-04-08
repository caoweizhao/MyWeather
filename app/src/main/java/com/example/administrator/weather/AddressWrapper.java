package com.example.administrator.weather;

import com.example.administrator.weather.mvp.model.locationbean.IAddress;

/**
 * Created by Administrator on 2017-3-28.
 */

public class AddressWrapper {
    private IAddress iAddress;

    public AddressWrapper(IAddress iAddress) {
        this.iAddress = iAddress;
    }

    public IAddress getIAddress() {
        return iAddress;
    }
}
