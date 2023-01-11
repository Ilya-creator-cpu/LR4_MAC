package Bilet_4;

import Agents.DFhelper;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.WakerBehaviour;
import jade.lang.acl.ACLMessage;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j

public class SendRequest extends WakerBehaviour {
    DFhelper dFhelper = new DFhelper();

    AID agent;



    List<AID> agents = new ArrayList<>();

    public SendRequest(Agent a, long timeout) {
        super(a, timeout);
    }


    @Override
    @SneakyThrows
    public void onWake() {

        agents = dFhelper.findAgents(getAgent(),"Seller");

        ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
        for (AID agent : agents)
            msg.addReceiver(agent);
        getAgent().send(msg);


    }
}
