package com.khoahd7621.youngblack.services.impl;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import com.khoahd7621.youngblack.constants.EAccountStatus;
import com.khoahd7621.youngblack.dtos.request.user.CreateNewAdminUserRequest;
import com.khoahd7621.youngblack.dtos.response.NoData;
import com.khoahd7621.youngblack.dtos.response.SuccessResponse;
import com.khoahd7621.youngblack.entities.User;
import com.khoahd7621.youngblack.exceptions.BadRequestException;
import com.khoahd7621.youngblack.exceptions.NotFoundException;
import com.khoahd7621.youngblack.mappers.UserMapper;
import com.khoahd7621.youngblack.repositories.UserRepository;
import com.khoahd7621.youngblack.utils.PageableUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

class UserAdminServiceImplTest {

    private UserAdminServiceImpl userAdminServiceImpl;
    private UserRepository userRepository;
    private UserMapper userMapper;
    private PageableUtil pageableUtil;

    private User user;

    @BeforeEach
    void beforeEach() {
        userRepository = mock(UserRepository.class);
        userMapper = mock(UserMapper.class);
        pageableUtil = mock(PageableUtil.class);
        userAdminServiceImpl = UserAdminServiceImpl.builder()
                .userRepository(userRepository)
                .userMapper(userMapper)
                .pageableUtil(pageableUtil).build();
        user = mock(User.class);
    }

    @Test
    void getListUsersByRoleAndStatusWithPaginate_ShouldReturnData() {
        // Todo
    }

    @Test
    void blockUserByUserId_ShouldThrowNotFoundException_WhenInvalidUserIdInDataRequest() {
        long userIdRequest = 1L;

        when(userRepository.findById(userIdRequest)).thenReturn(Optional.empty());

        NotFoundException actual = assertThrows(NotFoundException.class, () -> {
            userAdminServiceImpl.blockUserByUserId(userIdRequest);
        });

        assertThat(actual.getMessage(), is("Don't exist user with this id."));
    }

    @Test
    void blockUserByUserId_ShouldThrowBadRequestException_WhenUserFoundByUserIdRequestAlreadyBlocked() {
        long userIdRequest = 1L;
        Optional<User> userOptional = Optional.of(user);

        when(userRepository.findById(userIdRequest)).thenReturn(userOptional);
        when(userOptional.get().getStatus()).thenReturn(EAccountStatus.BLOCK);

        BadRequestException actual = assertThrows(BadRequestException.class, () -> {
            userAdminServiceImpl.blockUserByUserId(userIdRequest);
        });

        assertThat(actual.getMessage(), is("You cannot block user has been blocked."));
    }

    @Test
    void blockUserByUserId_ShouldReturnData_WhenValidDataRequest() throws NotFoundException, BadRequestException {
        long userIdRequest = 1L;
        Optional<User> userOptional = Optional.of(user);
        SuccessResponse<NoData> expected =
                new SuccessResponse<>(NoData.builder().build(), "Block user success!");

        when(userRepository.findById(userIdRequest)).thenReturn(userOptional);
        when(userOptional.get().getStatus()).thenReturn(EAccountStatus.ACTIVE);

        SuccessResponse<NoData> actual = userAdminServiceImpl.blockUserByUserId(userIdRequest);

        verify(user).setStatus(EAccountStatus.BLOCK);
        verify(userRepository).save(user);
        assertThat(actual.getCode(), is(expected.getCode()));
        assertThat(actual.getData().getNoData(), is(expected.getData().getNoData()));
        assertThat(actual.getMessage(), is(expected.getMessage()));
    }

    @Test
    void unBlockUserByUserId_ShouldThrowNotFoundException_WhenInvalidUserIdInDataRequest() {
        long userIdRequest = 1L;

        when(userRepository.findById(userIdRequest)).thenReturn(Optional.empty());

        NotFoundException actual = assertThrows(NotFoundException.class, () -> {
            userAdminServiceImpl.unBlockUserByUserId(userIdRequest);
        });

        assertThat(actual.getMessage(), is("Don't exist user with this id."));
    }

