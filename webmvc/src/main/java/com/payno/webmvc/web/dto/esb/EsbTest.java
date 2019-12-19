package com.payno.webmvc.web.dto.esb;

import com.google.common.base.Charsets;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;

/**
 * @author payno
 * @date 2019/12/19 10:18
 * @description
 */
public class EsbTest {
    @Test
    public void m() throws Exception{
        JAXBContext jaxbContext=JAXBContext.newInstance("com.payno.webmvc.web.dto.esb");
        Marshaller marshaller=jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_ENCODING, Charsets.UTF_8.name());
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,false);
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT,true);
        System.out.print("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        marshaller.marshal(
                EsbXo.of(
                        EsbHeadXo.template(),
                        EsbBodyXo.body(GjjCenterEsbRo.of("gz"))
                ),System.out);
    }

    @Test
    public void um() throws Exception{
        String source="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<Service><Head><serviceId>Nz001</serviceId><serviceName>Nz_Risk</serviceName></Head><Body><request><cityNm>gz</cityNm></request></Body></Service>";
        JAXBContext jaxbContext=JAXBContext.newInstance("com.payno.webmvc.web.dto.esb");
        Unmarshaller unmarshaller=jaxbContext.createUnmarshaller();
        StreamSource streamSource=new StreamSource();
        streamSource.setInputStream(new ByteArrayInputStream(source.getBytes()));
        EsbXo esbXo=unmarshaller.unmarshal(streamSource,EsbXo.class).getValue();
        System.out.println(esbXo);
    }
}
