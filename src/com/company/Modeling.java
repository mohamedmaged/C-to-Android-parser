package com.company;


import java.util.ArrayList;
import java.util.List;

public class Modeling {

    List<String> dataInput = new ArrayList<String>();
    List<String> dataOutput = new ArrayList<String>();
    FileHandler myFile = new FileHandler();
    List<MyLabel> myLabels = new ArrayList<MyLabel>();

    public void ParseGui(String inputPath,String outputPath) {
        dataInput = myFile.readFile(inputPath);

        getlabels();
        for (int i = 0; i < myLabels.size(); i++) {
            System.out.println(myLabels.get(i).name + "    " + myLabels.get(i).text + "    " + myLabels.get(i).size.x + "     " + myLabels.get(i).size.y + "      " + myLabels.get(i).location.x + "     " + myLabels.get(i).location.y);
        }
        myFile.writeFile(createGui(),outputPath);
    }

    private void getlabels() {
        char[] text = null;
        char[] name = null;
        Point y = null;
        char[] location = null;
        Point x = null;


        int m = 1;
        for (int i = 0; i < dataInput.size(); i++) {

            int n = dataInput.get(i).indexOf("label" + m + ".Name = ");
            int l = dataInput.get(i).indexOf("label" + m + ".Location = ");
            int s = dataInput.get(i).indexOf("label" + m + ".Size = ");
            int t = dataInput.get(i).indexOf("label" + m + ".Text = ");
            if (n != -1) {
                n = n + 15;
                name = new char[dataInput.get(i).length() - 2 - n];
                for (int j = n; j < dataInput.get(i).length() - 2; j++) {

                    name[j - n] = dataInput.get(i).charAt(j);
                }

            }
            if (s != -1) {
                StringBuilder six = new StringBuilder();
                StringBuilder siy = new StringBuilder();
                x = new Point();

                s = s + 15 + 23;
                int h = 0;
                for (int j = s; j < dataInput.get(i).length() - 2; j++) {
                    if (dataInput.get(i).charAt(j) != ',' && h == 0)
                        six.append(dataInput.get(i).charAt(j));

                    else {
                        if (dataInput.get(i).charAt(j) == ',')
                            j++;
                        siy.append(dataInput.get(i).charAt(j));
                        h++;
                    }

                }

                x.setPoint(six.toString(), siy.toString());
            }
            if (t != -1) {
                t = t + 15;
                text = new char[dataInput.get(i).length() - 2 - t];
                for (int j = t; j < dataInput.get(i).length() - 2; j++) {

                    text[j - t] = dataInput.get(i).charAt(j);

                }

                System.out.println(new String(text));
                m++;
                myLabels.add(new MyLabel(new String(text), new String(name), x, y));

            }

            if (l != -1) {
                StringBuilder lx = new StringBuilder();
                StringBuilder ly = new StringBuilder();
                y = new Point();
                l = l + 15 + 23 + 5;
                Boolean flag = true;
                for (int j = l; j < dataInput.get(i).length() - 2; j++) {
                    if (dataInput.get(i).charAt(j) != ',' && flag)
                        lx.append(dataInput.get(i).charAt(j));

                    else {
                        if (dataInput.get(i).charAt(j) == ',')
                            j++;
                        ly.append(dataInput.get(i).charAt(j));
                        flag=false;
                    }

                }
                y.setPoint(lx.toString(), ly.toString());
            }

        }


    }

