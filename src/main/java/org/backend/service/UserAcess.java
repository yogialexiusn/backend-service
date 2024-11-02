package org.backend.service;

import org.backend.request.BlockUserRequest;
import org.backend.request.CreateAccessRequest;
import org.backend.request.CreateUserRequest;
import org.backend.response.GetUserAccessListResponse;
import org.backend.response.embedded.*;


public interface UserAcess {
    CreateUserResponse createUser(CreateUserRequest request);
    BlockUserResponse blockUser(BlockUserRequest request);
    CreateAccessResponse createAccess(CreateAccessRequest request);
    GetUserResponse getUser(String username);
    GetUserAccessListResponse getUserAccess(String username);

}
