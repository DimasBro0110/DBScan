package com.dimas.CustomDBScan;

import org.apache.commons.math3.ml.clustering.Clusterable;

/**
 * Created by DmitriyBrosalin on 24/03/2017.
 */
public class DBScanPoint implements Clusterable {

    private String textMessage;
    private double[] textFeatures;

    public DBScanPoint(String text, double[] features){
        this.textMessage = text;
        this.textFeatures = features;
    }

    public double[] getPoint() {
        return this.textFeatures;
    }

    public String getTextMessage() {
        return textMessage;
    }
}
