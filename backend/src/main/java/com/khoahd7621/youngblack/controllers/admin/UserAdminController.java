package com.khoahd7621.youngblack.controllers.admin;

import com.khoahd7621.youngblack.dtos.response.SuccessResponse;
import com.khoahd7621.youngblack.dtos.response.user.ListUsersWithPaginateResponse;
import com.khoahd7621.youngblack.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/admin/user")
public class UserAdminController {

    @Autowired
    private UserService userService;

    @GetMapping
    public SuccessResponse<ListUsersWithPaginateResponse> getListUsers(
            @RequestParam(name = "limit", defaultValue = "20") Integer limit,
            @RequestParam(name = "offset", defaultValue = "0") Integer offset
    ) {
        return userService.getListUsers(limit, offset);
    }
}
