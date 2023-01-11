package Bilet_3;

import Agents.DFhelper;
import jade.core.Agent;
import lombok.extern.slf4j.Slf4j;
@Slf4j
public class AgentBuyer extends Agent {
    DFhelper dFhelper = new DFhelper();

    @Override
    public void setup() {
        registration();
        addBehaviour(new BuyerFSMBehaviour());
    }

    public void registration() {
        dFhelper.registerAgent(this,"Buyer");
    }
}
