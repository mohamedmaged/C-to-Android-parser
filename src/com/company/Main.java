package com.company;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {


    public static void main(String[] args) throws IOException {
	// write your code here

        //open file
       /* File file = new File("/home/mohamed/Downloads/Clock/Clock/Form1.Designer.cs");
        if(!Desktop.isDesktopSupported()){
            System.out.println("Desktop is not supported");
            return;
        }
        Desktop desktop = Desktop.getDesktop();
        if(file.exists()) desktop.open(file);
*/
;
        String guInputPath=new String("/home/mohamed/Downloads/Clock/Clock/Form1.Designer.cs");
        String logicInputPath=new String("/home/mohamed/Downloads/Clock/Clock/Form1.cs");
        String outputGuiPath=new String("/home/mohamed/AndroidStudioProjects/test/app/src/main/res/layout/activity_main.xml");
Modeling mymodel=new Modeling();
mymodel.ParseGui(guInputPath,outputGuiPath);
        }


}
