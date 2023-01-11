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
public class Agree extends Behaviour {
    ACLMessage receive;
    boolean flag;

    List<AID> agents;

    int result;

    public ACLMessage getReceive() {
        return receive;
    }

    @Override
    public void action() {

        MessageTemplate mt  = MessageTemplate.MatchPerformative(ACLMessage.AGREE);
         receive = getAgent().receive(mt);
        if (receive!= null) {
            result = 1;

            log.info("A selling is confirm");
            flag = true;

        }


    }

    @Override
    public int onEnd() {

        ACLMessage condition = new ACLMessage(ACLMessage.CFP);
        agents = DFhelper.findAgents(getAgent(),"Test");
        for (AID agent:agents)
            condition.addReceiver(agent);
        condition.setContent("AGREE");
        getAgent().send(condition);
        return result;
    }

    @Override
    public boolean done() {
        return flag;
    }
}
