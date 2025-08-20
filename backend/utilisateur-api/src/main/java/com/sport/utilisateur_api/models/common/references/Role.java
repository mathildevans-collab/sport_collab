package com.sport.utilisateur_api.models.common.references;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public enum Role {

    USER,
    ADMIN(Role.USER);

    private final Set<Role> parents = new LinkedHashSet<>();

    Role(Role... parents) {
      Collections.addAll(this.parents, parents);
    }

    public static Set<Role> getAllRoles(Role role){
      Set<Role> roles = new LinkedHashSet<>();
      roles.add(role);
      for (Role r : role.parents){
        roles.addAll(Role.getAllRoles(r));
      }
      return roles;
    }

    public boolean hasRole(Role other){
      return getAllRole().contains(other);
    }

    public Set<Role> getAllRole(){
      return Role.getAllRoles(this);
    }

}
