package Bilet_6;

import Agents.DFhelper;
import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class WaitingReport extends Behaviour {
    DFhelper dFhelper = new DFhelper();
    List<AID> agents = new ArrayList<>();

    @Override
    public void action() {
        agents = dFhelper.findAgents(getAgent(),"P");
        MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.CONFIRM);
        ACLMessage receive = getAgent().receive(mt);

        if (receive != null) {
            log.info("Отчет о проделанной работе получен " + receive.getContent());
            String[] work = receive.getContent().split(" ");
            if (Long.parseLong(work[0]) >= 100) {
                log.info("Работа выполнена");
                ACLMessage endOfTheWork = new ACLMessage(ACLMessage.AGREE);
                for (AID agent : agents)
                    endOfTheWork.addReceiver(agent);
                getAgent().send(endOfTheWork);


            }


        }


    }

    @Override
    public boolean done() {
        return false;
    }
}
