import Agents.Consumer.OneShotBehaviourr;
import Agents.Consumer.Report;
import Agents.Distributon.TakeQuest;
import Agents.Production.GeneratePower;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class EnergyTest extends SetAgents {

    @Test
    public void test1() throws InterruptedException {
        double maxPrice = 170.0;
        List<String> services = new ArrayList<>();
        services.add("jade.core.messaging.TopicManagementService");
        services.add("jade.core.event.NotificationService");
        startJade(services);


        TakeQuest waiting2 = new TakeQuest();
        createDistributor("TermalStationDistributor", waiting2);

        Thread.sleep(11);


        GeneratePower waiting3 = new GeneratePower();
        createProducer("TermalStation", waiting3);

        Thread.sleep(11);

        OneShotBehaviourr waiting1 = new OneShotBehaviourr("Agent1", "2", "2", "Agent4");
        Report waiting11 = new Report(maxPrice);
        createConsumer_new("Agent1", waiting1, waiting11);

        Thread.sleep(25000);

        Assertions.assertEquals(0, waiting11.onEnd());


//        Assertions.assertEquals(0, waiting11.onEnd());
    }

    @Test
    public void test2() throws InterruptedException {
        double maxPrice = 170.0;
        List<String> services = new ArrayList<>();
        services.add("jade.core.messaging.TopicManagementService");
        services.add("jade.core.event.NotificationService");
        startJade(services);


        TakeQuest waiting2 = new TakeQuest();
        createDistributor("Agent4", waiting2);

        Thread.sleep(1000);


        GeneratePower waiting3 = new GeneratePower();
        createProducer("TermalStation", waiting3);
        createProducer("SunStation", waiting3);

        Thread.sleep(5000);

        OneShotBehaviourr waiting1 = new OneShotBehaviourr("Agent1", "2", "2", "Agent4");
        Report waiting11 = new Report(maxPrice);
        createConsumer_new("Agent1", waiting1, waiting11);

        Thread.sleep(25000);

        Assertions.assertEquals(1, waiting11.onEnd());


    }

    @Test
    public void test3() throws InterruptedException {
        double maxPrice = 100.0;
        List<String> services = new ArrayList<>();
        services.add("jade.core.messaging.TopicManagementService");
        services.add("jade.core.event.NotificationService");
        startJade(services);


        TakeQuest waiting2 = new TakeQuest();
        createDistributor("Distributor", waiting2);

        Thread.sleep(11);


        GeneratePower waiting3 = new GeneratePower();
        createProducer("SunStation", waiting3);
        createProducer("WindStation", waiting3);

        Thread.sleep(11);

        OneShotBehaviourr waiting1 = new OneShotBehaviourr("Agent1", "12", "2", "Agent4");
        Report report = new Report(maxPrice);
        createConsumer_new("Agent1", waiting1, report);

        Thread.sleep(25000);

        Assertions.assertEquals(2, report.onEnd());

    }
}
