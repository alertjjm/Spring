package com.example.restapiservice;

import com.example.restapiservice.Employee.Employee;
import com.example.restapiservice.Employee.EmployeeController;
import com.example.restapiservice.Employee.EmployeeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private EmployeeRepository repository;
    @Test
    public void newEmployeeTest() throws Exception{
        Employee newemployee=new Employee(5L,"conan","dale", "test");
        String param = objectMapper.writeValueAsString(newemployee);
        when(repository.save(newemployee)).thenReturn(newemployee);
        mockMvc.perform(post("/employees")
        .content(param)
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"id\":5,\"name\":\"dale\",\"role\":\"test\"}"))
                .andDo(print());
    }
    @Test
    public void allTest() throws Exception {
        mockMvc.perform(get("/employees")).andExpect(status().isOk()).andDo(print());
    }
    @Test
    public void oneTest() throws Exception {
        mockMvc.perform(get("/employees/1")).
                andExpect(status().isOk()).andDo(print());
    }
}
