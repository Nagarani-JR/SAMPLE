package com.gft.employee.Dao;

import com.gft.employee.Common.ConnectionUtil;
import com.gft.employee.Common.ReturnStatus;
import com.gft.employee.Model.EmployeeModel;
import lombok.Cleanup;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmployeeDao {

    private static final Logger logger = Logger.getLogger(EmployeeDao.class.getName());

    public static ReturnStatus saveEmployee(EmployeeModel employeeModel)  {
        try(Connection con = ConnectionUtil.getConnection()) {
            String insertEmployeeQuery = "INSERT INTO EMPLOYEE (EMP_ID,EMP_NAME,EMP_ADDRESS,EMP_DESIGNATION,EMP_SALARY) Values (?,?,?,?,?)";
            @Cleanup PreparedStatement insertPs = con.prepareStatement(insertEmployeeQuery);
            insertPs.setInt(1, employeeModel.getEmpid());
            insertPs.setString(2, employeeModel.getEmpname());
            insertPs.setString(3, employeeModel.getAddress());
            insertPs.setString(4, employeeModel.getDesignation());
            insertPs.setDouble(5, employeeModel.getSalary());
            insertPs.executeUpdate();
            return new ReturnStatus(true, "Employee Master Saved Successfully");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Exception Occured While Creating a Employee", e);
            return new ReturnStatus(false , e.getMessage());
        }
    }


    public static ReturnStatus getEmployee()  {
        try (Connection con = ConnectionUtil.getConnection()) {
            String getEmployeeQuery = "SELECT EMP_ID,EMP_NAME,EMP_ADDRESS,EMP_DESIGNATION,EMP_SALARY FROM EMPLOYEE";
            @Cleanup PreparedStatement getEmployeePs = con.prepareStatement(getEmployeeQuery);
            @Cleanup ResultSet getEmployeeRs = getEmployeePs.executeQuery();
            List<EmployeeModel> employeeList = new ArrayList<>();
            while (getEmployeeRs.next()) {
                EmployeeModel employeeModel = new EmployeeModel();
                employeeModel.setEmpid(getEmployeeRs.getInt("EMP_ID"));
                employeeModel.setEmpname(getEmployeeRs.getString("EMP_NAME"));
                employeeModel.setAddress(getEmployeeRs.getString("EMP_ADDRESS"));
                employeeModel.setDesignation(getEmployeeRs.getString("EMP_DESIGNATION"));
                employeeModel.setSalary(getEmployeeRs.getDouble("EMP_SALARY"));
                employeeList.add(employeeModel);
            }

            if (employeeList.size() <= 0) {
                return new ReturnStatus(false, "Employee Details Not Found");
            }

            return new ReturnStatus(true, employeeList);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Exception Occured While Getting All Employees", e);
            return new ReturnStatus(false , e.getMessage());
        }
    }

    public static ReturnStatus EditEmployee(int employeeId)  {

        try (Connection con = ConnectionUtil.getConnection()) {
            String editEmployeeQuery = "SELECT EMP_ID,EMP_NAME,EMP_DESIGNATION,EMP_SALARY FROM EMPLOYEE EMP_ID =?";
            @Cleanup PreparedStatement editEmployeePs = con.prepareStatement(editEmployeeQuery);
            editEmployeePs.setInt(1, employeeId);
            @Cleanup  ResultSet editEmployeeRs = editEmployeePs.executeQuery();
            List<EmployeeModel> employeeList = new ArrayList<>();
            while (editEmployeeRs.next()) {
                EmployeeModel employeeModel = new EmployeeModel();
                employeeModel.setEmpid(editEmployeeRs.getInt("EMP_ID"));
                employeeModel.setEmpname(editEmployeeRs.getString("EMP_NAME"));
                employeeModel.setAddress(editEmployeeRs.getString("EMP_ADDRESS"));
                employeeModel.setDesignation(editEmployeeRs.getString("EMP_DESIGNATON"));
                employeeModel.setSalary(editEmployeeRs.getDouble("EMP_SALARY"));
                employeeList.add(employeeModel);
            }

            if (employeeList.size() <= 0) {
                return new ReturnStatus(false, "Employee Details Not Found");
            }

            return new ReturnStatus(true, employeeList);

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Exception Occured While Getting a Employee For Edit", e);
            return new ReturnStatus(false , e.getMessage());
        }
    }

    public static ReturnStatus updateEmployee(EmployeeModel employeeModel)  {
        try(Connection con = ConnectionUtil.getConnection()) {
            String updateEmployeeQuery = "UPDATE EMPLOYEE SET EMP_NAME = ?, EMP_ADDRESS = ?, EMP_DESIGNATION = ?, EMP_SALARY = ? WHERE EMP_ID = ? ";
            @Cleanup PreparedStatement updateEmployeePs = con.prepareStatement(updateEmployeeQuery);
            updateEmployeePs.setString(1, employeeModel.getEmpname());
            updateEmployeePs.setString(2, employeeModel.getAddress());
            updateEmployeePs.setDouble(3, employeeModel.getSalary());
            updateEmployeePs.setInt(4, employeeModel.getEmpid());
            updateEmployeePs.executeUpdate();
            return new ReturnStatus(true, "Updated Successfully For Employee" + employeeModel.getEmpname());
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Exception Occured While Updating a Employee", e);
            return  new ReturnStatus(false , e.getMessage());
        }
    }

}

