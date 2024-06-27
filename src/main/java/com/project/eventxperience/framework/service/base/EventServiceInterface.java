package com.project.eventxperience.framework.service.base;

import java.util.List;

public interface EventServiceInterface<RequestObject, ResponseObject> {
    ResponseObject saveEvent(RequestObject requestObject);
    ResponseObject retrieveEventById(Long id);
    ResponseObject deleteEvent(Long id);
    ResponseObject updateEvent(RequestObject requestObject, Long id);
    List<ResponseObject> listEvent();
}
