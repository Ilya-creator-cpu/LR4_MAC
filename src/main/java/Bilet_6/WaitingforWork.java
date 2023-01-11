package Bilet_6;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.extern.slf4j.Slf4j;
@Slf4j
public class WaitingforWork extends Behaviour {

    private long V;


    @Override
    public void action() {
        MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
        ACLMessage receive = getAgent().receive(mt);
        if (receive != null) {

            log.info("Сообщение о старте работ получено, объем работ составляет " + receive.getContent());
            V = Long.parseLong(receive.getContent());
            getAgent().addBehaviour(new PBehaviour(getAgent(),10000,System.currentTimeMillis(),V));
            getAgent().removeBehaviour(this);
        }

    }

    @Override
    public boolean done() {
        return false;
    }
}
