package model.dao;

import model.entities.Company;
import java.util.List;

public interface CompanyDao {
    void insert(Company company);
    void update(Company company);
    void deleteById(Integer id);
    Company findById(Integer id);
    List<Company> findAll();

}
