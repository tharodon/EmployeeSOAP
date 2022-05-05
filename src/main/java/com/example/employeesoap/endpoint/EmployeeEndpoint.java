package com.example.employeesoap.endpoint;

import com.example.employeesoap.entity.Employee;
import com.example.employeesoap.entity.EmployeeErrorResponse;
import com.example.employeesoap.exceptions.EmployeeNotFoundException;
import com.example.employeesoap.exceptions.InvalidPositionException;
import com.example.employeesoap.api.EmployeeService;
import com.example.employeesoap.mapper.EmployeeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import io.spring.guides.gs_producing_web_service.DeleteEmployeeRequest;
import io.spring.guides.gs_producing_web_service.PatchEmployeeRequest;
import io.spring.guides.gs_producing_web_service.GetEmployeeRequest;
import io.spring.guides.gs_producing_web_service.AddEmployeeRequest;
import io.spring.guides.gs_producing_web_service.EmployeeResponse;

@Endpoint
@RequiredArgsConstructor
public class EmployeeEndpoint {

    private static final String NAME_SPACE = "http://spring.io/guides/gs-producing-web-service";
    private final EmployeeMapper mapperService;
    private final EmployeeService employeeService;

    //todo общие замечание. делай по чаще reformat code // done

    @PayloadRoot(namespace = NAME_SPACE, localPart = "addEmployeeRequest")
    @ResponsePayload
    public EmployeeResponse addEmployee(@RequestPayload AddEmployeeRequest request) throws InvalidPositionException {
        Employee employee = employeeService.save(mapperService.fromEmployeeDto(request.getEmployeeDto()));
        return mapperService.getResponseFromEmployee(employee);
    }

    //todo название с update // done
    @PayloadRoot(namespace = NAME_SPACE, localPart = "patchEmployeeRequest")
    @ResponsePayload
    public EmployeeResponse updateEmployee(@RequestPayload PatchEmployeeRequest request) throws InvalidPositionException {
        Employee employee = employeeService.update(mapperService.fromEmployeeDto(request.getEmployeeDto()));
        return mapperService.getResponseFromEmployee(employee);
    }

    @PayloadRoot(namespace = NAME_SPACE, localPart = "deleteEmployeeRequest")
    @ResponsePayload
    public void deleteEmployee(@RequestPayload DeleteEmployeeRequest request) throws EmployeeNotFoundException {
        employeeService.delete(employeeService.findEmployeeById(request.getId()));
    }

    @PayloadRoot(namespace = NAME_SPACE, localPart = "getEmployeeRequest")
    @ResponsePayload
    public EmployeeResponse getEmployee(@RequestPayload GetEmployeeRequest request) throws EmployeeNotFoundException {
        Employee employee = employeeService.findEmployeeById(request.getId());
        return mapperService.getResponseFromEmployee(employee);
    }
}
