package com.neo.scan;

import jdk.internal.dynalink.beans.StaticClass;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.security.PublicKey;

@SpringBootApplication
public class ScanApplication {

    public static void main(String[] args) {

        SpringApplication.run(ScanApplication.class, args);
    }

}
