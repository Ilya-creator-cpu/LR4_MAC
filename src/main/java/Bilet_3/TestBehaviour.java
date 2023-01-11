package Bilet_3;

import Agents.Consumer.OneShotBehaviourr;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.WakerBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
public class TestBehaviour extends Behaviour {

boolean flag;
private static int result;

    @Override
    public void action() {
      //  log.info("Переходим в тестовое поведение");
        ACLMessage condition = myAgent.receive(MessageTemplate.MatchPerformative(ACLMessage.CFP));
        if (condition != null) {
            if (condition.getContent().equals("AGREE"))
                result = 1;
            if (condition.getContent().equals("DENY"))
                result = 2;
            if (condition.getContent().equals("IGNORE"))
                result = 3;
            log.info(condition.getContent() + "конечное состояние");
            log.info(String.valueOf(result) + " конечный результат");
            flag = true;
        }
    }
    @Override
    public int onEnd() {
         log.info(String.valueOf(result) + "результат");
        return result;
    }

    @Override
    public boolean done() {
        return flag;
    }
}
