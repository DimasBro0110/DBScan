import com.dimas.CustomDBScan.CSVFileReader;
import com.dimas.CustomDBScan.DBScanInstance;
import com.dimas.CustomDBScan.DBScanPoint;
import org.apache.commons.math3.ml.clustering.Cluster;
import org.apache.commons.math3.ml.distance.ManhattanDistance;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by DmitriyBrosalin on 24/03/2017.
 */
public class Main {

    public static void main(String[] args) throws IOException {

        CSVFileReader reader = new CSVFileReader(
                "path_to_your_file",
                25,
                ",");
        List<DBScanPoint> dataSet = reader.getListOfFeatures();

        DBScanInstance instance = new DBScanInstance(dataSet,
                0.027,
                1,
                new ManhattanDistance());

        List<Cluster<DBScanPoint>> resultData = instance.fitTransform();
        for(Cluster<DBScanPoint> points: resultData){
            for(DBScanPoint customPoint: points.getPoints()){
                System.out.println("#####");
                System.out.println(customPoint.getTextMessage());
                System.out.println(Arrays.toString(customPoint.getPoint()));
                System.out.println("#####");
            }
        }

    }

}
