package com.ejercicio.entrega.event.service;

import com.ejercicio.entrega.event.model.EventDto;
import com.ejercicio.entrega.event.model.PackageEvenDto;
import java.util.List;

public interface EventService {

    void emitterMessage(EventDto baseJmsDto, String queue);

    void emitterMessageList(List<String> baseJmsDto, String queue);
}
