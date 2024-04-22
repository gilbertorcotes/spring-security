package mx.darthill.api.springsecurity.persistence.util;

import java.util.Arrays;
import java.util.List;

public enum RolEnum {

    ADMINISTRATOR(Arrays.asList(
            RolPermissionEnum.READ_ALL_VETERINARIOS,
            RolPermissionEnum.READ_ONE_VETERINARIOS,
            RolPermissionEnum.CREATE_ONE_VETERINARIOS,
            RolPermissionEnum.UPDATE_ONE_VETERINARIOS,
            RolPermissionEnum.DELETE_ONE_VETERINARIOS,

            RolPermissionEnum.READ_ALL_ESPECIALIDAD,
            RolPermissionEnum.READ_ONE_ESPECIALIDAD,
            RolPermissionEnum.CREATE_ONE_ESPECIALIDAD,
            RolPermissionEnum.UPDATE_ONE_ESPECIALIDAD,
            RolPermissionEnum.DELETE_ONE_ESPECIALIDAD,

            RolPermissionEnum.READ_PROFILE
            )),
    OPERATOR(Arrays.asList(
            RolPermissionEnum.READ_ALL_VETERINARIOS,
            RolPermissionEnum.READ_ONE_VETERINARIOS,
            RolPermissionEnum.UPDATE_ONE_VETERINARIOS,

            RolPermissionEnum.READ_ALL_ESPECIALIDAD,
            RolPermissionEnum.READ_ONE_ESPECIALIDAD,
            RolPermissionEnum.UPDATE_ONE_ESPECIALIDAD,

            RolPermissionEnum.READ_PROFILE
    )),
    CUSTOMER(Arrays.asList(
            RolPermissionEnum.READ_PROFILE
    ));

    private List<RolPermissionEnum> permissions;

    RolEnum(List<RolPermissionEnum> permissions) {
        this.permissions = permissions;
    }

    public List<RolPermissionEnum> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<RolPermissionEnum> permissions) {
        this.permissions = permissions;
    }
}
