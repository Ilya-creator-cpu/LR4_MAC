package Bilet_5.Behaviours;

import jade.core.behaviours.FSMBehaviour;
import lombok.extern.slf4j.Slf4j;


    @Slf4j
    public class BuyerFSM extends FSMBehaviour {
        public BuyerFSM(String book, String secondBook) {

            registerFirstState(new SendRequest(getAgent(),10000,book),"Request");
            registerState(new WaitingAnswerFromSellers(),"WaitingAnswer");
            registerState(new SendRequest(getAgent(),10000, secondBook),"SecondRequest");
            registerLastState( new WaitingAnswerFromSellers(),"SecondAnswer");



            registerDefaultTransition("Request","WaitingAnswer");
            registerDefaultTransition("WaitingAnswer","SecondRequest");
            registerDefaultTransition("SecondRequest","SecondAnswer");

        }
}
