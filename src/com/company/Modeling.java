package com.company;


import java.util.ArrayList;
import java.util.List;

public class Modeling {

List<String> dataInput=new ArrayList<String>();
List<String> dataOutput=new ArrayList<String>();
FileHandler myFile=new FileHandler();
List<MyLabel> myLabels=new ArrayList<MyLabel>();

public void ParseGui()
{
    dataInput=myFile.readFile();

    getlabels();
    for (int i = 0; i < myLabels.size(); i++)
    {
        System.out.println(myLabels.get(i).name + "    "+myLabels.get(i).text+"    "+myLabels.get(i).size.x+ "     " +myLabels.get(i).size.y + myLabels.get(i).location.x + "     "+myLabels.get(i).location.y);

    }

}

private void getlabels()
{
    char[] text=null;
    char[] name=null;
    Point y=null;
    char[] location=null;
    Point x=null;


    int m=1;
    for(int i=0;i<dataInput.size();i++)
    {

       int n = dataInput.get(i).indexOf("label"+m+".Name = ");
       int l = dataInput.get(i).indexOf("label"+m+".Location = ");
       int s = dataInput.get(i).indexOf("label"+m+".Size = ");
       int t = dataInput.get(i).indexOf("label"+m+".Text = ");
      if(n!=-1)
      {
          n=n+15;
          name=new char[dataInput.get(i).length()-2-n];
          for (int j=n ;j<dataInput.get(i).length()-2;j++)
          {

              name [j-n]=dataInput.get(i).charAt(j);
          }

      }
        if(s!=-1)
        {
            StringBuilder six=new StringBuilder();
            StringBuilder siy=new StringBuilder();
            x=new Point();

            s=s+15+23;
            int h=0;
            for (int j=s ;j<dataInput.get(i).length()-2;j++)
            {
                if(dataInput.get(i).charAt(j)!=',' && h==0)
                    six.append(dataInput.get(i).charAt(j));

                else
                {
                    if (dataInput.get(i).charAt(j)==',')
                        j++;
                    siy.append(dataInput.get(i).charAt(j));
                    h++;
                }

            }

            x.setPoint(six.toString(),siy.toString());
        }
        if(t!=-1)
        {
            t=t+15;
            text=new char[dataInput.get(i).length()-2-t];
            for (int j=t ;j<dataInput.get(i).length()-2;j++)
            {

                text[j-t]=dataInput.get(i).charAt(j);

            }

            System.out.println(new String(text));
            m++;
            myLabels.add(new MyLabel(new String(text),new String(name),x,y));

        }

        if(l!=-1)
        {
            StringBuilder lx=new StringBuilder();
            StringBuilder ly=new StringBuilder();
            y=new Point();
            l=l+15+23+5;
            int h=0;
            for (int j=s ;j<dataInput.get(i).length()-2;j++)
            {
                if(dataInput.get(i).charAt(j)!=',' && h==0)
                    lx.append(dataInput.get(i).charAt(j));

                else
                {
                    if (dataInput.get(i).charAt(j)==',')
                        j++;
                    ly.append(dataInput.get(i).charAt(j));
                    h++;
                }

            }
            y.setPoint(lx.toString(),ly.toString());
        }

    }
}

}
