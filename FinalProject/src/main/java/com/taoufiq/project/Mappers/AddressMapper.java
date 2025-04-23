package com.taoufiq.project.Mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.taoufiq.project.DTOs.AddressDTO;
import com.taoufiq.project.Models.Address;

@Mapper
public interface AddressMapper {
    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    AddressDTO addressToAddressDTO(Address address);

   Address addressDTOToAddress(AddressDTO dto);
}
