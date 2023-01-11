package Bilet_4;

import Agents.DFhelper;
import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.extern.slf4j.Slf4j;
import java.lang.Math;
import java.util.List;

@Slf4j
public class WaitForRequest extends Behaviour {

    DFhelper dFhelper = new DFhelper();
    boolean flag;
    List<AID> agents;

    @Override
    public void action() {

        MessageTemplate mt  = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
        ACLMessage receive = getAgent().receive(mt);

        if (receive!= null) {

            agents = DFhelper.findAgents(getAgent(),"Buyer");

            log.info("Agent " + getAgent().getLocalName() + " получил запрос на покупку ЭЭ");

            ACLMessage request = new ACLMessage(ACLMessage.CONFIRM);
            int a = (int) (Math.random() * 65);
            request.setContent(Integer.toString(a));
            for (AID agent:agents)
                request.addReceiver(agent);
            getAgent().send(request);
            flag = true;
        }


    }

    @Override
    public boolean done() {
        return flag;
    }
}
