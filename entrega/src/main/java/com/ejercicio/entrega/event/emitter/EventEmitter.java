package com.ejercicio.entrega.event.emitter;

import com.ejercicio.entrega.event.model.EventDto;
import com.ejercicio.entrega.event.model.PackageEvenDto;
import com.ejercicio.entrega.event.service.EventService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class EventEmitter implements EventService {

  ObjectMapper mapper = new ObjectMapper();

  @Autowired
  private JmsTemplate jmsTemplate;

  @Override
  public void emitterMessage(EventDto baseJmsDto, String queue) {
    try {
      String jsonStr = mapper.writeValueAsString(baseJmsDto);
      jmsTemplate.convertAndSend(queue, jsonStr);

    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void emitterMessageList(List<String> baseJmsDto, String queue) {
    try {
      String jsonStr = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(baseJmsDto);
      jmsTemplate.convertAndSend(queue, jsonStr);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
  }

}
