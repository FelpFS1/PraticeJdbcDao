import db.DB;
import model.dao.CompanyDao;
import model.dao.DaoFactory;
import model.entities.Company;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        CompanyDao companyDao = DaoFactory.createCompanyDao(DB.getConnection());

        /* <------ INSERT Company --------> */
//        Company company = new Company(null,"Magazine");
//        companyDao.insert(company);

        /* <------ UPDATE Company --------> */
//        Company company2 = new Company(8,"BYD");
//        companyDao.update(company2);

        /* <------ FINDBYID Company --------> */

//        Company findCompany = companyDao.findById(company.getId());
//        System.out.println(findCompany);

        /* <------ FINDALL Company --------> */

        List<Company> companyList = companyDao.findAll();
        companyList.forEach(System.out::println);
        System.out.println("--------------------------------------------------------");

        /* <------ DELETE Company --------> */
        companyDao.deleteById(10);
        List<Company> compListOneDeleted = companyDao.findAll();
        compListOneDeleted.forEach(System.out::println);
    }
}