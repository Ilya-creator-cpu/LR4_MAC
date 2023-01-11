package Bilet_4;

import jade.core.behaviours.FSMBehaviour;

public class BuyerFSMBehaviour extends FSMBehaviour {

    public BuyerFSMBehaviour() {

        registerFirstState(new SendRequest(getAgent(),10000),"Send a Request");
        registerLastState(new WaitingForPrice(),"Wait for a price");



        registerDefaultTransition("Send a Request", "Wait for a price");


    }
}
