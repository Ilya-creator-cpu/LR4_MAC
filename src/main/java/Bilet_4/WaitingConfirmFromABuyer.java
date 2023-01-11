package Bilet_4;

import jade.core.Agent;
import jade.core.MessageQueue;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.extern.slf4j.Slf4j;
@Slf4j
public class WaitingConfirmFromABuyer extends Behaviour {




    @Override
    public void action() {
        MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.PROPOSE);
        ACLMessage confirmed = getAgent().receive(mt);
        if (confirmed!=null) {
            log.info("Подтверждение от покупателя получено");

        }
    }

    @Override
    public boolean done() {
        return false;
    }


}
