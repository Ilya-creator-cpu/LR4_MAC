package Bilet_5;

import Agents.DFhelper;
import Bilet_5.Behaviours.SellerBehaviour;
import jade.core.Agent;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
@Data
@Slf4j
public class AgentSeller extends Agent {

    DFhelper dFhelper = new DFhelper();

    private List<String> books = new ArrayList<>();

    private List<String> removedBooks = new ArrayList<>();
    ChoosingBooks bookChoser = new ChoosingBooks();

    @Override
    public void setup() {
        books = bookChoser.choosingBooks();
        dFhelper.registerAgent(this, "Seller");
        addBehaviour(new SellerBehaviour());
    }

}
