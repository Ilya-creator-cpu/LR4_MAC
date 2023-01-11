package Bilet_5.Behaviours;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
@Slf4j
public class WaitingAnswerFromSellers extends Behaviour {

    private List<Integer> prices = new ArrayList<>();

    private int minprice;

    private int answercounter;

    private boolean end;


    @Override
    public void action() {
        //log.info("ожидаем ответа продавцов");
        MessageTemplate mt = MessageTemplate.or(MessageTemplate.MatchPerformative(ACLMessage.AGREE),
                MessageTemplate.MatchPerformative(ACLMessage.CANCEL));
        ACLMessage receive = getAgent().receive(mt);
        if (receive!= null) {
            answercounter++;
            if (Integer.parseInt(receive.getContent()) != 0) {
                // log.info("Цена за книгу получена");
                prices.add(Integer.parseInt(receive.getContent()));
                //  log.info(prices.toString());
                minprice = prices.get(0);
            }
        }

        if (answercounter == 3) {
            for (Integer price : prices) {
                if (price < minprice)
                    minprice = price;
            }
            log.info("Итоговая цена за книгу "  + receive.getProtocol() +" " + Integer.toString(minprice));
           // log.info("Список цен {}",prices.toString());
            end = true;
        }
    }

    @Override
    public boolean done() {
        return end;
    }
}
