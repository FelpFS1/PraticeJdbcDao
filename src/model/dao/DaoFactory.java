package model.dao;

import model.dao.impl.CompanyDaoJDBC;

import java.sql.Connection;

public class DaoFactory {
    public static CompanyDao createCompanyDao(Connection conn){
        return new CompanyDaoJDBC(conn);
    }
}
