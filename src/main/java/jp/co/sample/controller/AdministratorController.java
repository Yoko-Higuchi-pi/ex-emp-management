package jp.co.sample.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Administrator;
import jp.co.sample.form.InsertAdministratorForm;
import jp.co.sample.service.AdministratorService;


/**
 * 管理者　関連機能の処理の制御を行うコントローラ.
 * 
 * @author yoko.higuchi
 *
 */

@Controller
@RequestMapping("/")
public class AdministratorController {
	/** service クラスから参照情報を注入 */
	@Autowired
	private AdministratorService administratorService;
	
	/**
	 * form のインスタンス化 (Model で利用可能にする).
	 * @return インスタンス
	 */
	@ModelAttribute
	public InsertAdministratorForm setUpInsertAdministratorForm() {
		return new InsertAdministratorForm();
	}
	
	/**
	 * insert を行う HTML を返す.
	 * @return insert を行う
	 */
	@RequestMapping("/insert")
	public String toInsert() {
		return "administrator/insert";
	}
	
	@RequestMapping("/toInsert")
	public String insert(InsertAdministratorForm form) {
		Administrator administrator = new Administrator();
		
		BeanUtils.copyProperties(form, administrator);
		administratorService.insert(administrator);
		
		return "redirect:/";
	}
}
