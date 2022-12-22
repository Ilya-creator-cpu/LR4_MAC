package Agents.Production;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.extern.slf4j.Slf4j;
@Slf4j
public class WaitingContract extends Behaviour {

    boolean finish = false;
    @Override
    public void action() {

        // log.info("Ожидаем контракта");
        MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.CONFIRM);
        ACLMessage receive = getAgent().receive(mt);
        if (receive != null) {
           log.info( getAgent().getLocalName() + " получил сообщение " + receive.getContent() + "хочет приобрести"
                    + receive.getSender().getLocalName());

            String[] skoka= receive.getContent().split(",");

            switch (getAgent().getLocalName()) {
                case "TermalStation":
                    if (Double.parseDouble(skoka[0]) <  TermalEnergy.getTermalEnergy()){
                        TermalEnergy.setTermalEnergy(TermalEnergy.getTermalEnergy() - Double.parseDouble(skoka[0]));
                        ACLMessage msg = new ACLMessage(ACLMessage.INFORM_IF);                                     //отправка  первого сообщ в тоПик
                        msg.setContent(skoka[1]);
                        msg.addReceiver(new AID(receive.getSender().getLocalName(), false));
                        getAgent().send(msg);
                        log.info( getAgent().getLocalName() + " ПРОДАЮ ЭНЕРГИЮ ЭТОМУ ЧЕЛУ "+ receive.getSender().getLocalName()
                                +  "вот стока энергии ему нада" +
                                receive.getContent() + " ВОТ СТОКА У МЕНЯ ОСТАЛОСЬ "+ TermalEnergy.getTermalEnergy());


                    } else{
                        ACLMessage msg = new ACLMessage(ACLMessage.INFORM_REF);                                     //отправка  первого сообщ в тоПик
                        msg.setContent("");
                        msg.addReceiver(new AID(receive.getSender().getLocalName(), false));
                        getAgent().send(msg);
                        log.info(getAgent().getLocalName() + "  НЕ ПРОДАЮ ЭНЕРГИЮ ЭТОМУ ЧЕЛУ "+ receive.getSender().getLocalName()
                                +  "вот стока энергии ему нада" +
                                receive.getContent() + " ВОТ СТОКА У МЕНЯ ОСТАЛОСЬ "+ TermalEnergy.getTermalEnergy());
                    }
                    break;
                case "SunStation":
                    if (Double.parseDouble(skoka[0] ) <  SunEnergy.getSunEnergy()){
                        SunEnergy.setSunEnergy(SunEnergy.getSunEnergy() - Double.parseDouble(skoka[0]));
                        ACLMessage msg = new ACLMessage(ACLMessage.INFORM_IF);                                     //отправка  первого сообщ в тоПик
                        msg.setContent(skoka[1]);
                        msg.addReceiver(new AID(receive.getSender().getLocalName(), false));
                        getAgent().send(msg);
                        log.info( getAgent().getLocalName() + " ПРОДАЮ ЭНЕРГИЮ ЭТОМУ ЧЕЛУ "+ receive.getSender().getLocalName()
                                +  "вот стока энергии ему нада" +
                                receive.getContent() + " ВОТ СТОКА У МЕНЯ ОСТАЛОСЬ "+ SunEnergy.getSunEnergy());



                    } else{
                        ACLMessage msg = new ACLMessage(ACLMessage.INFORM_REF);                                     //отправка  первого сообщ в тоПик
                        msg.setContent("");
                        msg.addReceiver(new AID(receive.getSender().getLocalName(), false));
                        getAgent().send(msg);
                        log.info(getAgent().getLocalName() + "  НЕ ПРОДАЮ ЭНЕРГИЮ ЭТОМУ ЧЕЛУ "+ receive.getSender().getLocalName()
                                +  "вот стока энергии ему нада" +
                                receive.getContent() + " ВОТ СТОКА У МЕНЯ ОСТАЛОСЬ "+ SunEnergy.getSunEnergy());

                    }
                    break;
                case "WindStation":
                    if (Double.parseDouble(skoka[0] ) <  WindEnergy.getWindEnergy()){
                        WindEnergy.setWindEnergy(WindEnergy.getWindEnergy() - Double.parseDouble(skoka[0]));
                        ACLMessage msg = new ACLMessage(ACLMessage.INFORM_IF);                                     //отправка  первого сообщ в тоПик
                        msg.setContent(skoka[1]);
                        msg.addReceiver(new AID(receive.getSender().getLocalName(), false));
                        getAgent().send(msg);
                        log.info( getAgent().getLocalName() + " ПРОДАЮ ЭНЕРГИЮ ЭТОМУ ЧЕЛУ "+ receive.getSender().getLocalName()
                                +  "вот стока энергии ему нада" +
                                receive.getContent() + " ВОТ СТОКА У МЕНЯ ОСТАЛОСЬ "+ WindEnergy.getWindEnergy());



                    } else{
                        ACLMessage msg = new ACLMessage(ACLMessage.INFORM_REF);                                     //отправка  первого сообщ в тоПик
                        msg.setContent("");
                        msg.addReceiver(new AID(receive.getSender().getLocalName(), false));
                        getAgent().send(msg);
                        log.info(getAgent().getLocalName() + "  НЕ ПРОДАЮ ЭНЕРГИЮ ЭТОМУ ЧЕЛУ "+ receive.getSender().getLocalName()
                                +  "вот стока энергии ему нада" +
                                receive.getContent() + " ВОТ СТОКА У МЕНЯ ОСТАЛОСЬ "+ WindEnergy.getWindEnergy());
                    }
                    break;


            }

        }
    }








    @Override
    public boolean done() {

        return finish;
    }
}
