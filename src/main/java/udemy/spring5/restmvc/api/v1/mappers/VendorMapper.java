package udemy.spring5.restmvc.api.v1.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import udemy.spring5.restmvc.api.v1.model.VendorDTO;
import udemy.spring5.restmvc.domain.Vendor;

@Mapper
public interface VendorMapper {

  VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);

  VendorDTO vendorToVendorDTO(Vendor vendor);
}
