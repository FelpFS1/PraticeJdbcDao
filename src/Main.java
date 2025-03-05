import db.DB;
import model.dao.CompanyDao;
import model.dao.DaoFactory;
import model.entities.Company;

public class Main {
    public static void main(String[] args) {
        CompanyDao companyDao = DaoFactory.createCompanyDao(DB.getConnection());

        /* <------ INSERT Company --------> */
        Company company = new Company(null,"FORD");
        companyDao.insert(company);

        /* <------ UPDATE Company --------> */
        Company company2 = new Company(8,"BYD");
        companyDao.update(company2);

        /* <------ FINDBYID Company --------> */

        Company findCompany = companyDao.findById(company.getId());
        System.out.println(findCompany);
    }
}