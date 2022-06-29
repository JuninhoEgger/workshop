package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentService {
	
	private DepartmentDao dao = DaoFactory.createDepartmentDao();
	
	public int getSizeOfDepartmentList() {
		List<Department> list = dao.findAll();
		return list.size();
	}
	
	public List<Department> findAll() {
		return dao.findAll();
	}
	
	public void saveOrUpdate(Department department) {
		if (department.getId() == null) {
			dao.insert(department);
		} else {
			dao.update(department);
		}
	}
	
	public void remove(Department department) {
		dao.deleteById(department.getId());
	}
	
}
