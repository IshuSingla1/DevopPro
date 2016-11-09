package com.xebia.service;

import com.xebia.controller.EmployeeController;
import com.xebia.domain.Employee;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.servlet.ServletResponseWrapper;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class EmployeeControllerTest {

    private MockMvc mvc;

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }

    @Test
    public void getGreetingReturnsEmployees() throws Exception{
        String uri = "/api/employees";

        when(employeeService.findAll()).thenReturn(getStubbedEmployees());

        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON)).andReturn();
        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();
        Assert.assertEquals(200, status);
        Assert.assertEquals("[{\"id\":null,\"name\":\"Manish\"},{\"id\":null,\"name\":\"Thakur\"}]", content);

    }

    @Test
    public void createEmployee() throws Exception {

        String uri = "/api/employee";
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post(uri).
                contentType(MediaType.APPLICATION_JSON).content("{\"name\":\"Jayati\"}")).andReturn();
        ArgumentCaptor<Employee> argument = ArgumentCaptor.forClass(Employee.class);
        verify(employeeService).save(argument.capture());
        Assert.assertEquals("Jayati", argument.getValue().getName());
        int status = result.getResponse().getStatus();
        Assert.assertEquals(201, status);
    }

    private List<Employee> getStubbedEmployees() {
        List<Employee> stubbedEmployees = new ArrayList<Employee>();
        stubbedEmployees.add(new Employee("Manish"));
        stubbedEmployees.add(new Employee("Thakur"));
        return stubbedEmployees;
    }
}
