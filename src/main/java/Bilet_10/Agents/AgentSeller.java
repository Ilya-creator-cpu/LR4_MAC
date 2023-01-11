package Bilet_10.Agents;

import Agents.TopicHelper;
import Bilet_10.Behaviours.SellerWakerBehaviour;
import Bilet_7.Behaviours.BuyerWakerBehaviour;
import jade.core.AID;
import jade.core.Agent;

public class AgentSeller extends Agent {
    AID topic;

    @Override
    protected void setup() {
        topic = TopicHelper.createTopic(this,"Auction");
        addBehaviour(new SellerWakerBehaviour(this,500,topic));
    }
}
