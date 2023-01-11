package Bilet_3;

import Agents.DFhelper;
import Bilet_3.BuyerBehaviour;
import Bilet_3.WaitForRequest;
import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Ignore extends Behaviour {
    List<AID> agents = new ArrayList<>();
    DFhelper dFhelper = new DFhelper();

    private boolean flag;
    private int result;
    @Override
    public void action() {
        agents = dFhelper.findAgents(getAgent(), "Test");
        MessageTemplate mt  = MessageTemplate.MatchPerformative(ACLMessage.PROPAGATE);
        ACLMessage receive = getAgent().receive(mt);
        if (receive!= null) {
            log.info("A selling is ignored");
            flag = true;

//            ACLMessage msg = new ACLMessage(ACLMessage.SUBSCRIBE);
//            msg.setContent("3");
//            for (AID agent : agents)
//                msg.addReceiver(agent);
//            getAgent().send(msg);
        }


    }

    @Override
    public int onEnd() {
        ACLMessage condition = new ACLMessage(ACLMessage.CFP);
        agents = DFhelper.findAgents(getAgent(),"Test");
        for (AID agent:agents)
            condition.addReceiver(agent);
        condition.setContent("IGNORE");
        getAgent().send(condition);
        return result;
    }

    public boolean done() {
        return flag;
    }
}
