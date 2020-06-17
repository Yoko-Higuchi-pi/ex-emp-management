package jp.co.sample.repository;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import jp.co.sample.domain.Administrator;

/**
 * administrators テーブル操作を行うリポジトリ
 * 
 * @author yoko.higuchi
 *
 */
public class AdministratorRepository {
	/** administrators の RowMapper */
	private static final RowMapper<Administrator> ADMI_ROW_MAPPER = (rs, i) -> {
		Administrator administrator = new Administrator();
		administrator.setId(rs.getInt("id"));
		administrator.setName(rs.getString("name"));
		administrator.setMailAddress(rs.getString("mail_address"));
		administrator.setPassword(rs.getString("passrowd"));
		
		return administrator;
	};
	
	/** SQL実行を行う */
	NamedParameterJdbcTemplate template;
	
	/**
	 * INSERT 実行を行う
	 * 
	 * @param administrator 挿入するデータ
	 */
	public void insert(Administrator administrator) {
		String sql = "INSERT INTO administrators(name, mail_address, password) VALUES(:name, :mailAddress, :password);";
		SqlParameterSource param = new BeanPropertySqlParameterSource(administrator);
		
		template.update(sql, param);
	}
	
	/**
	 * mail_address, password から該当するデータを抽出する
	 * 
	 * @param mailAddress 検索したいメールアドレス
	 * @param password 検索したいパスワード
	 * @return 検索結果(ヒットしたデータ)
	 */
	public Administrator findByMailAddressAndPassword(String mailAddress, String password) {
		String sql = "SELECT id, name, mail_address, password FROM administrators WHERE mail_address=:mailAddress AND password=:password;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("mailAddress", mailAddress).addValue("password", password);
		
		// 該当しない場合は null を返す
		try {
			return template.queryForObject(sql, param, ADMI_ROW_MAPPER);
		}catch (Exception e) {
			return null;
		}
		
	}
}
