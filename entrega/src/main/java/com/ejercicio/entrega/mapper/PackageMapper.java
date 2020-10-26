package com.ejercicio.entrega.mapper;

import com.ejercicio.entrega.model.dao.PackageDAO;
import com.ejercicio.entrega.model.dto.PackageDto;
import com.ejercicio.entrega.shared.mapper.MapperDto;
import org.springframework.stereotype.Component;

@Component
public class PackageMapper implements MapperDto<PackageDAO, PackageDto> {

  public static final PackageMapper INSTANCE = new PackageMapper();

  @Override
  public PackageDto write(PackageDAO instance) {
    return PackageDto.builder().id(instance.getId()).regions(RegionsMapper.INSTANCE.write(instance.getRegions()))
        .code(instance.getCode()).day(instance.getDay()).build();
  }

  @Override
  public PackageDAO read(PackageDto instance) {
    return PackageDAO.builder().id(instance.getId()).regions(RegionsMapper.INSTANCE.read(instance.getRegions()))
        .code(instance.getCode()).day(instance.getDay()).build();
  }

}
