package com.company.LibraryProject.service.validation;

import com.company.LibraryProject.dto.ErrorDto;
import com.company.LibraryProject.dto.OrdersDto;
import com.company.LibraryProject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class OrdersValidate {
    private final UserService userService;
    public List<ErrorDto> validate(OrdersDto ordersDto) {
        List<ErrorDto>errorDtoList=new ArrayList<>();
        if (userService.getUser(ordersDto.getUserId()).getData()==null){
            errorDtoList.add(new ErrorDto("User",String.format("This is User %d id not found",ordersDto.getUserId())));
        }
        return errorDtoList;
    }
}
