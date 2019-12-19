package com.payno.webmvc.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author payno
 * @date 2019/11/27 11:48
 * @description
 *  TEST
 */
@RestController
@RequestMapping("file/")
public class FileController {
    @PostMapping("download")
    public ResponseEntity<byte[]> download() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment","fileName");
        return new ResponseEntity<byte[]>(new byte[10], headers, HttpStatus.CREATED);
    }
}
