package Bilet_8.Behaviours;

import Agents.DFhelper;
import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
@Slf4j
public class RootBehaviour extends Behaviour {
    private MessageTemplate informTemplate;
    private MessageTemplate proposeTemplate;
    private List<AID> agents;
    private String[] content;
    private double discriminant;
    private double x;
    private boolean fin = false;

    @Override
    public void action() {
        //получаю коэффы и дискриминант
        informTemplate = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
        proposeTemplate = MessageTemplate.MatchPerformative(ACLMessage.PROPOSE);
        ACLMessage msgd = getAgent().receive(informTemplate);
        ACLMessage msgv = getAgent().receive(proposeTemplate);

        //нахожу в нашей пати информатора
        agents = DFhelper.findAgents(getAgent(), "counter");
        ACLMessage answer = new ACLMessage(ACLMessage.AGREE);
        answer.addReceiver(new AID("Informator", false));
        //считаю корни и отправляю информатору, завершаю поведение
        if (msgd != null && msgv != null) {
            switch (myAgent.getLocalName()) {
                case "Root1":   //-b+sqrt(D)/(2*a)
                    content = msgv.getContent().split(",");
                    discriminant = Double.parseDouble(msgd.getContent());
                    if (discriminant >= 0) {
                        x = (-Double.parseDouble(content[1]) + Math.sqrt(discriminant)) / (2 * Double.parseDouble(content[0]));
                        answer.setContent(String.valueOf(x));
                        log.info("Send x to Informator: " + x);
                        getAgent().send(answer);
                    } else {
                        log.info("Discriminant less than zero!");
                        answer.setContent("Inform you that discriminant less than zero!");
                        getAgent().send(answer);
                    }
                    break;
                case "Root2"://-b-sqrt(D)/(2*a)
                    content = msgv.getContent().split(",");
                    discriminant = Double.parseDouble(msgd.getContent());
                    if (discriminant > 0) { // мнимые корни по условию не рассматриваются
                        x = (-Double.parseDouble(content[1]) - Math.sqrt(discriminant)) / (2 * Double.parseDouble(content[0]));
                        answer.setContent(String.valueOf(x));
                        log.info("Send x to Informator: " + x);
                        getAgent().send(answer);
                    } else {
                        //да, и такое бывает: либо пишем в консольку, что ничего не делаем, ну либо можем отправить информатору сообщение об этом, в условии конкретно не сказано
                        log.info("Discriminant less than or equal zero!");
                        answer.setContent("Inform you that discriminant less than or equal zero!");
                        getAgent().send(answer);
                    }
                    break;
                default:
                    log.error("Invalid agent");
            }
            fin = true;
        } else {
            block();
        }
    }

    @Override
    public boolean done() {
        return fin;
    }
}
