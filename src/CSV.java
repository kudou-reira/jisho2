import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class CSV {
    private String outputPath = "./object-path-sample.csv";
    private ArrayList<Word> bookWords;
    private int number;

    public CSV(ArrayList<Word> bookWords, int number) {
        this.bookWords = bookWords;
        this.number = number;
    }

    public void convertToCSV() throws IOException,
            CsvDataTypeMismatchException,
            CsvRequiredFieldEmptyException {
        StringBuilder sb = new StringBuilder(2);
//        sb.append(Integer.toString(number));
        sb.append(outputPath);
        try (
                Writer writer = Files.newBufferedWriter(Paths.get(sb.toString()));
        ) {
            StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .build();

            System.out.println("bookwords" + bookWords);

            beanToCsv.write(bookWords);
        }
    }

}
