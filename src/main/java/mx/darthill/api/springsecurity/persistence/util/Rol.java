package mx.darthill.api.springsecurity.persistence.util;

import java.util.Arrays;
import java.util.List;

public enum Rol {

    ADMINISTRATOR(Arrays.asList(
            RolPermission.READ_ALL_VETERINARIOS,
            RolPermission.READ_ONE_VETERINARIOS,
            RolPermission.CREATE_ONE_VETERINARIOS,
            RolPermission.UPDATE_ONE_VETERINARIOS,
            RolPermission.DELETE_ONE_VETERINARIOS,

            RolPermission.READ_ALL_ESPECIALIDAD,
            RolPermission.READ_ONE_ESPECIALIDAD,
            RolPermission.CREATE_ONE_ESPECIALIDAD,
            RolPermission.UPDATE_ONE_ESPECIALIDAD,
            RolPermission.DELETE_ONE_ESPECIALIDAD,

            RolPermission.READ_PROFILE
            )),
    OPERATOR(Arrays.asList(
            RolPermission.READ_ALL_VETERINARIOS,
            RolPermission.READ_ONE_VETERINARIOS,
            RolPermission.UPDATE_ONE_VETERINARIOS,

            RolPermission.READ_ALL_ESPECIALIDAD,
            RolPermission.READ_ONE_ESPECIALIDAD,
            RolPermission.UPDATE_ONE_ESPECIALIDAD,

            RolPermission.READ_PROFILE
    )),
    CUSTOMER(Arrays.asList(
            RolPermission.READ_PROFILE
    ));

    private List<RolPermission> permissions;

    Rol(List<RolPermission> permissions) {
        this.permissions = permissions;
    }

    public List<RolPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<RolPermission> permissions) {
        this.permissions = permissions;
    }
}
