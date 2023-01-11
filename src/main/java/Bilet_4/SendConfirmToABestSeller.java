package Bilet_4;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.WakerBehaviour;
import jade.lang.acl.ACLMessage;
import lombok.extern.slf4j.Slf4j;
import java.util.Date;
@Slf4j
public class SendConfirmToABestSeller extends WakerBehaviour {

    AID BestSeller;

    public SendConfirmToABestSeller(Agent a, long wakeupDate, AID bestSeller) {
        super(a, wakeupDate);
        this.BestSeller = bestSeller;
    }



    @Override
    public void onWake() {
        ACLMessage bestAgent = new ACLMessage(ACLMessage.PROPOSE);
        bestAgent.addReceiver(BestSeller);
        getAgent().send(bestAgent);
        log.info("Подвтерждение было отправлено агенту" + BestSeller.toString());

    }
}
