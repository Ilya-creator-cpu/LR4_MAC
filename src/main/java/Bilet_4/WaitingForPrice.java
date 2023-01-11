package Bilet_4;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
public class WaitingForPrice extends Behaviour {



    List<Integer> prices = new ArrayList<>();
    List<String> agents = new ArrayList<>();

    HashMap<Integer,AID> agentPrices = new HashMap<>();



    String bestagent;

    AID agent;

    int bestprice;
    boolean flag;

    int count;

    @Override
    public void action() {
        MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.CONFIRM);
        ACLMessage receive = getAgent().receive(mt);
        if (receive!=null) {
            count++;
            prices.add(Integer.parseInt(receive.getContent()));
            agentPrices.put(Integer.parseInt(receive.getContent()),receive.getSender());
            agents.add(receive.getSender().getLocalName());
            log.info("Цена " + receive.getSender().getLocalName() + "составляет " + receive.getContent());

        }
        if (count == 2)
            flag = true;

    }

    @Override
    public int onEnd() {
        if (prices.get(0) > prices.get(1)) {
            bestprice = prices.get(1);
        log.info("У продавца " + agents.get(1) + " лучшая цена");
        bestagent = agents.get(1);
        }
        else if (prices.get(1) > prices.get(0)) {
            bestprice = prices.get(0);
            log.info("У продавца " + agents.get(0) + " лучшая цена");
            bestagent = agents.get(0);
        }
        log.info("Лучшая цена составляет " + bestprice);
        agent = agentPrices.get(bestprice);
        getAgent().addBehaviour(new SendConfirmToABestSeller(getAgent(),1000,agent));
        return bestprice;
    }

    @Override
    public boolean done() {
        return flag;
    }
}
