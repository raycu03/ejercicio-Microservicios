package com.ejercicio.planificacion.event.emitter;


import com.ejercicio.planificacion.event.model.EventDto;
import com.ejercicio.planificacion.event.service.EventService;
import com.ejercicio.planificacion.repository.RegionsRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
            System.out.println("### Message Successfully Pushed into Queue ###");

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }


}
