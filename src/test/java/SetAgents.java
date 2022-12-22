import Agents.Consumer.Consumer;
import Agents.Distributon.Distributor;
import Agents.Production.Producer;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.core.behaviours.Behaviour;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

import java.util.List;

public class SetAgents {
    private AgentContainer mainContainer;



    public void startJade(List<String> services) {
        ProfileImpl profile = new ProfileImpl();
        profile.setParameter("gui", "true");
        StringBuilder sb = new StringBuilder();

        services.forEach(el -> sb.append(el).append(";"));
        profile.setParameter("services", sb.toString());

        Runtime.instance().setCloseVM(true);
        mainContainer = Runtime.instance().createMainContainer(profile);
    }

    public void createConsumer(String name, Behaviour...behaviours) {
        try {
            AgentController newAgent = mainContainer.createNewAgent(name, Consumer.class.getName(),behaviours);
            newAgent.start();
        } catch (StaleProxyException e) {
            e.printStackTrace();
        }
    }

    public void createConsumer_new(String name, Behaviour...behaviours) {
        try {
            AgentController newAgent = mainContainer.createNewAgent(name, TestConsumer.class.getName() ,behaviours);
            newAgent.start();
        } catch (StaleProxyException e) {
            e.printStackTrace();
        }
    }

    public void createDistributor(String name, Behaviour...behaviours) {
        try {
            AgentController newAgent = mainContainer.createNewAgent(name, Distributor.class.getName(), behaviours);
            newAgent.start();
        } catch (StaleProxyException e) {
            e.printStackTrace();
        }
    }

    public void createProducer(String name, Behaviour...behaviours) {
        try {
            AgentController newAgent = mainContainer.createNewAgent(name, Producer.class.getName(), behaviours);
            newAgent.start();
        } catch (StaleProxyException e) {
            e.printStackTrace();
        }
    }
}
