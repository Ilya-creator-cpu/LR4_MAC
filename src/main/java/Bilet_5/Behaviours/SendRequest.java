package Bilet_5.Behaviours;

import Agents.DFhelper;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.WakerBehaviour;
import jade.lang.acl.ACLMessage;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
@Slf4j
public class SendRequest extends WakerBehaviour {

    public List<AID> agents;

    DFhelper dFhelper = new DFhelper();

    private String book;


    public SendRequest(Agent a, long wakeupDate, String book) {
        super(a, wakeupDate);
        this.book = book;

    }

    @Override
    public void onWake() {
        agents = dFhelper.findAgents(getAgent(),"Seller");
        ACLMessage request = new ACLMessage(ACLMessage.REQUEST);
        for (AID agent:agents)
            request.addReceiver(agent);
        request.setContent(book);
        getAgent().send(request);
       // log.info("Запрос о покупке книги " + book + " был отправлен");

    }
}
