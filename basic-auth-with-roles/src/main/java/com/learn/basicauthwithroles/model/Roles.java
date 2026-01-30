package com.learn.basicauthwithroles.model;


import lombok.Getter;

import java.util.Set;

@Getter
public enum Roles {
    ADMIN(Set.of(Permissions.CREATE, Permissions.READ, Permissions.WRITE)),
    USER(Set.of(Permissions.READ, Permissions.WRITE)),
    PUBLIC(Set.of(Permissions.READ));

    private final Set<Permissions> permissions;

    Roles(Set<Permissions> permissions) {
        this.permissions = permissions;
    }
}

