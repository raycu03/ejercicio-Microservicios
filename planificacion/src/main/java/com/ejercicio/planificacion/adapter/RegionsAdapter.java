package com.ejercicio.planificacion.adapter;

import com.ejercicio.planificacion.event.model.EventDto;
import com.ejercicio.planificacion.event.service.EventService;
import com.ejercicio.planificacion.mapper.RegionsMapper;
import com.ejercicio.planificacion.model.dao.DiaryDAO;
import com.ejercicio.planificacion.model.dao.DiaryRegionDAO;
import com.ejercicio.planificacion.model.dao.DiaryRegionDetailsDAO;
import com.ejercicio.planificacion.model.dao.RegionsDAO;
import com.ejercicio.planificacion.model.dto.RegionsDto;
import com.ejercicio.planificacion.repository.DiaryRegionRepository;
import com.ejercicio.planificacion.repository.DiaryRepository;
import com.ejercicio.planificacion.repository.PackageRepository;
import com.ejercicio.planificacion.repository.RegionsRepository;
import com.ejercicio.planificacion.repository.VehiclesRepository;
import com.ejercicio.planificacion.service.RegionsService;
import com.ejercicio.planificacion.shared.Action;
import com.ejercicio.planificacion.shared.Queue;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class RegionsAdapter implements RegionsService {

  @Autowired
  private RegionsRepository regionsRepository;
  @Autowired
  private PackageRepository packageRepository;
  @Autowired
  private VehiclesRepository vehiclesRepository;
  @Autowired
  private DiaryRegionRepository diaryRegionRepository;
  @Autowired
  private DiaryRepository diaryRepository;

  @Autowired
  private EventService eventService;

  @Override
  public RegionsDto save(RegionsDto regionsDto) {
    try {
      RegionsDAO regionsDAO = regionsRepository.save(RegionsMapper.INSTANCE.read(regionsDto));
      DiaryRegionCreate(regionsDAO);
      queue(regionsDAO, Action.GUARDAR);
      return RegionsMapper.INSTANCE.write(regionsDAO);

    }catch (DataIntegrityViolationException e){

      throw new RuntimeException("condigo ya registrado");
    }

  }

  @Override
  public RegionsDto update(RegionsDto regionsDto) {
    findById(regionsDto.getId());
    RegionsDAO regionsDAO = regionsRepository.save(RegionsMapper.INSTANCE.read(regionsDto));
    queue(regionsDAO, Action.ACTUALIZAR);
    return RegionsMapper.INSTANCE.write(regionsDAO);
  }

  @Override
  public RegionsDto findById(Integer id) {
    return RegionsMapper.INSTANCE.write(regionsRepository.findById(id).orElseThrow(() -> new RuntimeException("Region no encontrada")));
  }


  @Override
  public void delete(Integer id) {
    RegionsDAO regionsDAO = RegionsMapper.INSTANCE.read(findById(id));
    validate(regionsDAO);
    regionsRepository.delete(regionsDAO);
    queue(regionsDAO, Action.BORRAR);
  }

  @Override
  public List<RegionsDto> findAll() {
    return RegionsMapper.INSTANCE.write(regionsRepository.findAll());
  }

  void queue(RegionsDAO regionsDAO, Action action){
    EventDto event = EventDto.builder().action(action.getValue()).objet(regionsDAO).build();
    eventService.emitterMessage(event, Queue.REGIONS);
  }

  private void validate(RegionsDAO regionsDAO){
    if (!packageRepository.findByRegionsCode(regionsDAO.getCode()).isEmpty() || !vehiclesRepository.findByRegions(regionsDAO).isEmpty()){
      throw new RuntimeException("no puede borrar la region");
    }
  }

  private void DiaryRegionCreate(RegionsDAO regionsDAO){
    List<DiaryRegionDetailsDAO> list = new ArrayList<>();
    if (!diaryRegionRepository.findByRegions(regionsDAO).isPresent()){
       DiaryRegionDAO diaryRegionDAO = DiaryRegionDAO.builder().id(0).regions(regionsDAO).details(list).build();
      creteDiary(diaryRegionRepository.save(diaryRegionDAO));
    }
  }

  private void creteDiary(DiaryRegionDAO diaryRegionDAO){
    List<DiaryRegionDAO> diaryRegion = new ArrayList<>();
    if (diaryRepository.findAll().isEmpty()){
      DiaryDAO dao = DiaryDAO.builder().id(1).diaryRegion(diaryRegion).build();
      diaryRepository.save(dao);
    }
    Optional<DiaryDAO> dao = diaryRepository.findById(1);
    if (dao.isPresent()){
      dao.get().getDiaryRegion().add(diaryRegionDAO);
      diaryRepository.save(dao.get());
    }
  }

}
