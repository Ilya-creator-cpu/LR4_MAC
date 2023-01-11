package Bilet_6;

import Agents.DFhelper;
import jade.core.Agent;

public class AgentZ extends Agent {

    DFhelper dFhelper = new DFhelper();

    @Override
    public void setup() {
        registration();
        addBehaviour(new ZBehaviour(this,10000));
        addBehaviour(new WaitingReport());
    }

    public void registration() {
        dFhelper.registerAgent(this,"Z");

    }


}
