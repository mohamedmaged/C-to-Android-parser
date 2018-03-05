package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    String inputPath=new String("/home/mohamed/Downloads/Clock/Clock/Form1.Designer.cs");
    String outputPath=new String();
    public  List<String> readFile()
    {
        List<String> records = new ArrayList<String>();
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(inputPath));
            String line;
            while ((line = reader.readLine()) != null)
            {
                records.add(line);
            }
            reader.close();
            return records;
        }
        catch (Exception e)
        {
            System.err.format("Exception occurred trying to read '%s'.", inputPath);
            e.printStackTrace();
            return null;
        }
    }

}
