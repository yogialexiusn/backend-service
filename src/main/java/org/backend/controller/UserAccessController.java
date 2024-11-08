package org.backend.controller;

import org.backend.request.BlockUserRequest;
import org.backend.request.CreateAccessRequest;
import org.backend.request.CreateUserRequest;
import org.backend.response.GetUserAccessListResponse;
import org.backend.response.embedded.*;
import org.backend.service.IUserAcess;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
@Validated
public class UserAccessController extends BaseController{

    private final IUserAcess iUserAcess;

    public UserAccessController(IUserAcess iUserAcess) {
        this.iUserAcess = iUserAcess;
    }

    @GetMapping("/{username}")
    public ResponseEntity<GetUserResponse> getUser(@PathVariable String username) {
        return execute(iUserAcess.getUser(username));
    }

    @PostMapping
    @CrossOrigin(origins = "*")
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody @Valid CreateUserRequest request) {
        return execute(iUserAcess.createUser(request));
    }

    @PostMapping("/blockUser")
    public ResponseEntity<BlockUserResponse> blockUser(@RequestBody BlockUserRequest request) {
        return execute(iUserAcess.blockUser(request));
    }

    @GetMapping("/access/{username}")
    public ResponseEntity<GetUserAccessListResponse> getUserAccess(@PathVariable String username) {
        return execute(iUserAcess.getUserAccess(username));
    }

    @PostMapping("/access")
    public ResponseEntity<CreateAccessResponse> addAccess(@RequestBody @Valid CreateAccessRequest request) {
        return execute(iUserAcess.createAccess(request));
    }

}
