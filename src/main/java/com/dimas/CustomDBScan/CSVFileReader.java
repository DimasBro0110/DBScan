package com.dimas.CustomDBScan;

import org.apache.commons.math3.ml.clustering.DoublePoint;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by DmitriyBrosalin on 24/03/2017.
 */
public class CSVFileReader {

    private List<CustomPoint> listOfFeatures;
    private Map<Integer, String> mapOfHashes;
    private String pathToFile;
    private int amountOfColumns;
    private String csvSeparator;

    public CSVFileReader(String pathToFile, int amountOfColumns,
                         String csvSeparator) throws IOException
    {
        this.mapOfHashes = new HashMap<Integer, String>();
        this.pathToFile = pathToFile;
        this.amountOfColumns = amountOfColumns;
        this.csvSeparator = csvSeparator;
    }

    public List<CustomPoint> readDataFromLocalFileSystem() throws IOException
    {
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        List<CustomPoint> listOfData = new ArrayList<CustomPoint>();
        try{
            fileReader = new FileReader(pathToFile);
            bufferedReader = new BufferedReader(fileReader);
            String currentString = "";
            double[] currentFeatureVector = new double[amountOfColumns - 1];
            while((currentString = bufferedReader.readLine()) != null){
                String[] partsOfCurrentString = currentString.split(csvSeparator);
                for(int i=0; i<amountOfColumns-1; i++){
                    currentFeatureVector[i] = Float.parseFloat(partsOfCurrentString[i]);
                }
                listOfData.add(new CustomPoint(partsOfCurrentString[amountOfColumns], currentFeatureVector));
                mapOfHashes.put(Arrays.toString(currentFeatureVector).hashCode(),
                        partsOfCurrentString[amountOfColumns]);
            }

            return listOfData;
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            bufferedReader.close();
        }
        return null;
    }

    public List<CustomPoint> getListOfFeatures(){
        return this.listOfFeatures;
    }

    public List<DoublePoint> readFeaturesFromFile() throws IOException {
        FileReader fr = new FileReader(new File(pathToFile));
        BufferedReader br = new BufferedReader(fr);
        String curLine = "";
        List<DoublePoint> lst = new ArrayList<DoublePoint>();
        while((curLine = br.readLine()) != null){
            String[] feats = curLine.split(";");
            double[] array_feats = new double[amountOfColumns - 1];
            int counter = 0;
            for(int i=0; i<amountOfColumns - 1; i++){
                array_feats[counter] = Double.parseDouble(feats[i]);
                counter += 1;
            }
            mapOfHashes.put(Arrays.toString(array_feats).hashCode(),
                    feats[amountOfColumns]);
            lst.add(new DoublePoint(array_feats));
        }
        br.close();
        return lst;
    }

    public Map<Integer, String> getMapOfHashes(){
        return this.mapOfHashes;
    }

}
