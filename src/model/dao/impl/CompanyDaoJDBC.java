package model.dao.impl;

import db.DbException;
import model.dao.CompanyDao;
import model.entities.Company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CompanyDaoJDBC implements CompanyDao {
    private Connection conn;

    public CompanyDaoJDBC(Connection conn){
        this.conn = conn;
    }

    @Override
    public void insert(Company company) {
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO company " +
                    "(company_name) " +
                    "VALUES (?)",PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1,company.getName());


            int rowAffected = ps.executeUpdate();
            if(rowAffected > 0){
                ResultSet rs = ps.getGeneratedKeys();
                while (rs.next()){
                    int id = rs.getInt(1);
                    company.setId(id);
                }
                System.out.println("Done! Company created!");

            }

        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
        }

    }

    @Override
    public void update(Company company) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Company findById(Integer id) {
        return null;
    }

    @Override
    public List<Company> findAll() {
        return List.of();
    }

}
