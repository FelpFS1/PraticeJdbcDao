package model.dao;

import model.dao.impl.CompanyDaoJDBC;

public class DaoFactory {
    public static CompanyDao createCompanyDao(){
        return new CompanyDaoJDBC();
    }

}
