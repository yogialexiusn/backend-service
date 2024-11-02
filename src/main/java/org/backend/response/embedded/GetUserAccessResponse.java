package org.backend.response.embedded;

import lombok.*;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUserAccessResponse {
    private String username;
    private String menuAccess;
    private String roleName;
    private Timestamp createdTime;
    private Timestamp updatedTime;
}
