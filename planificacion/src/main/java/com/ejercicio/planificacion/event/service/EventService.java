package com.ejercicio.planificacion.event.service;

import com.ejercicio.planificacion.event.model.EventDto;

public interface EventService {

    void emitterMessage(EventDto baseJmsDto, String queue);
}
