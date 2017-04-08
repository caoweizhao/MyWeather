package com.example.administrator.weather.mvp.model.weatherbean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2017-3-25.
 */

public class DailyCondBean {
    /**
     * code_d : 100
     * code_n : 104
     * txt_d : 晴
     * txt_n : 阴
     */

    @SerializedName("code_d")
    private String codeD;
    @SerializedName("code_n")
    private String codeN;
    @SerializedName("txt_d")
    private String txtD;
    @SerializedName("txt_n")
    private String txtN;

    public String getCodeD() {
        return codeD;
    }

    public void setCodeD(String codeD) {
        this.codeD = codeD;
    }

    public String getCodeN() {
        return codeN;
    }

    public void setCodeN(String codeN) {
        this.codeN = codeN;
    }

    public String getTxtD() {
        return txtD;
    }

    public void setTxtD(String txtD) {
        this.txtD = txtD;
    }

    public String getTxtN() {
        return txtN;
    }

    public void setTxtN(String txtN) {
        this.txtN = txtN;
    }
}
