package Bilet_7.Agents;

import Agents.TopicHelper;
import Bilet_7.Behaviours.SellerAuctionBehaviour;
import jade.core.AID;
import jade.core.Agent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AgentSeller extends Agent {

    AID topic;

    @Override
    protected void setup() {
       topic = TopicHelper.createTopic(this,"Auction");
        addBehaviour(new SellerAuctionBehaviour(topic));

    }
}
