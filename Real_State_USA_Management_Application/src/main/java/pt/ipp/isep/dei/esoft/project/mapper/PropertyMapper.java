package pt.ipp.isep.dei.esoft.project.mapper;

import pt.ipp.isep.dei.esoft.project.domain.Property;
import pt.ipp.isep.dei.esoft.project.dto.PropertyDTO;

import java.util.List;

public class PropertyMapper {

    public static PropertyDTO mapToDTO(Property property) {
        PropertyDTO dto = new PropertyDTO();
        dto.setAreaM2(property.getAreaM2());
        dto.setCityCentreDistance(property.getCityCentreDistance());
        dto.setPhotos(property.getPhotos());
        dto.setAddress(property.getAddress().toString());
        dto.setPrice(property.getPrice());
        dto.setOwner(property.getOwner().getName());
        dto.setPropertyType(property.getPropertyType());
        return dto;
    }
    public static PropertyDTO mapListToDTO(Property property) {
        PropertyDTO dto = new PropertyDTO();
        dto.setAreaM2(property.getAreaM2());
        dto.setCityCentreDistance(property.getCityCentreDistance());
        dto.setPhotos(property.getPhotos());
        dto.setAddress(property.getAddress().toString());
        dto.setPrice(property.getPrice());
        dto.setOwner(property.getOwner().getName());
        dto.setPropertyType(property.getPropertyType());
        return dto;
    }

}
