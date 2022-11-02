package com.khoahd7621.youngblack.controllers.admin;

import com.khoahd7621.youngblack.constants.EAccountStatus;
import com.khoahd7621.youngblack.constants.ERoles;
import com.khoahd7621.youngblack.dtos.response.NoData;
import com.khoahd7621.youngblack.dtos.response.SuccessResponse;
import com.khoahd7621.youngblack.dtos.response.user.ListUsersWithPaginateResponse;
import com.khoahd7621.youngblack.exceptions.custom.BadRequestException;
import com.khoahd7621.youngblack.exceptions.custom.NotFoundException;
import com.khoahd7621.youngblack.services.UserAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/admin/user")
public class UserAdminController {

    @Autowired
    private UserAdminService userAdminService;

    @GetMapping
    public SuccessResponse<ListUsersWithPaginateResponse> getListUsersByRoleAndStatusWithPaginate(
            @RequestParam(name = "role") ERoles role,
            @RequestParam(name = "status") EAccountStatus status,
            @RequestParam(name = "limit", defaultValue = "20") Integer limit,
            @RequestParam(name = "offset", defaultValue = "0") Integer offset
    ) {
        return userAdminService.getListUsersByRoleAndStatusWithPaginate(role, status, limit, offset);
    }

    @PutMapping("/{userId}/block")
    public SuccessResponse<NoData> blockUserByUserId(@PathVariable Long userId) throws NotFoundException, BadRequestException {
        return userAdminService.blockUserByUserId(userId);
    }

    @PutMapping("/{userId}/un-block")
    public SuccessResponse<NoData> unBlockUserByUserId(@PathVariable Long userId) throws NotFoundException, BadRequestException {
        return userAdminService.unBlockUserByUserId(userId);
    }
}
