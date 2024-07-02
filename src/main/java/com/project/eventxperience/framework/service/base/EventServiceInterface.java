package com.project.eventxperience.framework.service.base;

import com.project.eventxperience.framework.model.User;

import java.util.List;

public interface EventServiceInterface<RequestObject, ResponseObject> {
    ResponseObject saveEvent(User authenticatedUser, RequestObject requestObject);
    ResponseObject retrieveEventById(Long id);
    ResponseObject deleteEvent(Long id);
    ResponseObject updateEvent(User authenticatedUser, RequestObject requestObject, Long id);
    List<ResponseObject> listEvent();
}
