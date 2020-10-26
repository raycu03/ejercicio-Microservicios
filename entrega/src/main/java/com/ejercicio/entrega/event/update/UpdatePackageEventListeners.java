package com.ejercicio.entrega.event.update;

import com.ejercicio.entrega.event.model.EventDto;
import com.ejercicio.entrega.event.model.PackageEvenDto;
import com.ejercicio.entrega.model.dao.PackageDAO;
import com.ejercicio.entrega.model.dao.RegionsDAO;
import com.ejercicio.entrega.repository.PackageRepository;
import com.ejercicio.entrega.repository.RegionsRepository;
import com.ejercicio.entrega.shared.Queue;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class UpdatePackageEventListeners {

  @Autowired
  private PackageRepository packageRepository;

  @Autowired
  private RegionsRepository regionsRepository;

  private final ObjectMapper mapper = new ObjectMapper();

  @JmsListener(destination = Queue.PACKAGE_DELIVERIES)
  public void listener(String jsonInString) {
    try {
      EventDto regions = mapper.readValue(jsonInString, EventDto.class);
      actions(regions);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void actions(EventDto eventDto) {
    PackageDAO packageDAO = convert(eventDto);
    switch (eventDto.getAction()) {
      case Queue.UPDATE_ACTION:
        packageRepository.save(found(packageDAO));
      default:
    }
  }

  private PackageDAO found(PackageDAO regionsDAO) {
    PackageDAO regionsFound = packageRepository.findByCode(regionsDAO.getCode())
        .orElseThrow(() -> new RuntimeException("Region no encontrada"));
    regionsDAO.setId(regionsFound.getId());
    return regionsDAO;
  }

  private PackageDAO convert(EventDto eventDto) {
    try {
      String packages = new ObjectMapper().writeValueAsString(eventDto.getObjet());

      PackageEvenDto packageEvenDto = mapper.readValue(packages, PackageEvenDto.class);
      return PackageDAO.builder().code(packageEvenDto.getCode()).day(packageEvenDto.getDay()).
          regions(loadRegions(packageEvenDto.getRegionsCode())).build();

    } catch (Exception e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  private RegionsDAO loadRegions(String code) {
    return regionsRepository.findByCode(code).get();

  }
}
