package Bilet_8.Agents;

import Agents.DFhelper;
import Bilet_8.Behaviours.RecieveAnswers;
import Bilet_8.Behaviours.RecieveInformation;
import Bilet_8.Behaviours.RootBehaviour;
import Bilet_8.Behaviours.SendInformation;
import Bilet_8.ConfigUnmarshaller;
import jade.core.Agent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FunctionAgent extends Agent {
    @Override
    protected void setup() {
        DFhelper.registerAgent(this, "counter");
        log.info(this.getLocalName() + " born and registered!");
        try {
            Thread.sleep(8000); //задержка, чтобы ты успел открыть сниффер
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        switch (this.getLocalName()) {  //взяли имя агента
            case "Informator": //распарсили конфиг с коэффами и добавили 2 поведения: отправить коэффы дальше и ожидать ответа
                Inform agentCfg = ConfigUnmarshaller.getCfg(Inform.class,"src\\main\\resources\\"+this.getLocalName()+".xml");
                log.info("Informator's values:\nA: "+agentCfg.getA() + "\nB: "+agentCfg.getB() + "\nC: "+agentCfg.getC()+"\n");
                addBehaviour(new SendInformation(agentCfg.getA()+","+agentCfg.getB()+","+agentCfg.getC()));
                addBehaviour(new RecieveAnswers());
                break;
            case "Discriminant":
                addBehaviour(new RecieveInformation()); //поведение: получил коэффы, посчитал дискриминант, передал дальше
                break;

            /* Типа 2 в 1 */
            case "Root1":
            case "Root2":
                addBehaviour(new RootBehaviour());  //получил дискриминант, посчитал корень - отправил ответ информатору
                break;
            default:
                log.error("Invalid agent");
        }
    }
}
