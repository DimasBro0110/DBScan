package com.dimas.CustomDBScan;

import org.apache.commons.math3.ml.clustering.Clusterable;

/**
 * Created by DmitriyBrosalin on 24/03/2017.
 */
public class DBScanPoint implements Clusterable {

    private double[] textFeatures;
    private CustomPoint point;

    public DBScanPoint(CustomPoint point){
        this.point = point;
        this.textFeatures = point.getFeaturesSms();
    }

    public double[] getPoint() {
        return this.textFeatures;
    }

    public CustomPoint getPointStructure(){
        return this.point;
    }

}
