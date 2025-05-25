package practice;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class jsonComparison {


    public static void main(String args[]){

        File jsonFile=new File("src/test/java/practice/json1.json");
        File jsonFile2=new File("src/test/java/practice/json2.json");
        ObjectMapper objectMapper=new ObjectMapper();
        try {
            JsonNode json1 = objectMapper.readTree(jsonFile);
            JsonNode json2 = objectMapper.readTree(jsonFile2);
            System.out.println(json1.equals(json2));
        }catch(IOException e){
            e.printStackTrace();
        }

    }
}
