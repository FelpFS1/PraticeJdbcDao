import db.DB;
import model.dao.CompanyDao;
import model.dao.DaoFactory;
import model.entities.Company;

public class Main {
    public static void main(String[] args) {

        /* <------ INSERT Company --------> */
//        Company company = new Company(null,"BYD");
       CompanyDao companyDao = DaoFactory.createCompanyDao(DB.getConnection());
//        companyDao.insert(company);

        Company company2 = new Company(2,"BYD");
        companyDao.update(company2);


    }
}