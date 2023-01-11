package Bilet_8;

import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.FileReader;
@Slf4j
public class ConfigUnmarshaller {

//
//	public static <T> void marshalAny (T information, String outPutFileName) {
//		JAXBContext jaxbContext;
//		try {
//			jaxbContext = JAXBContext.newInstance(information.getClass());
//			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
//			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//			jaxbMarshaller.marshal(information, new File(outPutFileName));
//		} catch (JAXBException e) {
//			e.printStackTrace();
//		}
//	}

        public static <T> T getCfg(Class<T> classType, String filepath) {
            try {
                JAXBContext context = JAXBContext.newInstance(classType);
                T config = (T) context.createUnmarshaller().unmarshal(new FileReader(filepath));
                return config;
            } catch (JAXBException e) {
                log.error("Invalid XML file. Reason: ", e.getMessage());
                throw new RuntimeException(e);
            } catch (FileNotFoundException e) {
                log.error("Invalid path. Reason: ", e.getMessage());
                throw new RuntimeException(e);
            }
        }
}
