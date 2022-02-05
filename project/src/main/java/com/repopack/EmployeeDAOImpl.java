package com.repopack;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.model.Accounts;

@Repository
public class EmployeeDAOImpl extends AbstractDAO<Integer, Accounts> implements AccountDAO {

	@Override
	public List<Accounts> findAllEmployees() {
		Criteria criteria = createEntityCriteria();
        return (List<Accounts>) criteria.list();
	}
	@Override
	public void saveEmployee(Accounts accounts) {
		persist(accounts);
	}
	
	@Override
	public Accounts findAccountByName(String username) {
		Criteria criteria=createEntityCriteria();
		criteria.add(Restrictions.eq("username",username));
		return (Accounts) criteria.uniqueResult();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Accounts> findAccountByPass(String password) {
		Criteria criteria=createEntityCriteria();
		criteria.add(Restrictions.eq("password",password));
		return (List<Accounts>) criteria.list();
	}
	
	@Override
	public Accounts findusernameAndpassword(String username, String password) {
		Criteria criteria=createEntityCriteria();
		criteria.add(Restrictions.eq("username", username));
		criteria.add(Restrictions.eq("password", password));
		return (Accounts) criteria.uniqueResult();
	}
}
