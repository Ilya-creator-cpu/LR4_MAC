package Bilet_3;

import Agents.DFhelper;
import Bilet_3.TestBehaviour;
import jade.core.Agent;
import lombok.extern.slf4j.Slf4j;
@Slf4j
public class TestAgent extends Agent {

    DFhelper dFhelper = new DFhelper();

    @Override
    public void setup() {
        registration();
        addBehaviour(new TestBehaviour());

    }

    public void registration() {

        dFhelper.registerAgent(this,"Test");

    }
}
