package com.softwarica.employeeapi.api;

import com.softwarica.employeeapi.model.Employee;
import com.softwarica.employeeapi.model.EmployeeCUD;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface EmployeeAPI {
    //    get all the employees
    @GET("employees")
    Call<List<Employee>> getAllEmployees();

    @POST("create")
    Call<Void> registerEmployee(@Body EmployeeCUD emp);

    //    get employee on basis of id
    @GET("employee/{empID}")
    Call<Employee> getEmployeeByID(@Path("empID") int empId);



}
