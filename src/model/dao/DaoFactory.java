package model.dao;

import model.dao.impl.CompanyDaoJDBC;
import model.dao.impl.EmployeeDaoJDBC;

import java.sql.Connection;

public class DaoFactory {
    public static CompanyDao createCompanyDao(Connection conn){
        return new CompanyDaoJDBC(conn);
    }

    public static EmployeeDao createEmployeeDao(Connection conn){
        return new EmployeeDaoJDBC(conn);
    }
}
