package Agents.Production;

import jade.core.Agent;
import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.WakerBehaviour;
import lombok.extern.slf4j.Slf4j;
@Slf4j
public class WaitForContract extends ParallelBehaviour {

    public WaitForContract(Agent a) {
        super(a, WHEN_ANY);

        addSubBehaviour(new WaitingContract());
        addSubBehaviour(new WakerBehaviour(getAgent(),10000) {
            @Override
            protected void onWake() {
                log.info("Завершаем процесс");
            }
        });

    }
}
