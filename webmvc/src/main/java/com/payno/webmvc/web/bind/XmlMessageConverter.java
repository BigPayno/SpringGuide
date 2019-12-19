package com.payno.webmvc.web.bind;

import com.google.common.base.Charsets;
import com.payno.webmvc.web.dto.esb.EsbXo;
import org.springframework.http.HttpHeaders;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.xml.AbstractXmlHttpMessageConverter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * @author payno
 * @date 2019/12/19 09:17
 * @description
 */
public class XmlMessageConverter extends AbstractXmlHttpMessageConverter<EsbXo> {
    private Marshaller marshaller;
    private Unmarshaller unmarshaller;
    public XmlMessageConverter() {
        super();
        try{
            JAXBContext esbContext=JAXBContext.newInstance("com.payno.webmvc.web.dto.esb");
            marshaller=esbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_ENCODING, Charsets.UTF_8.name());
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,false);
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT,true);
            unmarshaller=esbContext.createUnmarshaller();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected boolean supports(Class aClass) {
        return aClass.isAssignableFrom(EsbXo.class);
    }

    @Override
    protected void transform(Source source, Result result) throws TransformerException {
        super.transform(source, result);
    }

    @Override
    protected EsbXo readFromSource(Class aClass, HttpHeaders httpHeaders, Source source) throws Exception {
        /**
         * 将Xml编码并转换成EsbXo并写入HttpRequest的流中
         */
        if(source instanceof StreamSource){
            StreamSource streamSource=(StreamSource)source;
            return unmarshaller.unmarshal(streamSource,EsbXo.class).getValue();
        }else {
            throw new HttpMessageConversionException("Http message convert to java object failed!");
        }
    }

    @Override
    protected void writeToResult(EsbXo o, HttpHeaders httpHeaders, Result result) throws Exception {
        /**
         * 将EsbXo编译成Xml并写入HttpResponse的流中
         */
        if(result instanceof StreamResult){
            StreamResult streamResult=(StreamResult)result;
            Writer writer= new OutputStreamWriter(streamResult.getOutputStream());
            writer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            marshaller.marshal(o,writer);
        }else{
            throw new HttpMessageConversionException("Java object convert to http message failed!");
        }
    }
}
