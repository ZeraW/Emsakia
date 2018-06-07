package com.hima.atef.splash.Utilities;

/**
 * Created by Hima on 3/20/2018.
 */

public class ModelTimeAndDate  {

    private String date ;
    private String Fagr;
    private String Shrouq;
    private String Zohr;
    private String Asr;
    private String Magreb;
    private String Ishaa;
    private String EMSAK;

    public ModelTimeAndDate(String date,String emsak , String fagr, String shrouq, String zohr, String asr, String magreb, String ishaa) {
        this.date = date;
        this.Fagr = fagr;
        this.Shrouq = shrouq;
        this.Zohr = zohr;
        this.Asr = asr;
        this.Magreb = magreb;
        this.Ishaa = ishaa;
        this.EMSAK = emsak;

    }

    public String getDate() {
        return date;
    }

    public String getFagr() {
        return Fagr;
    }

    public String getShrouq() {
        return Shrouq;
    }

    public String getZohr() {
        return Zohr;
    }

    public String getAsr() {
        return Asr;
    }

    public String getMagreb() {
        return Magreb;
    }

    public String getIshaa() {
        return Ishaa;
    }

    public String getEMSAK() {
        return EMSAK;
    }
}
