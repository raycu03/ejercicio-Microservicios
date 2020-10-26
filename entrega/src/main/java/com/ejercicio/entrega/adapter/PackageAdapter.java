package com.ejercicio.entrega.adapter;

import com.ejercicio.entrega.event.model.EventDto;
import com.ejercicio.entrega.event.model.PackageEvenDto;
import com.ejercicio.entrega.event.service.EventService;
import com.ejercicio.entrega.mapper.PackageMapper;
import com.ejercicio.entrega.model.dao.PackageDAO;
import com.ejercicio.entrega.model.dto.PackageDto;
import com.ejercicio.entrega.repository.PackageRepository;
import com.ejercicio.entrega.service.PackageService;
import com.ejercicio.entrega.shared.Queue;
import com.ejercicio.entrega.shared.Action;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class PackageAdapter implements PackageService {

  @Autowired
  private PackageRepository packageRepository;
  @Autowired
  private EventService eventService;

  @Override
  public PackageDto save(PackageDto packageDto) {
    try {
      PackageDAO packageDAO = packageRepository.save(PackageMapper.INSTANCE.read(packageDto));
      queue(packageDAO, Action.GUARDAR);
      return PackageMapper.INSTANCE.write(packageDAO);

    } catch (DataIntegrityViolationException e) {

      throw new RuntimeException("codigo ya registrado");
    }
  }

  @Override
  public PackageDto update(PackageDto packageDto) {
    findById(packageDto.getId());
    validateDelivery(packageDto.getId());
    PackageDAO packageDtoFound = packageRepository.save(PackageMapper.INSTANCE.read(packageDto));
    queue(packageDtoFound, Action.ACTUALIZAR);
    return PackageMapper.INSTANCE.write(packageDtoFound);
  }

  @Override
  public void delete(Integer id) {
    PackageDAO packageDAO = PackageMapper.INSTANCE.read(findById(id));
    validateDelivery(packageDAO.getId());
    packageRepository.delete(packageDAO);
    queue(packageDAO, Action.BORRAR);
  }

  @Override
  public List<PackageDto> findAll() {
    return PackageMapper.INSTANCE.write(packageRepository.findAll());
  }

  @Override
  public PackageDto findById(Integer id) {
    return PackageMapper.INSTANCE.write(packageRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Region no encontrada")));
  }

  @Override
  public void generate() {
    List<PackageDAO> packages = packageRepository.delivery();
    eventService.emitterMessageList(listReturn(packages), Queue.DELIVERIES);
  }


  private List<String> listReturn(List<PackageDAO> packages) {
    List<String> list = new ArrayList<>();
    packages.forEach(packageDAO -> list.add(packageDAO.getCode()));
    return list;
  }

  void queue(PackageDAO packageDAO, Action action) {
    PackageEvenDto regionsEventDto = PackageEvenDto.builder().id(packageDAO.getId())
        .regionsCode(packageDAO.getRegions().getCode()).
            day(packageDAO.getDay()).code(packageDAO.getCode()).build();
    EventDto event = EventDto.builder().action(action.getValue()).objet(regionsEventDto).build();
    eventService.emitterMessage(event, Queue.PACKAGE_CREATE);
  }

  void validateDelivery(Integer id) {
    if (packageRepository.validateDelivery(id).isPresent()) {
      throw new RuntimeException("Este paquete ya fue entregado");
    }
  }

}
