package Bilet_2;

import Agents.DFhelper;
import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Deny extends Behaviour {
 boolean flag;

    @Override
    public void action() {
        MessageTemplate mt  = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
        ACLMessage receive = getAgent().receive(mt);
        if (receive!= null) {

            log.info("Seller have deny");
        }

    }

    @Override
    public boolean done() {
        return flag;
    }


}
