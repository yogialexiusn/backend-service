package org.backend.service.impl;

import org.backend.constant.ResponseCode;
import org.backend.entity.Access;
import org.backend.entity.User;
import org.backend.repository.AccessRepository;
import org.backend.repository.UserRepository;
import org.backend.request.BlockUserRequest;
import org.backend.request.CreateAccessRequest;
import org.backend.request.CreateUserRequest;
import org.backend.response.GetUserAccessListResponse;
import org.backend.response.embedded.*;
import org.backend.service.IUserAcess;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class UserAcessImpl implements IUserAcess {

    List<String> accessMenu = Arrays.asList("CAREER", "NEWS");

    private final AccessRepository accessRepository;
    private final UserRepository userRepository;

    public UserAcessImpl(AccessRepository accessRepository, UserRepository userRepository) {
        this.accessRepository = accessRepository;
        this.userRepository = userRepository;
    }

    @Override
    public CreateUserResponse createUser(CreateUserRequest request) {
        CreateUserResponse.DTO dto = CreateUserResponse.DTO.builder()
                .username(request.getUsername())
                .build();

        if (accessRepository.findByUsername(request.getUsername()).isEmpty()) {
            for (String menu : accessMenu) {
                Access access = new Access();
                access.setUsername(request.getUsername());
                access.setRoleName(request.getRole());
                access.setMenuAccess(menu);
                accessRepository.save(access);
            }
            User user = new User();
            user.setUsername(request.getUsername());
            user.setStatus(true);
            userRepository.save(user);
            return CreateUserResponse.buildResponse(dto, ResponseCode.SUCCESS);
        }
        return CreateUserResponse.buildResponse(dto, ResponseCode.ACCOUNT_ALREADY_EXIST);
    }

    @Override
    public BlockUserResponse blockUser(BlockUserRequest request) {
        BlockUserResponse.DTO dto = BlockUserResponse.DTO.builder()
                .username(request.getUsername())
                .status(request.isStatus())
                .build();

        User user = userRepository.findByUsername(request.getUsername());
        if (user == null ) {
            dto.setStatus(false);
            return BlockUserResponse.buildResponse(dto, ResponseCode.USERNAME_NOTFOUND);
        }
        if (!user.isStatus()){
            dto.setStatus(false);
            return BlockUserResponse.buildResponse(dto, ResponseCode.USERNAME_ALREADY_DEACTIVE);
        }
        user.setUsername(request.getUsername());
        user.setStatus(false);
        userRepository.save(user);
        return BlockUserResponse.buildResponse(dto, ResponseCode.SUCCESS);
    }

    @Override
    public CreateAccessResponse createAccess(CreateAccessRequest request) {
        CreateAccessResponse.DTO dto = CreateAccessResponse.DTO.builder()
                .username(request.getUsername())
                .menuAccess(request.getMenuAccess())
                .roleName(request.getRoleName())
                .build();
        Access access = accessRepository.findByUsernameAndMenuAccess(request.getUsername(), request.getMenuAccess());
        if (access == null) {
            return CreateAccessResponse.buildResponse(null, ResponseCode.ACCESS_NOTFOUND);
        }
        access.setUsername(request.getUsername());
        access.setRoleName(request.getRoleName());
        access.setMenuAccess(request.getMenuAccess());
        accessRepository.save(access);
        return CreateAccessResponse.buildResponse(dto, ResponseCode.SUCCESS);
    }

    public GetUserResponse getUser(String username){
        GetUserResponse.DTO dto = GetUserResponse.DTO.builder()
                .username(username)
                .status(false)
                .build();
        User user = userRepository.findByUsername(username);
        if (user == null ) {
            return GetUserResponse.buildResponse(dto, ResponseCode.USERNAME_NOTFOUND);
        }
        dto.setStatus(user.isStatus());
        return GetUserResponse.buildResponse(dto, ResponseCode.SUCCESS);
    }

    public GetUserAccessListResponse getUserAccess(String username) {
        List<Access> accessList = accessRepository.findByUsername(username);
        if (accessList == null || accessList.isEmpty()) {
            return GetUserAccessListResponse.buildResponse(Collections.emptyList(), ResponseCode.USER_ACCESS_NOTFOUND);
        }
        List<GetUserAccessResponse> userAccessResponses = accessList.stream()
                .map(access -> new GetUserAccessResponse(
                        access.getUsername(),
                        access.getMenuAccess(),
                        access.getRoleName(),
                        access.getCreatedTime(),
                        access.getUpdatedTime()
                ))
                .toList();
        return GetUserAccessListResponse.buildResponse(userAccessResponses, ResponseCode.SUCCESS);
    }
}
