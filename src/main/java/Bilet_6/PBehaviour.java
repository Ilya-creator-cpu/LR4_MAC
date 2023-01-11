package Bilet_6;

import Agents.DFhelper;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import lombok.extern.slf4j.Slf4j;
import java.lang.Math;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class PBehaviour extends TickerBehaviour {

    DFhelper dFhelper = new DFhelper();
    List<AID> agents = new ArrayList<>();

    long starttime;
    long work;
    long V;

    long percent;
    public PBehaviour(Agent a, long period, long starttime, long V) {
        super(a, period);
        this.starttime = starttime;
        this.V = V;
    }

    @Override
    protected void onTick() {
        work = (System.currentTimeMillis() - starttime)/10000*10;

        percent = work;
        agents = dFhelper.findAgents(getAgent(),"Z");
        ACLMessage report = new ACLMessage(ACLMessage.CONFIRM);
        report.setContent(Long.toString(percent) + " %");
        for (AID agent : agents)
            report.addReceiver(agent);
        getAgent().send(report);
        if (work >= V) {
            getAgent().addBehaviour(new WaitForConfirm());
            getAgent().removeBehaviour(this);
        }

        // log.info("Процент выполненной работы " + Long.toString(work));

    }
}
