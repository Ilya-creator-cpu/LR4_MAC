package Bilet_10.Agents;

import Agents.TopicHelper;
import Bilet_10.Behaviours.BuyerAuctionBehaviour;
import Bilet_7.Behaviours.SellerAuctionBehaviour;
import jade.core.AID;
import jade.core.Agent;

public class AgentBuyer extends Agent {
    AID topic;

    @Override
    protected void setup() {
        topic = TopicHelper.createTopic(this,"Auction");
        addBehaviour(new BuyerAuctionBehaviour(topic));

    }
}
