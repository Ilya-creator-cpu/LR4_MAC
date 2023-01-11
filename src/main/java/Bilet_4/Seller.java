package Bilet_4;

import Agents.DFhelper;
import jade.core.Agent;
import lombok.extern.slf4j.Slf4j;
@Slf4j
public class Seller extends Agent {

    DFhelper dFhelper = new DFhelper();

    @Override
    public void setup() {
        registration();
        log.info(this.getAID().toString());
        addBehaviour(new SellerFSMBehaviour());


    }

    public void registration() {

        dFhelper.registerAgent(this,"Seller");

    }
}
