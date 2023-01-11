package Bilet_3;

import Agents.DFhelper;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.WakerBehaviour;
import jade.lang.acl.ACLMessage;
import lombok.extern.slf4j.Slf4j;
import java.util.Date;
import java.util.List;

@Slf4j
public class BuyerBehaviour extends WakerBehaviour {

    private List<AID> agents;

    DFhelper dFhelper = new DFhelper();

    public BuyerBehaviour(Agent a, long wakeupDate) {
        super(a, wakeupDate);
    }
    @Override
    protected void onWake() {
        log.info("Seller wants to buy");
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        String content = "Wants to buy";
        msg.setContent(content);
        msg.setProtocol(getAgent().getLocalName());
        agents = dFhelper.findAgents(getAgent(),"Seller");
        for(AID agent:agents)
            msg.addReceiver(agent);
        getAgent().send(msg);
        log.info("Запрос о покупке отправлен");
    }

}
