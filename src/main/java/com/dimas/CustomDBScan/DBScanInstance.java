package com.dimas.CustomDBScan;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.math3.ml.clustering.Cluster;
import org.apache.commons.math3.ml.clustering.DBSCANClusterer;
import org.apache.commons.math3.ml.distance.DistanceMeasure;

import java.util.List;

/**
 * Created by DmitriyBrosalin on 24/03/2017.
 */
public class DBScanInstance {

    private DBSCANClusterer<DBScanPoint> dbScanInstance;
    private List<DBScanPoint> customDataSet;
    private double pointRadius;
    private int amountOfPointsInCluster;
    private DistanceMeasure distanceMeasure;
    private static final Logger logger =
            Logger.getLogger(DBScanInstance.class.getName());

    public DBScanInstance(List<DBScanPoint> data, double pointRadius, int amountOfPointsInCluster,
                          DistanceMeasure measure)
    {
        this.customDataSet = data;
        this.pointRadius = pointRadius;
        this.amountOfPointsInCluster = amountOfPointsInCluster;
        this.distanceMeasure = measure;
    }

    public List<Cluster<DBScanPoint>> fitTransform(){
        if(this.customDataSet != null){
            long timeStart = System.currentTimeMillis();
            this.dbScanInstance = new DBSCANClusterer<DBScanPoint>(
                    0.021, 120, this.distanceMeasure);
            List<Cluster<DBScanPoint>> resultOfClustering =  this.dbScanInstance.cluster(this.customDataSet);
            long stopTime = System.currentTimeMillis();
            logger.log(Level.INFO,
                    "Clustering time elapsed: " + (stopTime - timeStart) / 1000 + ", sec");
            return resultOfClustering;
        } else
            return null;
    }

}
