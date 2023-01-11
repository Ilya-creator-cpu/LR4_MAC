package Bilet_8.Behaviours;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RecieveAnswers extends Behaviour {
    @Override
    public void action() {
        try {
            Thread.sleep(5000); //подождем пока все оклемаются
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        MessageTemplate agreeTemplate = MessageTemplate.MatchPerformative(ACLMessage.AGREE);
        ACLMessage msg = getAgent().receive(agreeTemplate);

        if (msg != null) {
            log.info("Answer from " + msg.getSender().getLocalName() + ": " + msg.getContent());
        } else {
            block();
        }
    }

    @Override
    public boolean done() {
        return false;
    }
}
