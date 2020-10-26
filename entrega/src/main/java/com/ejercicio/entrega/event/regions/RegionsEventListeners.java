package com.ejercicio.entrega.event.regions;

import com.ejercicio.entrega.event.model.EventDto;
import com.ejercicio.entrega.model.dao.RegionsDAO;
import com.ejercicio.entrega.repository.RegionsRepository;
import com.ejercicio.entrega.shared.Queue;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class RegionsEventListeners {

    @Autowired
    private RegionsRepository regionsRepository;

    private ObjectMapper mapper = new ObjectMapper();

    @JmsListener(destination = Queue.REGIONS_CREATE)
    public void listener(String jsonInString) {
        try {
            EventDto regions = mapper.readValue(jsonInString, EventDto.class);
            actions(regions);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void actions(EventDto eventDto) {
        RegionsDAO regions = convert(eventDto);
        switch(eventDto.getAction()) {
            case Queue.SAVE_ACTION:
                    regionsRepository.save(regions);
                break;
            case Queue.DELETE_ACTION:
                regionsRepository.delete(found(regions));
                break;
            case Queue.UPDATE_ACTION:
                regionsRepository.save(found(regions));
            default:
        }
    }
    private RegionsDAO found(RegionsDAO regionsDAO){
        RegionsDAO regionsFound = regionsRepository.findByCode(regionsDAO.getCode()).orElseThrow(() -> new RuntimeException("Region no encontrada"));
        regionsDAO.setId(regionsFound.getId());
        return regionsDAO;
    }

    private RegionsDAO convert(EventDto eventDto){
        try {
            String packages = new ObjectMapper().writeValueAsString(eventDto.getObjet());
            return mapper.readValue(packages, RegionsDAO.class);

        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
