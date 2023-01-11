package Bilet_5.Behaviours;

import Agents.DFhelper;
import Bilet_5.ChoosingBooks;
import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.extern.slf4j.Slf4j;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
    public class SellerBehaviour extends Behaviour {

        private int price;

        private int high;

        private int low;

        Random r = new Random();
        DFhelper dFhelper = new DFhelper();

        List<String> books = new ArrayList<>();

        List<String> removedBooks = new ArrayList<>();
        ChoosingBooks bookChoser = new ChoosingBooks();
        @Override
        public void action() {
            books = bookChoser.choosingBooks();
            //  log.info("Имеющиеся книги у агента " + getAgent().getLocalName() + " - " + books.toString());
            MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
            ACLMessage receive = getAgent().receive(mt);

            if (receive!= null) {

                // log.info("Пришел запрос на покупку книг от " + receive.getSender().getName() + "список необходимых книг" + receive.getContent());
                //log.info(receive.getContent());
                if(books.contains(receive.getContent())) {
                    ACLMessage msg = new ACLMessage(ACLMessage.AGREE);
                    log.info("Книга " + receive.getContent() + " имеется у агента " + getAgent().getLocalName());
                    price = (int) (200 + Math.random() * 199);
                    log.info("Продавец {} предлагает {} рублей за книгу {}", getAgent().getLocalName(), price, receive.getContent());
                    msg.setContent(String.valueOf(price));
                    msg.setProtocol(receive.getContent());
                    List<AID> agents = dFhelper.findAgents(getAgent(),"Buyer");
                    for (AID agent:agents)
                        msg.addReceiver(agent);
                    getAgent().send(msg);
                    books.remove(receive.getContent());
                } else {
                    ACLMessage msg = new ACLMessage(ACLMessage.CANCEL);
                    msg.setContent(String.valueOf(price));
                    msg.setProtocol(receive.getContent());
                    List<AID> agents = dFhelper.findAgents(getAgent(),"Buyer");
                    for (AID agent:agents)
                        msg.addReceiver(agent);
                    getAgent().send(msg);
                }

            }
        }

        @Override
        public boolean done() {

            return false;
        }
}
