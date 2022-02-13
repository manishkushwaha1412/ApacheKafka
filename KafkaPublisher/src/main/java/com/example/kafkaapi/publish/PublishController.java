package com.example.kafkaapi.publish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublishController {

    @Autowired
    private KafkaTemplate<String, Object> template;

    private static final String KAFKA_TOPIC = "manish";

    @GetMapping("publish/{message}")
    public String publishMessage(@PathVariable String message){

        String localMessage = message + "Has been published to Topic "+KAFKA_TOPIC;
        template.send(KAFKA_TOPIC, localMessage);
        return "Published Successfully";
    }

    @GetMapping("publish/json")
    public String publishJson(){
        Student student = new Student(12345L, "Manish", "manish@gmail.com");
        template.send(KAFKA_TOPIC, student);
        return "JSON Object has been published";
    }

}
