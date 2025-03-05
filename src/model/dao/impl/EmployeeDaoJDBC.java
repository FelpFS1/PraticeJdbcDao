package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.EmployeeDao;
import model.entities.Company;
import model.entities.Employee;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EmployeeDaoJDBC implements EmployeeDao {
    private Connection conn;
    private PreparedStatement preparedStatement;
    public EmployeeDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Employee employee) {
        try{
            preparedStatement = conn.prepareStatement("INSERT INTO employee " +
                    "(employee_name,employee_age,employee_salary,company_id) " +
                    "VALUES (?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,employee.getName());
            preparedStatement.setInt(2,employee.getAge());
            preparedStatement.setDouble(3,employee.getSalary());
            preparedStatement.setInt(4,employee.getCompany().getId());
            int rowsAffected = preparedStatement.executeUpdate();

            if(rowsAffected > 0){
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if(resultSet.next()){
                    employee.setId(resultSet.getInt(1));
                }
            }

        }catch (SQLException e){
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(preparedStatement);
        }

    }

    @Override
    public void update(Employee employee) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Employee findById(Integer id) {
        ResultSet resultSet = null;
        try {
            preparedStatement = conn.prepareStatement("SELECT employee.*,company.company_name as compName " +
                    "FROM employee INNER JOIN company " +
                    "ON employee.company_id = company.company_id " +
                    "WHERE employee.employee_id = ?");
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                Company company = new Company(resultSet.getInt("company_id"),resultSet.getString("compName"));

                Employee employee = new Employee(resultSet.getString("employee_name"),
                        id,resultSet.getInt("employee_age"),company,resultSet.getDouble("employee_salary"));
                return  employee;
            }

        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(preparedStatement);
        }
        return null;
    }

    @Override
    public List<Employee> findAll() {
        return List.of();
    }
}
