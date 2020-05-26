package com.payno.feign;

import com.google.common.io.ByteStreams;
import com.google.common.io.Files;
import feign.Feign;
import feign.codec.StringDecoder;
import feign.form.spring.SpringFormEncoder;
import feign.spring.SpringContract;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author payno
 * @date 2020/5/26 15:04
 * @description
 */
public class SpringMultipartDemo {
    public interface EasyClient {
        @PostMapping("/upload")
        void upload(MultipartFile file);
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
        /**
         *  这个form直接用有问题
         * @CloudGuide有修改的
         */
        EasyClient client = Feign.builder()
                .contract(new SpringContract())
                .encoder(new SpringFormEncoder())
                .decoder(new StringDecoder())
                .target(EasyClient.class,"http://localhost:8080");
        client.upload(of(new File("D://test.properties")));
    }
}
