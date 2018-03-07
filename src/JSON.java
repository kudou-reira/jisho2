import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class JSON {
    private String outputPath = "./object-path-sample.json";
//    private ArrayList<Word> bookWords;

    //the constructor will probably initialize with filename

    public void test() {
        System.out.println("this is json class running");
    }

    public void convertToJSONFile(ArrayList<Word> bookWords) {
        StringBuilder sb = new StringBuilder(2);
//        sb.append(Integer.toString(number));
        sb.append(outputPath);

        Gson gson = new Gson();
        try {
            gson.toJson(bookWords, new FileWriter(outputPath));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void fileJSON(ArrayList<Word> bookWords) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileWriter temp = new FileWriter(outputPath);
//        System.out.println(bookWords);
        try {
            temp = new FileWriter(outputPath);
            gson.toJson(bookWords, temp);
//            temp.close();

        } finally {
            temp.close();
        }
    }
}
