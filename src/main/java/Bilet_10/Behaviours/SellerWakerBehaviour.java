package Bilet_10.Behaviours;

import Bilet_7.Behaviours.BuyerBehaviour;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.WakerBehaviour;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SellerWakerBehaviour extends WakerBehaviour {
    AID topic;
    public SellerWakerBehaviour(Agent a, long timeout, AID topic) {

        super(a, timeout);
        this.topic = topic;
        log.info("Buyer woke up");
    }

    @Override
    protected void onWake() {
        getAgent().addBehaviour(new SellerBehaviour(topic));
    }
}
