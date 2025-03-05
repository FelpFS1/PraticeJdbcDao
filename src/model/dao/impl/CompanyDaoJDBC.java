package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.CompanyDao;
import model.entities.Company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompanyDaoJDBC implements CompanyDao {
    private Connection conn;
    PreparedStatement preparedStatement = null;

    public CompanyDaoJDBC(Connection conn){
        this.conn = conn;
    }

    @Override
    public void insert(Company company) {
        try {
            preparedStatement = conn.prepareStatement("INSERT INTO company " +
                    "(company_name) " +
                    "VALUES (?)",PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,company.getName());
            
            int rowAffected = preparedStatement.executeUpdate();
            if(rowAffected > 0){
                ResultSet rs = preparedStatement.getGeneratedKeys();
                while (rs.next()){
                    int id = rs.getInt(1);
                    company.setId(id);
                }
                System.out.println("Done! Company created!");

            }

        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(preparedStatement);
        }

    }

    @Override
    public void update(Company company) {
        try {
            preparedStatement = conn.prepareStatement("UPDATE company SET " +
                    "company_name = ? WHERE company_id = ?",PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1,company.getName());
            preparedStatement.setInt(2,company.getId());
            int rowsAffected = preparedStatement.executeUpdate();

            if(rowsAffected > 0){
                company.setId(company.getId());
                company.setName(company.getName());
            }

        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(preparedStatement);
        }

    }

    @Override
    public void deleteById(Integer id) {
        try{
            preparedStatement = conn.prepareStatement("DELETE FROM company " +
                    "WHERE company_id = ?");
            preparedStatement.setInt(1,id);
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(preparedStatement);
        }
    }

    @Override
    public Company findById(Integer id) {

        try {
            preparedStatement = conn.prepareStatement("SELECT * FROM company " +
                    "WHERE company_id = ?");
            preparedStatement.setInt(1,id);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()){
                Company company = new Company();
                company.setId(rs.getInt("company_id"));
                company.setName(rs.getString("company_name"));
                return company;
            }


        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(preparedStatement);
        }
        return null;
    }

    @Override
    public List<Company> findAll() {
        List<Company> companies = new ArrayList<>();

        try{
            preparedStatement= conn.prepareStatement("SELECT * FROM company");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                companies.add(new Company(resultSet.getInt("company_id"),resultSet.getString("company_name")));
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(preparedStatement);
        }
        return companies;
    }

}
