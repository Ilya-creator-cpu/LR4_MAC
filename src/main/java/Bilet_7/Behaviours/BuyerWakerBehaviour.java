package Bilet_7.Behaviours;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.WakerBehaviour;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BuyerWakerBehaviour extends WakerBehaviour {
    AID topic;
    public BuyerWakerBehaviour(Agent a, long timeout, AID topic) {

        super(a, timeout);
        this.topic = topic;
        log.info("Buyer woke up");
    }

    @Override
    protected void onWake() {
        getAgent().addBehaviour(new BuyerBehaviour(topic));
    }
}
