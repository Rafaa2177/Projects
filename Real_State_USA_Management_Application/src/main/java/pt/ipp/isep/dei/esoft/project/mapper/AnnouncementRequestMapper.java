package pt.ipp.isep.dei.esoft.project.mapper;

import pt.ipp.isep.dei.esoft.project.domain.AnnouncementRequest;
import pt.ipp.isep.dei.esoft.project.domain.Person;
import pt.ipp.isep.dei.esoft.project.domain.Property;
import pt.ipp.isep.dei.esoft.project.domain.enums.RequestType;
import pt.ipp.isep.dei.esoft.project.dto.AnnouncementRequestDTO;

public class AnnouncementRequestMapper {
    private Property property;
    private double price;
    private double contractDuration;
    private Person agent;
    private RequestType requestType;
    public static AnnouncementRequestDTO toDTO(Property property, double contractDuration,
                                               Person agent, RequestType requestType) {
        AnnouncementRequestDTO requestDTO = new AnnouncementRequestDTO(property , contractDuration, agent, requestType);
      return requestDTO;
    }
    public AnnouncementRequest toDomain(Property property, double contractDuration,
                                        Person agent, RequestType requestType){
        return new AnnouncementRequest(property,contractDuration, agent,requestType);
    }
}
