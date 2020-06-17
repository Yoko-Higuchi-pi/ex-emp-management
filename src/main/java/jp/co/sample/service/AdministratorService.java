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
	
	public void insert(Administrator administrator) {
		administratorRepository.insert(administrator);
	}
	
}