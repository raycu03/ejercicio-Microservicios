package com.ejercicio.planificacion.adapter;

import com.ejercicio.planificacion.mapper.VehiclesMapper;
import com.ejercicio.planificacion.model.dao.VehiclesDAO;
import com.ejercicio.planificacion.model.dto.VehiclesDto;
import com.ejercicio.planificacion.repository.DiaryRegionDetailsRepository;
import com.ejercicio.planificacion.repository.VehiclesRepository;
import com.ejercicio.planificacion.service.VehiclesService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class VehiclesAdapter implements VehiclesService {

  private final ObjectMapper mapper = new ObjectMapper();
  @Autowired
  private VehiclesRepository vehiclesRepository;
  @Autowired
  private DiaryRegionDetailsRepository diaryRegionDetailsRepository;

  @Override
  public VehiclesDto save(VehiclesDto vehiclesDto) {
    try {
      return VehiclesMapper.INSTANCE.write(vehiclesRepository.save(VehiclesMapper.INSTANCE.read(vehiclesDto)));
    }catch (DataIntegrityViolationException e){
      throw new RuntimeException("codigo ya registrado");
    }

  }

  @Override
  public VehiclesDto update(VehiclesDto vehiclesDto) {
    findById(vehiclesDto.getId());
    return VehiclesMapper.INSTANCE.write(vehiclesRepository.save(VehiclesMapper.INSTANCE.read(vehiclesDto)));
  }

  @Override
  public VehiclesDto findById(Integer id) {
    return VehiclesMapper.INSTANCE.write(vehiclesRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Region no encontrada")));
  }

  @Override
  public List<VehiclesDto> findAll() {
    return VehiclesMapper.INSTANCE.write(vehiclesRepository.findAll());
  }

  @Override
  public void delete(Integer id) {
    VehiclesDAO vehiclesDAO = VehiclesMapper.INSTANCE.read(findById(id));
    validate(vehiclesDAO);
    vehiclesRepository.delete(vehiclesDAO);
  }

  private void validate(VehiclesDAO vehiclesDAO) {
    if (!diaryRegionDetailsRepository.findByVehicles(vehiclesDAO).isEmpty()) {
      throw new RuntimeException("No puede borrar el vehículo");
    }
  }
}