    @Test
    void unBlockUserByUserId_ShouldThrowBadRequestException_WhenUserFoundByUserIdRequestAlreadyUnBlocked() {
        long userIdRequest = 1L;
        Optional<User> userOptional = Optional.of(user);

        when(userRepository.findById(userIdRequest)).thenReturn(userOptional);
        when(userOptional.get().getStatus()).thenReturn(EAccountStatus.ACTIVE);

        BadRequestException actual = assertThrows(BadRequestException.class, () -> {
            userAdminServiceImpl.unBlockUserByUserId(userIdRequest);
        });

        assertThat(actual.getMessage(), is("You cannot un-block user has been activated."));
    }

    @Test
    void unBlockUserByUserId_ShouldReturnData_WhenValidDataRequest() throws NotFoundException, BadRequestException {
        long userIdRequest = 1L;
        Optional<User> userOptional = Optional.of(user);
        SuccessResponse<NoData> expected =
                new SuccessResponse<>(NoData.builder().build(), "Un block user success!");

        when(userRepository.findById(userIdRequest)).thenReturn(userOptional);
        when(userOptional.get().getStatus()).thenReturn(EAccountStatus.BLOCK);

        SuccessResponse<NoData> actual = userAdminServiceImpl.unBlockUserByUserId(userIdRequest);

        verify(user).setStatus(EAccountStatus.ACTIVE);
        verify(userRepository).save(user);
        assertThat(actual.getCode(), is(expected.getCode()));
        assertThat(actual.getData().getNoData(), is(expected.getData().getNoData()));
        assertThat(actual.getMessage(), is(expected.getMessage()));
    }

    @Test
    void createNewAdminUser_ShouldThrowBadRequestException_WhenConfirmPasswordNotMatchPasswordInDataRequest() {
        CreateNewAdminUserRequest requestData = CreateNewAdminUserRequest.builder()
                .password("123456").confirmPassword("12345678").build();

        BadRequestException actual = assertThrows(BadRequestException.class, () -> {
            userAdminServiceImpl.createNewAdminUser(requestData);
        });

        assertThat(actual.getMessage(), is("Confirm password not match new password."));
    }

    @Test
    void createNewAdminUser_ShouldThrowBadRequestException_WhenEmailDuplicatedInDataRequest() {
        CreateNewAdminUserRequest requestData = CreateNewAdminUserRequest.builder()
                .email("email@gmail.com").password("123456").confirmPassword("123456").build();

        when(userRepository.findByEmail(requestData.getEmail())).thenReturn(Optional.of(user));

        BadRequestException actual = assertThrows(BadRequestException.class, () -> {
            userAdminServiceImpl.createNewAdminUser(requestData);
        });

        assertThat(actual.getMessage(), is("This email already existed."));
    }

    @Test
    void createNewAdminUser_ShouldThrowBadRequestException_WhenPhoneDuplicatedInDataRequest() {
        CreateNewAdminUserRequest requestData = CreateNewAdminUserRequest.builder()
                .email("email@gmail.com").phone("0792596123")
                .password("123456").confirmPassword("123456").build();

        when(userRepository.findByEmail(requestData.getEmail())).thenReturn(Optional.empty());
        when(userRepository.findByPhone(requestData.getPhone())).thenReturn(Optional.of(user));

        BadRequestException actual = assertThrows(BadRequestException.class, () -> {
            userAdminServiceImpl.createNewAdminUser(requestData);
        });

        assertThat(actual.getMessage(), is("This phone number already existed."));
    }

    @Test
    void createNewAdminUser_ShouldReturnData_WhenValidDataRequest() throws BadRequestException {
        CreateNewAdminUserRequest requestData = CreateNewAdminUserRequest.builder()
                .firstName("firstName").lastName("lastName")
                .email("email@gmail.com").phone("0792596123")
                .password("123456").confirmPassword("123456").build();
        SuccessResponse<NoData> expected =
                new SuccessResponse<>(NoData.builder().build(), "Create new admin user successfully.");

        when(userRepository.findByEmail(requestData.getEmail())).thenReturn(Optional.empty());
        when(userRepository.findByPhone(requestData.getEmail())).thenReturn(Optional.empty());
        when(userMapper.toUser(requestData)).thenReturn(user);

        SuccessResponse<NoData> actual = userAdminServiceImpl.createNewAdminUser(requestData);

        verify(userRepository).save(user);
        assertThat(actual.getCode(), is(expected.getCode()));
        assertThat(actual.getData().getNoData(), is(expected.getData().getNoData()));
        assertThat(actual.getMessage(), is(expected.getMessage()));
    }
}