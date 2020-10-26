package com.ejercicio.planificacion.event.packages;

import com.ejercicio.planificacion.event.model.EventDto;
import com.ejercicio.planificacion.model.dao.PackageDAO;
import com.ejercicio.planificacion.repository.PackageRepository;
import com.ejercicio.planificacion.shared.Queue;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PackageEvenListeners {

  @Autowired
  private PackageRepository packageRepository;

  private ObjectMapper mapper = new ObjectMapper();

  @JmsListener(destination = Queue.PACKAGE)
  public void listener(String jsonInString) {
    try {
      EventDto regions = mapper.readValue(jsonInString, EventDto.class);
      actions(regions);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void actions(EventDto eventDto){
    PackageDAO packageEvenDto = convert(eventDto);
    switch(eventDto.getAction()) {
      case Queue.SAVE_ACTION:
        packageEvenDto.setId(0);
          packageRepository.save(packageEvenDto);
        break;
      case Queue.DELETE_ACTION:
        packageRepository.delete(found(packageEvenDto));
        break;
      case Queue.UPDATE_ACTION:
        packageRepository.save(found(packageEvenDto));
        break;
      default:
    }
  }

  private PackageDAO found(PackageDAO packageDAO){
    PackageDAO packages = packageRepository.findByCode(packageDAO.getCode()).orElseThrow(() -> new RuntimeException("Region no encontrada"));
    packageDAO.setId(packages.getId());
    return packageDAO;
  }

  private PackageDAO convert(EventDto eventDto){
    try {
      String packages = new ObjectMapper().writeValueAsString(eventDto.getObjet());
      return mapper.readValue(packages, PackageDAO.class);

    }catch (Exception e){
      throw new RuntimeException(e.getMessage());
    }
  }

}
