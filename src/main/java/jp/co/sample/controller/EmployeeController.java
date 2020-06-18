package jp.co.sample.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Employee;
import jp.co.sample.form.UpdateEmployeeForm;
import jp.co.sample.service.EmployeeService;

/**
 * 従業員　関連機能の処理の制御を行うコントローラ.
 * 
 * @author yoko.higuchi
 *
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	
	/**
	 * 従業員一覧を表示する HTMLを返すメソッド.
	 * 
	 * @param model リクエストスコープ
	 * @return 従業員一覧画面
	 */
	@RequestMapping("/showList")
	public String shorList(Model model) {
		List<Employee> employeeList = new ArrayList<>();
		employeeList = employeeService.showList();
		
		model.addAttribute("employeeList", employeeList);
		
		return "employee/list";
	}
	
	/**
	 * 従業員情報を表示する.
	 * 
	 * @param id 検索したい主キー(ID)
	 * @param model リクエストスコープ
	 * @return 詳細画面
	 */
	@RequestMapping("/showDetail")
	public String showDetail(String id, Model model) {
		Employee employee = employeeService.ShowDetail(Integer.parseInt(id));
		model.addAttribute("employee", employee);
		
		return "employee/detail";
	}
	
	/**
	 * 扶養人数を更新する.
	 * 
	 * @param form 主キー(ID)、更新する扶養人数が格納されたform
	 * @return 従業員一覧画面
	 */
	@RequestMapping("/update")
	public String update(UpdateEmployeeForm form) {
		Employee employee = employeeService.ShowDetail(Integer.parseInt(form.getId()));
		
		// 主キー検索した結果、データが存在する場合は更新する
		if (employee != null) {
			employee.setDependentsCount(Integer.parseInt(form.getDependentsCount()));
			employeeService.update(employee);
		}
		
		return "redirect:/employee/showList";
	}
}
