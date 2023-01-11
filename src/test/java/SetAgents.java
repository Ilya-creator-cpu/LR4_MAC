import Bilet_3.*;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.core.behaviours.Behaviour;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import lombok.SneakyThrows;

import java.util.List;

public class SetAgents {
    protected AgentContainer mainContainer;

    public void startJade(List<String> services) {
        ProfileImpl profile = new ProfileImpl();
     //   profile.setParameter("gui", "true");
        StringBuilder sb = new StringBuilder();

        services.forEach(el -> sb.append(el).append(";"));
        profile.setParameter("services", sb.toString());

        Runtime.instance().setCloseVM(true);
        mainContainer = Runtime.instance().createMainContainer(profile);
    }

    @SneakyThrows
    public void createAgreeAgent(String name, Behaviour...behaviours) {
        AgentController newAgent =  mainContainer.createNewAgent(name, AgentAgree.class.getName(), behaviours);
        newAgent.start();
    }
    @SneakyThrows
    public void createDenyAgent(String name, Behaviour...behaviours) {
        AgentController newAgent =  mainContainer.createNewAgent(name, AgentDeny.class.getName(), behaviours);
        newAgent.start();
    }
    @SneakyThrows
    public void createIgnoreAgent(String name, Behaviour...behaviours) {
        AgentController newAgent =  mainContainer.createNewAgent(name, AgentIgnore.class.getName(), behaviours);
        newAgent.start();
    }
    @SneakyThrows
    public void createBuyerAgent(String name,Behaviour...behaviours) {
        AgentController newAgent = mainContainer.createNewAgent(name, AgentBuyer.class.getName(), behaviours);
        newAgent.start();

    }

    @SneakyThrows
    public void createTestAgent(String name, Behaviour...behaviours) {
        AgentController newAgent = mainContainer.createNewAgent(name, TestAgent.class.getName(), behaviours);
        newAgent.start();
    }
}
