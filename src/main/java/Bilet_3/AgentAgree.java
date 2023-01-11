package Bilet_3;

import Agents.DFhelper;
import jade.core.Agent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AgentAgree extends Agent {

    DFhelper dFhelper = new DFhelper();

    @Override
    public void setup() {
        int a = 0;
        registration();
        log.info("Seller was registered");
        addBehaviour(new SellerBehaviour(a));

    }

    public void registration() {

        dFhelper.registerAgent(this,"Seller");

    }
}
