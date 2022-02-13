package com.example.kafkaapi.publish;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ConsumerController {

    List<String> messages = new ArrayList<>();
    Student student = null;

    @GetMapping("/consume/message")
    public List<String> consumeMessage(){
        return this.messages;
    }

    @GetMapping("/consume/json")
    public Student consumeStudent(){
        return this.student;
    }

    @KafkaListener(groupId = "manish-1", topics = "manish", containerFactory = "kafkaListenerFactory")
    public List<String> consumeMessageFromTopic(String data){
        messages.add(data);
        return messages;
    }

    @KafkaListener(groupId = "manish-2", topics = "manish", containerFactory = "userKafkaListenerFactory")
    public Student consumeStudentMessageFromTopic(Student data){
       student = data;
       return student;
    }

}
