package com.ejercicio.planificacion.event.deliveries;

import com.ejercicio.planificacion.service.DeliveriesService;
import com.ejercicio.planificacion.shared.Queue;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class DeliveriesEvenListeners {

  @Autowired
  private DeliveriesService deliveriesService;

  private ObjectMapper mapper = new ObjectMapper();

  @JmsListener(destination = Queue.DELIVERIES)
  public void listener(String jsonInString) {
    try {
      List<String> packageCode  = Arrays.asList(mapper.readValue(jsonInString, String[].class));
      deliveriesService.createDeliveries(packageCode);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
