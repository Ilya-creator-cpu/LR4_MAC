package Bilet_8.Behaviours;

import Agents.DFhelper;
import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
@Slf4j
public class RecieveInformation extends Behaviour {
    private MessageTemplate requestTemplate;
    private double discriminant;

    private List<AID> agents;
    private boolean fin = false;

    @Override
    public void action() {
        requestTemplate = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
        agents = DFhelper.findAgents(getAgent(), "counter");
        ACLMessage msg = getAgent().receive(requestTemplate);
        if (msg != null) { //если получил сообщение
            log.info("Recieve message: " + msg.getContent() + "\n from " + msg.getSender().getLocalName());
            String[] values = msg.getContent().split(",");
            discriminant = Double.parseDouble(values[1]) * Double.parseDouble(values[1])
                    - 4 * Double.parseDouble(values[0]) * Double.parseDouble(values[2]);
            ACLMessage coeffs = new ACLMessage(ACLMessage.PROPOSE); //сообщение с коэффами
            ACLMessage discriminantInformation = new ACLMessage(ACLMessage.INFORM); //сообщение с дискриминантом
            for (AID agent : agents) {
                if (agent.getLocalName().contains("Root")) {
                    coeffs.addReceiver(agent);
                    discriminantInformation.addReceiver(agent);
                    log.info("Send messages to " + agent.getLocalName()
                            + "\nmessage: " + discriminant);
                }
            }
            //загрузил коэффы и дискриминант, отправил, закончил поведение
            coeffs.setContent(msg.getContent());
            discriminantInformation.setContent(String.valueOf(discriminant));
            getAgent().send(discriminantInformation);
            getAgent().send(coeffs);
            fin = true;
        } else {
            block(); //либо жду сообщение
        }
    }

    @Override
    public boolean done() {
        return fin;
    }
}
