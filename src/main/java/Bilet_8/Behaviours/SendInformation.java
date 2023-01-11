package Bilet_8.Behaviours;



import Agents.DFhelper;
import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
@Slf4j
public class SendInformation extends OneShotBehaviour {
    private List<AID> agents;

    private String content;

    public SendInformation(String content) {

        this.content = content;
    }

    @Override
    public void action() {
        agents = DFhelper.findAgents(getAgent(), "counter");  //посмотрел кто в сети
        ACLMessage request = new ACLMessage(ACLMessage.REQUEST);
        request.addReceiver(new AID("Discriminant", false));
        request.setContent(content);
        getAgent().send(request);
        log.info("Send to Discriminant message: " + content); // и отправил ему коэффициенты в виде строки, закончил поведение

    }
}
