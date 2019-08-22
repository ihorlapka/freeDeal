package com.ihorcompany.fd;

import com.ihorcompany.fd.service.StorageService;
import com.ihorcompany.fd.serviceImplement.storage.StorageProperties;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class FdApplication {

    @PostConstruct
    void timezone(){
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    public static void main(String[] args) {
        SpringApplication.run(FdApplication.class, args);
    }

    CommandLineRunner init(StorageService storageService){
        return (args -> {
            storageService.deleteAll();
            storageService.init();
        });
    }
}
