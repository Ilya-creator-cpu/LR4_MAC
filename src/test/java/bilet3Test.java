import Bilet_3.*;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class bilet3Test extends SetAgents {




    @Test
    @SneakyThrows
    public void agreeTest () {
        List<String> services = new ArrayList<>();
        services.add("jade.core.event.NotificationService");
        startJade(services);

        SellerBehaviour sellerAgree = new SellerBehaviour(0);

        BuyerFSMBehaviour buyerAgree = new BuyerFSMBehaviour();

        createAgreeAgent("AgentSeller", sellerAgree);

        createBuyerAgent("AgentBuyer", buyerAgree);

        TestBehaviour testBehaviour = new TestBehaviour();

        createTestAgent("AgentTest", testBehaviour);

        Thread.sleep(2000);

        log.info(String.valueOf(testBehaviour.onEnd()));

       Assertions.assertEquals(1,testBehaviour.onEnd());

    }
    @SneakyThrows
    @Test
    public void denyTest() {
        List<String> services = new ArrayList<>();
        services.add("jade.core.event.NotificationService");
        startJade(services);
        SellerBehaviour testDeny = new SellerBehaviour(0);

        BuyerFSMBehaviour deny = new BuyerFSMBehaviour();

        createDenyAgent("AgentSeller", testDeny);

        createBuyerAgent("AgentBuyer", deny);

        TestBehaviour testBehaviour = new TestBehaviour();

        createTestAgent("AgentTest", testBehaviour);

        Thread.sleep(2000);

        log.info(String.valueOf(testBehaviour.onEnd()));

        Assertions.assertEquals(2,testBehaviour.onEnd());
    }

    @SneakyThrows
    @Test
    public void ignoreTest() {
        List<String> services = new ArrayList<>();
        services.add("jade.core.event.NotificationService");
        startJade(services);
        SellerBehaviour testIgnore = new SellerBehaviour(0);

        BuyerFSMBehaviour ignore = new BuyerFSMBehaviour();

        createIgnoreAgent("AgentSeller", testIgnore);

        createBuyerAgent("AgentBuyer", ignore);

        TestBehaviour testBehaviour = new TestBehaviour();

        createTestAgent("AgentTest", testBehaviour);

        Thread.sleep(2000);

        log.info(String.valueOf(testBehaviour.onEnd()));

        Assertions.assertEquals(3,testBehaviour.onEnd());

    }


}
