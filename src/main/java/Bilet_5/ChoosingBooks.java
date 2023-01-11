package Bilet_5;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
@Slf4j
public class ChoosingBooks {
    String firstBook;
    String secondBook;
    int bookNum = new Random().nextInt(5);

    public int getBookNum() {return bookNum;}
    public List<String> choosingBooks() {


        switch (bookNum){
            case 0:
                firstBook = "War and peace";
                secondBook = "Oblomov";
                break;
            case 1:
                firstBook= "Oblomov";
                secondBook = "War and peace";
                break;
            case 2:
                firstBook = "Green mile";
                secondBook = "Oblomov";
                break;
            case 3:
                firstBook = "War and peace";
                secondBook = "Green mile";
                break;
            case 4:
                firstBook = "Oblomov";
                secondBook = "Green mile";
                break;
            case 5:
                firstBook = "Green mile";
                secondBook = "War and peace";

        }




//        switch (bookNum2){
//            case 0:
//                secondBook = "War and peace";
//                break;
//            case 1:
//                secondBook = "Oblomov";
//                break;
//            case 2:
//                secondBook = "Green mile";
//
//        }
//        log.info(firstBook + "первая книга");
//        log.info(secondBook+ "вторая книга");
//        if (firstBook.equals(secondBook)) {
//
//            while (bookNum2 == bookNum) {
//                bookNum2 = new Random().nextInt(3);
//            }
//            switch (bookNum2){
//                case 0:
//                    secondBook = "War and peace";
//                    break;
//                case 1:
//                    secondBook = "Oblomov";
//                    break;
//                case 2:
//                    secondBook = "Green mile";
//
//            }
//
//
//        }
        List<String> books = new ArrayList<>();
        books.add(firstBook);
        books.add(secondBook);
        return books;
    }
}
