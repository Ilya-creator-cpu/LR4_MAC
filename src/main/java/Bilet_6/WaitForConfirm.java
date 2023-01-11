package Bilet_6;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.extern.slf4j.Slf4j;
@Slf4j
public class WaitForConfirm extends Behaviour {

    @Override
    public void action() {
        MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.AGREE);
        ACLMessage receive = getAgent().receive(mt);
        if (receive != null) {
            log.info("Пришло подтверждение окончания работ от заказчика");

        }
    }

    @Override
    public boolean done() {
        return false;
    }
}
