package com.project.eventxperience.utils;

import com.project.eventxperience.model.SportEvent;
import com.project.eventxperience.model.User;
import com.project.eventxperience.model.Role;

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
