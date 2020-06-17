package jp.co.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.domain.Administrator;
import jp.co.sample.repository.AdministratorRepository;

/**
 * 管理者関連の業務処理を行うサービス.
 * 
 * @author yoko.higuchi
 *
 */

@Service
@Transactional
public class AdministratorService {
	@Autowired
	private AdministratorRepository administratorRepository;
	
	/**
	 * 管理者情報を登録する.
	 * 
	 * @param administrator 登録情報
	 */
	public void insert(Administrator administrator) {
		administratorRepository.insert(administrator);
	}
	
	/**
	 * メールアドレスとパスワードから管理者情報を抽出する.
	 * 
	 * @param mailAddress 入力されたメールアドレス
	 * @param password 入力されたパスワード
	 * @return SQL実行結果から得られたデータ
	 */
	public Administrator login(String mailAddress, String password) {
		return administratorRepository.findByMailAddressAndPassword(mailAddress, password);
	}
}
