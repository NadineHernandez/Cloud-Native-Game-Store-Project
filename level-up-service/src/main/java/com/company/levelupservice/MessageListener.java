package com.company.levelupservice;

import com.company.levelupservice.controller.LevelUpController;
import com.company.levelupservice.dto.LevelUp;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageListener {
    @Autowired
    LevelUpController levelUpController;

    @RabbitListener(queues = LevelUpServiceApplication.QUEUE_NAME)
    public void receiveMessage(LevelUp msg){
        if(msg.getLevelUpId() == 0){

        }
    }
}
