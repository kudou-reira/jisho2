import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        // each book will have words sent in as an array list
        // however, probably want these to be separated
        // jisho should take a single word
        // another class should hold the collection of jisho
        List<String> book1 = Arrays.asList("一", "頂", "理");
        List<String> book2 = Arrays.asList("詰め", "堂", "射");
        List<String> book3 = Arrays.asList("吐き出", "綱渡り", "赤外");
        List<String> book4 = Arrays.asList("押しつける", "反省", "自覚");

        ArrayList<List> tempList = new ArrayList<>();

        tempList.add(book1);
//        tempList.add(book2);
//        tempList.add(book3);
//        tempList.add(book4);


        ExecutorService executor = Executors.newWorkStealingPool();

        // later on have to create the array of words in books?
        // here, it initializes jisho book (collection of words), but maybe it's better to initialize word by word in a different class
        // iterate over arraylist of Lists and add lambda functions to them
        List<Callable<Jisho>> callables = createLists(tempList);

        // the executor invokes all instantiating of a new object () -> new Jisho(book1); etc
        try {
            executor.invokeAll(callables)
                    .stream()
                    .map(future -> {
                        try {
                            return future.get();
                        }
                        catch (Exception e) {
                            throw new IllegalStateException(e);
                        }
                    })
                    .forEach((book) -> {
                        book.fetchData();
                        System.out.println("this is book searched words " + book.getSearchedWords().toString());
                        // probably next should take this function and convert to csv
                        ArrayList<Word> bookWords = book.getSearchedWords();
                        JSON tempJSON = new JSON();
                        System.out.println("should pretty print");
                        try {
                            tempJSON.fileJSON(bookWords);
                        } catch(IOException e) {
                            e.printStackTrace();
                        }

                    });
        } catch(InterruptedException e) {
            e.printStackTrace();
        }

//        Jisho temp1 = new Jisho(book1);
//        temp1.fetchData();
//        System.out.println(temp1.getSearchedWords().toString());
    }

    private static List<Callable<Jisho>> createLists(ArrayList<List> tempList) {
        List<Callable<Jisho>> tempCallables = new ArrayList<>();
        for(List temp : tempList) {
            tempCallables.add(() -> new Jisho(temp));
        }
        return tempCallables;
    }
}
