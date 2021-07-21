import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;  
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


public class createJSON {  

    public static void main(String[] args) throws FileNotFoundException, IOException {  


        String partname="";
        String partnumber="";
        String weight ="";
        String line="";


        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String fileName = "C:\\Parts\\parts.csv";
        String jsonfile="C:\\Parts\\Json_batch.json";
        Scanner scanner = new Scanner(new File(fileName));
        File jfile=new File(jsonfile);
        jfile.delete();
        jfile.createNewFile();

        FileWriter fileWriter = new FileWriter(jfile,true);
        fileWriter.write("{ \"parts\":[");
        //Set the delimiter used in file
        while( scanner.hasNextLine())
        {
            line=scanner.nextLine();
            Scanner linesc=new Scanner(line);
            linesc.useDelimiter(",");

            while (linesc.hasNext())
            {
                partname=linesc.next();
                partnumber=linesc.next();
                weight = linesc.next();

                System.out.println("PartName | PartNumber | Weight = "+partname+"|"+partnumber+"|"+weight);
                partname=partname.replaceAll("\\n", "");
                partnumber=partnumber.replaceAll("\\n", "");
                weight=weight.replaceAll("\\n", "");

                JsonObject PartObj = new JsonObject();
                PartObj.addProperty("PartName", partname);
                PartObj.addProperty("PartNumber", partnumber);
                PartObj.addProperty("Weight", weight);

                final String jsonline=gson.toJson(PartObj);
                System.out.println("Final print : "+jsonline);
                fileWriter.write(jsonline);

                if(scanner.hasNext()) {
                    fileWriter.write(",");
                }
            }
        }

        fileWriter.write("]}");
        fileWriter.close();

        scanner.close();

    }
}