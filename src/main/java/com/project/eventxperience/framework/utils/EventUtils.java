package com.project.eventxperience.framework.utils;

import com.project.eventxperience.framework.model.Event;
import com.project.eventxperience.framework.model.User;
import com.project.eventxperience.framework.model.Role;


public class EventUtils {

    public static boolean isOrganizerOfEvent(Event event, User user) {
        if (event.getCreator() != null && event.getCreator().getId().equals(user.getId())) {
            for (Role role : user.getRoles()) {
                if ("ROLE_ORGANIZER".equals(role.getName())) {
                    return true;
                }
            }
        }
        return false;
    }

}