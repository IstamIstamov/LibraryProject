package com.company.LibraryProject.service.validation;

import com.company.LibraryProject.dto.ErrorDto;
import com.company.LibraryProject.dto.OrdersBookDto;
import com.company.LibraryProject.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class OrdersBookValidate {
    private final OrdersService ordersService;
    public List<ErrorDto> validate(OrdersBookDto dto) {
        List<ErrorDto>errorDtoList=new ArrayList<>();
        if (ordersService.get(dto.getOrdersId()).getData()==null){
            errorDtoList.add(new ErrorDto("Orders",String.format("This is orders %d id not found!",dto.getOrdersId())));
        }
        return errorDtoList;
    }
}
