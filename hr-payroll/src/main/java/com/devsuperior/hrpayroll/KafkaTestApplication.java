//package com.devsuperior.hrpayroll;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.kafka.core.KafkaTemplate;
//
//@SpringBootApplication
//public class KafkaTestApplication implements CommandLineRunner {
//
//    @Autowired
//    private KafkaTemplate<String, String> kafkaTemplate;
//
//    public static void main(String[] args) {
//        SpringApplication.run(KafkaTestApplication.class, args);
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        String topic = "payroll.generated.email";
//        String message = "Estou encaminhando uma mensagem do aplicativo Spring boot!";
//
//        kafkaTemplate.send(topic, message);
//        System.out.println("Mensagem enviada: " + message);
//    }
//}