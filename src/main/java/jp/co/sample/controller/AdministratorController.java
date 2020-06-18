package jp.co.sample.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Administrator;
import jp.co.sample.form.InsertAdministratorForm;
import jp.co.sample.form.LoginForm;
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
	
	/** セッション */
	@Autowired
	private HttpSession session;
	
	/**
	 * InsertAdministrationForm のインスタンス化.
	 * @return new したオブジェクト
	 */
	@ModelAttribute
	public InsertAdministratorForm setUpInsertAdministratorForm() {
		return new InsertAdministratorForm();
	}
	
	/**
	 * LoginForm のインスタンス化.
	 * @return new したオブジェクト
	 */
	@ModelAttribute
	public LoginForm setUpLoginForm() {
		return new LoginForm();
	}
	
	/**
	 * ログイン画面を表示する.
	 * 
	 * @return ログイン画面
	 */
	@RequestMapping("")
	public String toLogin(Model model) {
		return "administrator/login";
	}
	
	
	/**
	 * 登録 を行う HTML を返す.
	 * @return 登録 を行う
	 */
	@RequestMapping("/toInsert")
	public String toInsert() {
		return "administrator/insert";
	}
	

	/**
	 * 管理者情報を登録する.
	 * 
	 * @param form 入力情報
	 * @return ログイン画面
	 */
	@RequestMapping("/insert")
	public String insert(InsertAdministratorForm form) {
		Administrator administrator = new Administrator();
		
		BeanUtils.copyProperties(form, administrator);
		administratorService.insert(administrator);
		
		return "redirect:/";
	}
	
	/**
	 * ログインチェックを行う.
	 * 
	 * @param form 入力情報
	 * @param model リクエストスコープ
	 * @return エラー時:ログイン画面へのリダイレクト, 成功時:リスト一覧表示画面
	 */
	@RequestMapping("/login")
	public String login(LoginForm form, Model model) {
		Administrator administrator = new Administrator();
		
		administrator = administratorService.login(form.getMailAddress(), form.getPassword());
		System.out.println(administrator);
		if (administrator == null) {
			model.addAttribute("error", "メールアドレスまたはパスワードが不正です");
			return toLogin(model);
		} else {
			session.setAttribute("administratorName", administrator.getName());
			
			return "forward:/employee/showList";
		}
		
	}
	
	/**
	 * セッション情報を削除し、ログアウトする.
	 * 
	 * @return ログイン画面
	 */
	@RequestMapping("/logout")
	public String logout() {
		session.invalidate();
		
		return "redirect:/";
	}
}
