package com.gft.employee.Resource;

import com.gft.employee.Common.ReturnStatus;
import com.gft.employee.Dao.EmployeeDao;
import com.gft.employee.Model.EmployeeModel;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@Path("/employee")
public class EmployeeResource {

    @POST
    @Path("/create-employee")
    @Produces(MediaType.APPLICATION_JSON)
    public ReturnStatus createEmployee(EmployeeModel employeeModel){
        return EmployeeDao.saveEmployee(employeeModel);
    }

    @GET
    @Path("/get-employee-list")
    @Produces(MediaType.APPLICATION_JSON)
    public ReturnStatus getAllEmployee() {
        return EmployeeDao.getEmployee();
    }

    @GET
    @Path("/edit-employee")
    @Produces(MediaType.APPLICATION_JSON)
    public ReturnStatus getEmployee_ById(@QueryParam("emp_id") int empId){
        return EmployeeDao.EditEmployee(empId);
    }

    @POST
    @Path("/update-employee")
    @Produces(MediaType.APPLICATION_JSON)
    public ReturnStatus updateEmployee_ById(EmployeeModel employeeModel){
        return EmployeeDao.updateEmployee(employeeModel);
    }


}
