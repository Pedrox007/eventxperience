package com.project.eventxperience.framework.utils;

import com.project.eventxperience.framework.model.User;
import com.project.eventxperience.framework.model.Role;
import com.project.eventxperience.sportevent.model.SportEvent;

import java.util.List;

public class EventUtils {

    public static boolean isOrganizerOfEvent(SportEvent event, User user) {
        List<Role> roles = user.getRoles();

        if (event.getCreator() != null && event.getCreator().getId().equals(user.getId())) {
            for (Role role : roles) {
                if ("ROLE_ORGANIZER".equals(role.getName())) {
                    return true;
                }
            }
        }

        return false;
    }
}
