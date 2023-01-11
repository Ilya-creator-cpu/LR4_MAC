package Bilet_6;

import Agents.DFhelper;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.core.behaviours.WakerBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ZBehaviour extends WakerBehaviour {

    private long V = 100;

    DFhelper dFhelper = new DFhelper();

    List<AID> agents = new ArrayList<>();


    public ZBehaviour(Agent a, long wakeupDate) {
        super(a, wakeupDate);
    }

    @Override
    public void onWake() {
        agents = dFhelper.findAgents(getAgent(),"P");
        ACLMessage start = new ACLMessage(ACLMessage.INFORM);
        start.setContent(Long.toString(V));
        for (AID agent : agents)
            start.addReceiver(agent);
        getAgent().send(start);
    }
}
