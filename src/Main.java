import db.DB;
import model.dao.CompanyDao;
import model.dao.DaoFactory;
import model.dao.EmployeeDao;
import model.entities.Company;
import model.entities.Employee;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        CompanyDao companyDao = DaoFactory.createCompanyDao(DB.getConnection());
        EmployeeDao employeeDao = DaoFactory.createEmployeeDao(DB.getConnection());

        /* <------ INSERT Company --------> */
//        Company company = new Company(null,"Magazine lu");
//        companyDao.insert(company);

        /* <------ UPDATE Company --------> */
//        Company company2 = new Company(8,"BYD");
//        companyDao.update(company2);

        /* <------ FINDBYID Company --------> */

//          Company findCompany = companyDao.findById(11);
//        System.out.println(findCompany);

        /* <------ FINDALL Company --------> */

//        List<Company> companyList = companyDao.findAll();
//        companyList.forEach(System.out::println);
//        System.out.println("--------------------------------------------------------");
//
//        /* <------ DELETE Company --------> */
//        companyDao.deleteById(10);
//        List<Company> compListOneDeleted = companyDao.findAll();
//        compListOneDeleted.forEach(System.out::println);

        /* <------ INSERT Employee --------> */

//        Employee employee1 = new Employee("Matheus",null,42,findCompany,5000);
//        employeeDao.insert(employee1);

        /* <------ FIND Employee BY ID --------> */
//
//        Employee employee = employeeDao.findById(4);
//        System.out.println(employee);

        /* <------ DELETE Employee BY ID --------> */

//        employeeDao.deleteById(9);


        /* <------ UPDATE Employee  --------> */

//        employeeDao.update(new Employee("Viegas",4,75,findCompany,3000));
//
//        Employee updatedEmployee = employeeDao.findById(4);
//        System.out.println(updatedEmployee);

        /* <------ FINDALL Employee  --------> */

        List<Employee> employees = employeeDao.findAll();

        for (Employee employee : employees) {
            System.out.println(employee);
        }
   }
}