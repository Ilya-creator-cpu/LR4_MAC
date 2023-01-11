package Bilet_10.Behaviours;

import Agents.DFhelper;
import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
@Slf4j
public class SellerBehaviour extends Behaviour {
    List<AID> agents;
    AID topic;

    DFhelper dFhelper = new DFhelper();


    public SellerBehaviour(AID topic){
        this.topic = topic;
    }
    @SneakyThrows
    @Override
    public void onStart() {

        agents = dFhelper.findAgents(getAgent(),"Buyer");

    }

    @Override
    public void action() {
        ACLMessage deny = getAgent().receive(MessageTemplate.MatchPerformative(ACLMessage.DISCONFIRM));
        if (deny != null){
            agents.remove(deny.getSender());
        } else {
            block();
        }
        if (agents.size() == 1){
            log.info("Auction winner: "+agents.get(0).getLocalName());
            ACLMessage agree =  new ACLMessage(ACLMessage.AGREE);
            agree.setContent("0");
            agree.addReceiver(topic);
            getAgent().send(agree);
        }
    }

    @Override
    public boolean done() {
        return agents.size() == 1;
    }
}
