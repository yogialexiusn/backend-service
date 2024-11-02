package org.backend.request;

import lombok.Data;
import org.backend.validation.ValueMustValid;

@Data
public class CreateUserRequest {
    private String username;
    @ValueMustValid(valueAllowed = {"SUPERADMIN", "ADMIN", "VIEWER"})
    private String role;

}