package com.gft.employee.Common;

import com.gft.employee.Model.EmployeeModel;

import java.util.List;

public class ReturnStatus {

    private boolean status = true;
    private String description = "";
    private Object returnObject;

    public ReturnStatus(boolean status,String description , Object returnObject) {
        this.status = status;
        this.description = description;
        this.returnObject = returnObject;
    }

    public ReturnStatus(boolean status,String description) {
        this.status = status;
        this.description = description;
    }


    public ReturnStatus(boolean status, List<EmployeeModel> employeeModellist) {
        this.status = status;
        this.returnObject = employeeModellist;


        
    }
}
