package Bilet_4;

import jade.core.behaviours.FSMBehaviour;

public class SellerFSMBehaviour extends FSMBehaviour {
    public SellerFSMBehaviour() {


        registerFirstState(new WaitForRequest(),"WaitingForRequest");
        registerLastState(new WaitingConfirmFromABuyer(),"Waiting a answer from a Buyer");


        registerDefaultTransition("WaitingForRequest","Waiting a answer from a Buyer");





    }
}
