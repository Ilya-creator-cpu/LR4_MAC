package Bilet_5;

import Agents.DFhelper;
import Bilet_5.Behaviours.BuyerFSM;
import jade.core.AID;
import jade.core.Agent;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
public class AgentBuyer extends Agent {

    private String neededBook;

    DFhelper dFhelper = new DFhelper();

    private List<AID> agents;
    private int bookNum = new Random().nextInt(3);
    private int bookNum2 = new Random().nextInt(3);
    private String book;

    private String secondBook;

    ChoosingBooks bookChoser = new ChoosingBooks();

    private List<String> books = new ArrayList<>();

    @Override
    protected void setup() {


        log.info("Buyer woke up");
        dFhelper.registerAgent(this, "Buyer");
        agents = DFhelper.findAgents(this,"Seller");
        book = bookChoser.choosingBooks().get(0);
        secondBook = bookChoser.choosingBooks().get(1);

        log.info("Первая книга для покупки " + book + ", " + "вторая книга для покупки " + secondBook);
//        log.info("Число рандома {} ", bookChoser.getBookNum() );
//        if (book.equals(secondBook)) {
//            log.info("Одинаковые книги");}
        addBehaviour(new BuyerFSM(book,secondBook));
    }
    public String choosingBooks(int bookNum) {
        switch (bookNum){
            case 0:
                book = "War and peace";
                break;
            case 1:
                book = "Oblomov";
                break;
            case 2:
                book = "Green mile";

        }
        return book;
    }
}

