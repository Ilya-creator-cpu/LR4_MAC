package Bilet_7.Agents;

import Agents.TopicHelper;
import Bilet_7.Behaviours.BuyerWakerBehaviour;
import jade.core.AID;
import jade.core.Agent;

public class AgentBuyer extends Agent {

    AID topic;

    @Override
    protected void setup() {
        topic = TopicHelper.createTopic(this,"Auction");
        addBehaviour(new BuyerWakerBehaviour(this,500,topic));
    }
}
