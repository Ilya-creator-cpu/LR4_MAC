package Bilet_3;

import jade.core.behaviours.FSMBehaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BuyerFSMBehaviour extends FSMBehaviour {

    private int result;
        public BuyerFSMBehaviour() {

            registerFirstState(new BuyerBehaviour(this.getAgent(),1000),"FirstQuest");
            registerLastState(new WaitForRequest(this.getAgent(), ParallelBehaviour.WHEN_ANY),"Waiting for answers");








            registerDefaultTransition("FirstQuest","Waiting for answers");

        }

    @Override
    public int onEnd() {


        return result;
        }

}
