package com.ejercicio.planificacion.adapter;

import com.ejercicio.planificacion.event.model.EventDto;
import com.ejercicio.planificacion.event.service.EventService;
import com.ejercicio.planificacion.model.dao.DiaryRegionDAO;
import com.ejercicio.planificacion.model.dao.DiaryRegionDetailsDAO;
import com.ejercicio.planificacion.model.dao.PackageDAO;
import com.ejercicio.planificacion.model.dao.RegionsDAO;
import com.ejercicio.planificacion.model.dao.VehiclesDAO;
import com.ejercicio.planificacion.repository.DiaryRegionDetailsRepository;
import com.ejercicio.planificacion.repository.DiaryRegionRepository;
import com.ejercicio.planificacion.repository.PackageRepository;
import com.ejercicio.planificacion.repository.RegionsRepository;
import com.ejercicio.planificacion.repository.VehiclesRepository;
import com.ejercicio.planificacion.service.DeliveriesService;
import com.ejercicio.planificacion.shared.Action;
import com.ejercicio.planificacion.shared.Day;
import com.ejercicio.planificacion.shared.Queue;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveriesAdapter implements DeliveriesService {

  @Autowired
  private PackageRepository packageRepository;
  @Autowired
  private RegionsRepository regionsRepository;
  @Autowired
  private DiaryRegionRepository diaryRegionRepository;
  @Autowired
  private VehiclesRepository vehiclesRepository;
  @Autowired
  private DiaryRegionDetailsRepository diaryRegionDetailsRepository;
  @Autowired
  private EventService eventService;

  @Override
  public void createDeliveries(List<String> code) {
    List<PackageDAO> findPackage = findPackage(code);
    selectRegions(findPackage);
  }

  private List<PackageDAO> findPackage(List<String> code) {
    List<PackageDAO> packageList = new ArrayList<>();
    code.forEach(codes -> {
      Optional<PackageDAO> packageDAO = packageRepository.findByCode(codes);
      packageDAO.ifPresent(packageList::add);
    });
    return packageList;
  }

  private void selectRegions(List<PackageDAO> packageDAOS) {
    packageDAOS.forEach(packageDAO -> {
      RegionsDAO regionsDAO = loadRegions(packageDAO.getRegionsCode());
      DiaryRegionDAO diaryRegionDAO = loadRegions(regionsDAO);
      List<VehiclesDAO> vehiclesList = vehiclesRepository.findByRegions(regionsDAO);
      CreteDiaryRegionDetailsDAO(packageDAO, vehiclesList, diaryRegionDAO);
    });
  }

  private RegionsDAO loadRegions(String code) {
    Optional<RegionsDAO> regionsDAO = regionsRepository.findByCode(code);
    if (regionsDAO.isPresent()) {
      return regionsDAO.get();
    }
    throw new RuntimeException("Registro no encontrado");
  }

  private DiaryRegionDAO loadRegions(RegionsDAO regionsDAO) {
    Optional<DiaryRegionDAO> diaryRegionDAO = diaryRegionRepository
        .findByRegionsId(regionsDAO.getId());
    if (diaryRegionDAO.isPresent()) {
      return updateDiary(diaryRegionDAO.get());
    }
    throw new RuntimeException("Registro no encontrado");
  }

  private DiaryRegionDAO updateDiary(DiaryRegionDAO dao) {
    if (dao.getDetails().isEmpty()) {
      dao.setDay(GenerateDay());
      return diaryRegionRepository.save(dao);
    }
    if (dao.getDay().before(Calendar.getInstance())) {
      dao.setDay(GenerateDay());
      return diaryRegionRepository.save(dao);
    }
    return dao;
  }

  private void CreteDiaryRegionDetailsDAO(PackageDAO packageDAO, List<VehiclesDAO> vehiclesDAOS,
      DiaryRegionDAO diaryRegionDAO) {

    for (VehiclesDAO vehiclesDAO : vehiclesDAOS) {
      if (diaryRegionDetailsRepository.findByVehiclesAndDay(vehiclesDAO, diaryRegionDAO.getDay())
          .size() < vehiclesDAO.getMaxNumberDeliveries()) {
        packageDAO.setDay(diaryRegionDAO.getDay());
        PackageDAO safe = queue(packageDAO);
        DiaryRegionDetailsDAO detailsDAO = DiaryRegionDetailsDAO.builder().id(0)
            .day(diaryRegionDAO.getDay()).packages(safe).vehicles(vehiclesDAO).build();
        detailsDAO = diaryRegionDetailsRepository.save(detailsDAO);
        List<DiaryRegionDetailsDAO> list = diaryRegionDAO.getDetails();
        list.add(detailsDAO);
        diaryRegionDAO.setDetails(list);
        diaryRegionRepository.save(diaryRegionDAO);
        break;

      }
    }
    updateDailyRegion(vehiclesDAOS, diaryRegionDAO);
  }

  private Calendar GenerateDay() {
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);
    if (Day.DOMINGO.getValue().equals(calendar.get(Calendar.DAY_OF_WEEK))) {
      calendar.add(Calendar.DATE, 1);
    }
    return calendar;
  }


  private PackageDAO queue(PackageDAO packageDAO) {
    PackageDAO dao = packageRepository.save(packageDAO);
    EventDto event = EventDto.builder().action(Action.ACTUALIZAR.getValue()).objet(dao).build();
    eventService.emitterMessage(event, Queue.PACKAGE_DELIVERIES);
    return dao;
  }


  private void updateDailyRegion(List<VehiclesDAO> vehiclesDAOS, DiaryRegionDAO diaryRegionDAO) {
    Boolean updateDay = true;
    for (VehiclesDAO vehiclesDAO : vehiclesDAOS) {
      if (diaryRegionDetailsRepository.findByVehiclesAndDay(vehiclesDAO, diaryRegionDAO.getDay())
          .size() < vehiclesDAO.getMaxNumberDeliveries()) {
        updateDay = false;
      }
    }
    if (updateDay) {
      diaryRegionDAO.setDay(updateDay(diaryRegionDAO.getDay()));
      diaryRegionRepository.save(diaryRegionDAO);
    }
  }


  private Calendar updateDay(Calendar calendar) {
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);
    calendar.add(Calendar.DATE, 1);
    if (Day.DOMINGO.getValue().equals(calendar.get(Calendar.DAY_OF_WEEK))) {
      calendar.add(Calendar.DATE, 1);
    }
    return calendar;
  }

}
