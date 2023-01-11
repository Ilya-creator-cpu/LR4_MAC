package Bilet_4;

import Agents.CfgClass;
import Agents.Consumer.SendQuantity;
import Agents.DFhelper;
import Agents.Data;
import jade.core.Agent;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class Buyer extends Agent {
    Data data;

    private int hour = 4;



    private Map<Integer,Integer> hashMap = new HashMap<>();
    CfgClass power = new CfgClass();

    DFhelper dFhelper = new DFhelper();

    @SneakyThrows
    protected void setup(){
        registration();
        addBehaviour(new BuyerFSMBehaviour());

    }

    public void registration(){
        dFhelper.registerAgent(this,"Buyer");

    }

}
