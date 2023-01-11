package Bilet_6;

import Agents.DFhelper;
import jade.core.Agent;

public class AgentP extends Agent {

    DFhelper dFhelper = new DFhelper();


    @Override
    public void setup() {

        registration();
        addBehaviour(new WaitingforWork());


    }




    public void registration() {
        dFhelper.registerAgent(this,"P");
    }





}
