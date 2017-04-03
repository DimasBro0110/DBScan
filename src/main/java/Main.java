import com.dimas.CustomDBScan.CSVFileReader;
import com.dimas.CustomDBScan.CustomPoint;
import com.dimas.CustomDBScan.DBScanInstance;
import com.dimas.CustomDBScan.DBScanPoint;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.SingularValueDecomposition;
import org.apache.commons.math3.ml.clustering.*;
import org.apache.commons.math3.ml.distance.ManhattanDistance;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by DmitriyBrosalin on 24/03/2017.
 */
public class Main {

    public static double[][] getTruncatedSVD(double[][] matrix, final int k) {
        SingularValueDecomposition svd = new SingularValueDecomposition(new Array2DRowRealMatrix(matrix));

        double[][] truncatedU = new double[svd.getU().getRowDimension()][k];
        svd.getU().copySubMatrix(0, truncatedU.length - 1, 0, k - 1, truncatedU);

        double[][] truncatedS = new double[k][k];
        svd.getS().copySubMatrix(0, k - 1, 0, k - 1, truncatedS);

        double[][] truncatedVT = new double[k][svd.getVT().getColumnDimension()];
        svd.getVT().copySubMatrix(0, k - 1, 0, truncatedVT[0].length - 1, truncatedVT);

        RealMatrix approximatedSvdMatrix = (new Array2DRowRealMatrix(truncatedU)).multiply(new Array2DRowRealMatrix(truncatedS)).multiply(new Array2DRowRealMatrix(truncatedVT));

        return approximatedSvdMatrix.getData();
    }

    public static void main(String[] args) throws IOException {

        CSVFileReader reader = new CSVFileReader(
                "***",
                25,
                ";");
        List<DoublePoint> dataSet = reader.readFeaturesFromFile();

        DBSCANClusterer<DoublePoint> clusterer = new DBSCANClusterer<DoublePoint>(0.07, 70);
        List<Cluster<DoublePoint>> result = clusterer.cluster(dataSet);

        Map<Integer, String> helper = reader.getMapOfHashes();

        int counter = 0;
        String sourceDirectory = "***";

        for(Cluster<DoublePoint> singleCluster: result){
            BufferedWriter br = new BufferedWriter(new FileWriter(
                    sourceDirectory + "cluster_" + counter + ".txt")
            );
            List<DoublePoint> currentPoints = singleCluster.getPoints();
            for(DoublePoint point: currentPoints){
                int hash = Arrays.toString(point.getPoint()).hashCode();
                String smsText = helper.get(hash);
                if(smsText != null){
                    br.write(smsText + "\n");
                }
            }
            br.close();
            counter += 1;
        }

    }

}