    public String createGui()
    {
        StringBuilder GUI=new StringBuilder("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<android.support.constraint.ConstraintLayout xmlns:android=\"http://schemas.android.com/apk/res/android\"\n" +
                "    xmlns:app=\"http://schemas.android.com/apk/res-auto\"\n" +
                "    xmlns:tools=\"http://schemas.android.com/tools\"\n" +
                "    android:layout_width=\"match_parent\"\n" +
                "    android:layout_height=\"match_parent\"\n" +
                "    tools:context=\"com.example.mohamed.test.MainActivity\">"+"\n"+"\n");
        for (int i = 0; i < myLabels.size(); i++) {
            String labeltext=new String("<TextView \n" +
                    "        android:textSize=\"30sp\"\n" +
                    "        android:layout_width=\"wrap_content\"\n" +
                    "        android:layout_height=\"wrap_content\"\n" +
                    "        app:layout_constraintBottom_toBottomOf=\"parent\"\n" +
                    "        app:layout_constraintLeft_toLeftOf=\"parent\"\n" +
                    "        app:layout_constraintRight_toRightOf=\"parent\"\n" +
                    "        app:layout_constraintTop_toTopOf=\"parent\"\n"+
                    "        android:id =\"@+id/"+myLabels.get(i).name+"\"\n"+
                    "        android:text=\""+myLabels.get(i).text+"\"\n"+
                    "        android:layout_marginTop=\""+myLabels.get(i).location.x+"dp\"\n"+
                    "        android:layout_marginBottom=\""+100*(i+1)+"dp\"\n"+
                    "                                   />"+"\n"+"\n");
            GUI.append(labeltext);
        }
GUI.append("</android.support.constraint.ConstraintLayout>");
return(GUI.toString());
    }
    public void parseLogic(String input,String output)
    {
        getlabelsData(input);

        StringBuilder logic=new StringBuilder("package com.example.mohamed.test;\n" +
                "\n" +
                "import android.support.v7.app.AppCompatActivity;\n" +
                "import android.os.Bundle;\n" +
                "import android.widget.TextView;\n" +
                "\n" +
                "import java.text.DateFormat;\n" +
                "import java.text.SimpleDateFormat;\n" +
                "import java.util.Calendar;\n" +
                "import java.util.Date;\n" +
                "import java.util.Locale;\n" +
                "\n" +
                "public class MainActivity extends AppCompatActivity {\n" +
                "\n" +
                "    @Override\n" +
                "    protected void onCreate(Bundle savedInstanceState) {\n" +
                "        super.onCreate(savedInstanceState);\n" +
                "        setContentView(R.la    yout.activity_main);"+"\n");
        for (int i = 0; i < myLabels.size(); i++) {
            logic.append("TextView "+myLabels.get(i).name+" =(TextView) findViewById(R.id."+myLabels.get(i).name+");\n");
            if(myLabels.get(i).date)
            logic.append(myLabels.get(i).name+".setText("+"new SimpleDateFormat(\"dd-MM-yyyy\", Locale.getDefault()).format(new Date())"+");"+"\n");
           else if(myLabels.get(i).time & myLabels.get(i).seconds)
            {
                logic.append("DateFormat df = new SimpleDateFormat(\"HH:mm:ss aa\");"+"\n");
                logic.append(myLabels.get(i).name+".setText("+"df.format(Calendar.getInstance().getTime()));"+"\n");
            }
            else if(myLabels.get(i).time)
            {
                logic.append("DateFormat df = new SimpleDateFormat(\"HH:mm aa\");"+"\n");
                logic.append(myLabels.get(i).name+".setText("+"df.format(Calendar.getInstance().getTime()));"+"\n");
            }
        }
        logic.append("}\n}");
        myFile.writeFile(logic.toString(),output);

    }

    public void getlabelsData(String input)
    {
        List<String> dinput = myFile.readFile(input);
        boolean flag=false;
        for (int i = 0; i < dinput.size(); i++) {
            int t=dinput.get(i).indexOf("InitializeComponent();");
            if(t!=-1 || flag)
            {
                flag=true;

                for (int j = 0; j <myLabels.size() ; j++) {

                   if(dinput.get(i).toString().contains(myLabels.get(j).name+".Text"))
                    {
                           if( dinput.get(i).toString().contains("hh:mm:ss tt"))
                           {
                               myLabels.get(j).time = true;
                                myLabels.get(j).seconds=true;
                           }
                          else if( dinput.get(i).toString().contains("hh:mm tt"))
                           {
                               myLabels.get(j).time=true;
                           }
                           else if(dinput.get(i).toString().contains("dd/MM/yyyy"))
                           {
                               myLabels.get(j).date=true;
                           }

                    }
                }


            }
        }
    }


}
