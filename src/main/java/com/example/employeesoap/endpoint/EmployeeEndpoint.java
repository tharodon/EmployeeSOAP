package com.example.employeesoap.endpoint;

import com.example.employeesoap.entity.Employee;
import com.example.employeesoap.service.EmployeeService;
import com.example.employeesoap.service.MapperService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import io.spring.guides.gs_producing_web_service.EmployeeDto;
import io.spring.guides.gs_producing_web_service.DeleteEmployeeRequest;
import io.spring.guides.gs_producing_web_service.PatchEmployeeRequest;
import io.spring.guides.gs_producing_web_service.GetEmployeeRequest;
import io.spring.guides.gs_producing_web_service.AddEmployeeRequest;
import io.spring.guides.gs_producing_web_service.EmployeeResponse;
@Endpoint
@RequiredArgsConstructor
public class EmployeeEndpoint {

    private final MapperService mapperService;
    private final EmployeeService employeeService;
    private static final String NAME_SPACE = "http://spring.io/guides/gs-producing-web-service"; //todo вкусовщина, но приятно видеть консты выше)) + почему такой текст ?

    @PayloadRoot(namespace = NAME_SPACE, localPart = "addEmployeeRequest")
    @ResponsePayload
    public EmployeeResponse addEmployee(@RequestPayload AddEmployeeRequest request){
        Employee employee = employeeService.save(mapperService.convertFromDto(request.getEmployeeDto()));
        return mapperService.getResponse(employee);
    }

    @PayloadRoot(namespace = NAME_SPACE, localPart = "patchEmployeeRequest")
    @ResponsePayload
    public EmployeeResponse patchEmployee(@RequestPayload PatchEmployeeRequest request){
        Employee employee = employeeService.update(mapperService.convertFromDto(request.getEmployeeDto()));
        return mapperService.getResponse(employee);
    }

    @PayloadRoot(namespace = NAME_SPACE, localPart = "deleteEmployeeRequest")
    @ResponsePayload
    public void deleteEmployee(@RequestPayload DeleteEmployeeRequest request){
        employeeService.delete(request.getId());
    }

    @PayloadRoot(namespace = NAME_SPACE, localPart = "getEmployeeRequest")
    @ResponsePayload
    public EmployeeResponse getEmployee(@RequestPayload GetEmployeeRequest request){
        Employee employee = employeeService.findEmployeeById(request.getId());
        return mapperService.getResponse(employee);
    }
}
