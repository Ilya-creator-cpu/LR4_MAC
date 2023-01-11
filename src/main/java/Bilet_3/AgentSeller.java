package Bilet_3;

import Agents.DFhelper;
import jade.core.Agent;
import lombok.extern.slf4j.Slf4j;
@Slf4j
public class AgentSeller extends Agent {
    int a = (int) (Math.random() * 3);
    DFhelper dFhelper = new DFhelper();

    @Override
    public void setup() {

        registration();
        log.info("Seller was registered");
        addBehaviour(new SellerBehaviour(a));

    }

    public void registration() {

        dFhelper.registerAgent(this,"Seller");

    }
}
