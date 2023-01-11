package Bilet_3;

import jade.core.Agent;
import jade.core.behaviours.ParallelBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WaitForRequest extends ParallelBehaviour {
    private int result;

    public WaitForRequest(Agent a, int whenAny) {
        super(a, ParallelBehaviour.WHEN_ANY);
        addSubBehaviour(new Agree());
        addSubBehaviour(new Deny());
        addSubBehaviour(new Ignore());
    }

    @Override
    public int onEnd() {
        ACLMessage condition = getAgent().receive(MessageTemplate.MatchPerformative(ACLMessage.CFP));
        if (condition != null) {
            if (condition.getContent().equals("AGREE"))
                result = 1;
            if (condition.getContent().equals("DENY"))
                result = 2;
            if (condition.getContent().equals("IGNORE"))
                result = 3;
  //          log.info(condition.getContent() + "конечное состояние");
        }

        return result;
    }
}
