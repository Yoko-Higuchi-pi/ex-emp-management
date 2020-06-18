package jp.co.sample.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	/** 従業員の業務処理 */
	@Autowired
	private EmployeeService employeeService;
	
	/**
	 * 更新情報を取得するクラスのインスタンス生成
	 * 
	 * @return インスタンス
	 */
	@ModelAttribute
	public UpdateEmployeeForm setUpUpdateEmployeeForm() {
		return new UpdateEmployeeForm();
	}
	
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
	 * 従業員情報を取得する
	 * 
	 * @param id 
	 * @param model
	 * @return
	 */
	@RequestMapping("/showDetail")
	public String showDetail(String id, Model model) {
		Employee employee = employeeService.ShowDetail(Integer.parseInt(id));
		
		model.addAttribute("employee", employee);
		
		return "employee/detail";
	}
	
}
