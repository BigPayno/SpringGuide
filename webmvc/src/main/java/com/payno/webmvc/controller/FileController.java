package com.payno.webmvc.controller;

import com.google.common.base.Stopwatch;
import com.payno.webmvc.web.domain.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author payno
 * @date 2019/11/27 11:48
 * @description
 */
@RestController
@RequestMapping("file/")
public class FileController {
    @PostMapping("upload")
    public Response upload(@RequestParam("file") MultipartFile file) {
        return Response.ok();
    }
    @PostMapping("download")
    public ResponseEntity<byte[]> download() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment","fileName");
        return new ResponseEntity<byte[]>(new byte[10], headers, HttpStatus.CREATED);
    }
}
