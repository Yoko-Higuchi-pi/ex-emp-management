package jp.co.sample.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Employee;

/**
 * employees テーブル操作を行うリポジトリ
 * 
 * @author yoko.higuchi
 *
 */

@Repository
public class EmployeeRepository {
	/** EmployeeのRowMapper */
	private static final RowMapper<Employee> EMPLOYEE_ROW_MAPPER = (rs, i) -> {
		Employee employee = new Employee();
		employee.setId(rs.getInt("id"));
		employee.setName(rs.getString("name"));
		employee.setImage(rs.getString("image"));
		employee.setGender(rs.getString("gender"));
		employee.setHireDate(rs.getDate("hire_date"));
		employee.setMailAddress(rs.getString("mail_address"));
		employee.setZipCode(rs.getString("zip_code"));
		employee.setAddress(rs.getString("address"));
		employee.setTelephone(rs.getString("telephone"));
		employee.setSalary(rs.getInt("salary"));
		employee.setCharacteristics(rs.getString("characteristics"));
		employee.setDependentsCount(rs.getInt("dependents_count"));
		
		return employee;
	};
	
	/** SQL実行変数 */
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	/**
	 * 全検索を行う
	 * @return 全件のリスト
	 */
	public List<Employee> findAll() {
		List<Employee> employeeList = new ArrayList<>();
		String sql = "SELECT id, name, image, gender, hire_date, mail_address, zip_code, address, telephone, salary, characteristics, dependents_count FROM employees ORDER BY hire_date;";
		try {
			employeeList = template.query(sql, EMPLOYEE_ROW_MAPPER);
			return employeeList;			
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * id をもとにDB検索を行う
	 * @param id 検索したい主キー(id)
	 * @return 検索結果
	 */
	public Employee load(Integer id) {
		Employee employee = new Employee();
		String sql = "SELECT id, name, image, gender, hire_date, mail_address, zip_code, address, telephone, salary, characteristics, dependents_count FROM employees WHERE id = :id;";
		
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		try {
			employee = template.queryForObject(sql, param, EMPLOYEE_ROW_MAPPER);
			return employee;
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * id に該当するデータを更新
	 * @param employee 更新するデータの情報
	 */
	public void update(Employee employee) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(employee);
		String sql = "UPDATE employees SET name=:name, image=:image, gender=:gender, hire_date=:hireDate, mail_address=:mailAddress, zip_code=:zipCode, address=:address, telephone=:telephone, salary=:salary, characteristics=:characteristics, dependents_count=:dependentsCount WHERE id = :id;";
		
		template.update(sql, param);
	}
}
