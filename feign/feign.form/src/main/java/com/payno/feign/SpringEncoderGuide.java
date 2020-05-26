package com.payno.feign;

import com.google.common.io.Files;
import feign.Feign;
import feign.codec.StringDecoder;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author payno
 * @date 2020/5/26 15:23
 * @description
 */
public class SpringEncoderGuide {
    public interface EasyClient {
        @PostMapping(value = "/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
        void upload(MultipartFile file);

        @PostMapping("/hello/{name}")
        String get(@PathVariable("name") String name);
    }

    public static MultipartFile of(File file){
        final DiskFileItem item = new DiskFileItem(
                "file",
                MediaType.MULTIPART_FORM_DATA_VALUE,
                true,
                file.getName(),
                100000000,
                file.getParentFile());
        try {
            OutputStream os = item.getOutputStream();
            os.write(Files.toByteArray(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new CommonsMultipartFile(item);
    }

    public static void main(String[] args) {
        EasyClient client = Feign.builder()
                .contract(new SpringMvcContract())
                .encoder(new SpringEncoder(()->
                        new HttpMessageConverters(new FormHttpMessageConverter(),new StringHttpMessageConverter())))
                .decoder(new StringDecoder())
                .target(EasyClient.class,"http://localhost:8080");
        client.upload(of(new File("D://test.properties")));
        System.out.println(client.get("chad"));
    }
}
