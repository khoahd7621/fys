package com.khoahd7621.youngblack.services;

import com.khoahd7621.youngblack.constants.EAccountStatus;
import com.khoahd7621.youngblack.constants.ERoles;
import com.khoahd7621.youngblack.dtos.response.SuccessResponse;
import com.khoahd7621.youngblack.dtos.response.user.ListUsersWithPaginateResponse;

public interface UserAdminService {

    public SuccessResponse<ListUsersWithPaginateResponse> getListUsersByRoleAndStatusWithPaginate(ERoles role, EAccountStatus status, Integer limit, Integer offset);
}
