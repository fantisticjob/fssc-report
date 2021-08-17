package com.longfor.fsscreport.bpm.consumer;

import com.longfor.fsscreport.bpm.service.IBpmService;
import com.longfor.fsscreport.config.RabbitMqConfig;
import com.rabbitmq.client.Channel;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * RabbitMq回调处理公共类
 * @author 
 *
 */
@Component
public class BpmRabbitMqConsumer {

	@Autowired
	private IBpmService ibs;
	
	
    private final Logger log = LoggerFactory.getLogger(getClass());

    @RabbitListener(queues = RabbitMqConfig.BPM_CALLBACK_QUEUE, containerFactory = "bpmFactory")
    public void handleMessage(Message message, Channel channel)  {
        try {
            // 处理消息,处理业务逻辑
            log.info("LFMqDemoConsumer  handleMessage :{}" + message);
            //String string = message.getMessageProperties().toString();
            log.info("LFMqDemoConsumer  getMessageProperties :{}" + 1);
            //String string2 = new String(message.getBody(), "utf-8");
            log.info("LFMqDemoConsumer  receiveMessage :{}" + 2);
            log.info("接收消息成功");
            // 确认消息已经消费成功
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            //ibs.updateStateByInstanceId(new String(message.getBody(), "utf-8"));
        }catch (Exception e){

        	log.error("handleMessage()方法异常{}",e.toString());
            //  丢弃消息

            // 拒绝当前消息，并把消息返回原队列
            try {
				channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
			} catch (IOException e1) {
	        	log.error("basicNack()方法异常{}",e.toString());

			}
        }
    }
}
