package com.dimas.CustomDBScan;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DmitriyBrosalin on 24/03/2017.
 */
public class CSVFileReader {

    List<DBScanPoint> listOfFeatures;

    public CSVFileReader(String pathToFile, int amountOfColumns,
                         String csvSeparator) throws IOException
    {
        listOfFeatures = readDataFromLocalFileSystem(pathToFile, amountOfColumns, csvSeparator);
    }

    private List<DBScanPoint> readDataFromLocalFileSystem(String pathToFile, int amountOfColumns,
                                                          String csvSeparator) throws IOException
    {
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        List<DBScanPoint> listOfData = new ArrayList<DBScanPoint>();
        try{
            fileReader = new FileReader(pathToFile);
            bufferedReader = new BufferedReader(fileReader);
            String currentString = "";
            double[] currentFeatureVector = new double[amountOfColumns - 1];
            while((currentString = bufferedReader.readLine()) != null){
                String[] partsOfCurrentString = currentString.split(csvSeparator);
                for(int i=0; i<amountOfColumns-1; i++){
                    currentFeatureVector[i] = Double.parseDouble(partsOfCurrentString[i]);
                }
                listOfData.add(new DBScanPoint(partsOfCurrentString[amountOfColumns], currentFeatureVector));
            }
            return listOfData;
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            bufferedReader.close();
        }
        return null;
    }

    public List<DBScanPoint> getListOfFeatures(){
        return this.listOfFeatures;
    }

}
