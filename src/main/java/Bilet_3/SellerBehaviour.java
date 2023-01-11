package Bilet_3;

import Agents.DFhelper;
import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.extern.slf4j.Slf4j;
import java.util.ArrayList;
import java.util.List;
@Slf4j
public class SellerBehaviour extends Behaviour {
    DFhelper dFhelper = new DFhelper();

    List<AID> agents = new ArrayList<>();

    ACLMessage msg = new ACLMessage(ACLMessage.PROPAGATE);

    private int a;

    public SellerBehaviour(int a) {
        this.a = a;

    }

    @Override
    public void action() {
        MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
        ACLMessage receive = getAgent().receive(mt);
        if (receive != null) {
            log.info("Состояние {}", a);
            agents = dFhelper.findAgents(getAgent(), "Buyer");

            log.info("Seller was received a message");

            switch (a) {
                case 0:
                    msg = new ACLMessage(ACLMessage.AGREE);
                    msg.setContent("AGREE");
                    log.info("Даем согласие");
                    break;
                case 1:
                    msg = new ACLMessage(ACLMessage.PROPAGATE);//игнорирование
                    msg.setContent("IGNOR");
                    log.info("Игнорируем");
                    break;
                case 2:
                    msg = new ACLMessage(ACLMessage.CANCEL); //отказ
                    msg.setContent("DENY");
                    log.info("Отказываем");
                    break;
            }
            for (AID agent : agents)
                msg.addReceiver(agent);
            getAgent().send(msg);

        }
    }

    @Override
    public boolean done() {
        return false;
    }
}
