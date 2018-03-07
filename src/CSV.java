import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
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
//    private ArrayList<Word> bookWords;

    //the constructor will probably initialize with filename

    public void test() {
        System.out.println("this is csv class running");
    }

    public void convertToCSV(ArrayList<Word> bookWords) throws IOException,
            CsvDataTypeMismatchException,
            CsvRequiredFieldEmptyException {
        StringBuilder sb = new StringBuilder(2);
//        sb.append(Integer.toString(number));
        sb.append(outputPath);
        try (
                Writer writer = Files.newBufferedWriter(Paths.get(sb.toString()));
        ) {
            System.out.println("writing bookwords" + bookWords);

            final String[] CSV_HEADER = { "searchedWords", "entries" };
            ColumnPositionMappingStrategy<Word> mappingStrategy =
                    new ColumnPositionMappingStrategy<>();

            mappingStrategy.setType(Word.class);
            mappingStrategy.setColumnMapping(CSV_HEADER);

            StatefulBeanToCsv<Word> beanToCsv = new StatefulBeanToCsvBuilder<Word>(writer)
                    .withMappingStrategy(mappingStrategy)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .build();

//            StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer)
//                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
//                    .build();

            beanToCsv.write(bookWords);
        }
    }

}
