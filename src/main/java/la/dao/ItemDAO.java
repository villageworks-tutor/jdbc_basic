package la.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import la.bean.ItemBean;

public class ItemDAO {

	/**
	 * クラスフィールド：データベース接続オブジェクト
	 */
	private Connection conn;
	
	/**
	 * コンストラクタ
	 * @throws DAOException
	 */
	public ItemDAO() throws DAOException {
		// データベース接続情報
		String driver = "org.postgresql.Driver";
		String url = "jdbc:postgresql:sample";
		String user = "student";
		String password = "himitu";
		try {
			// JDBCドライバのロード
			Class.forName(driver);
			// データベースへの接続：データベース接続オブジェクトの取得
			this.conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new DAOException("データベースへの接続に失敗しました。");
		}
	}

	/**
	 * すべての商品を取得する。
	 * @return List<ItemBean> 商品リスト
	 * @throws DAOException
	 */
	public List<ItemBean> findAll() throws DAOException {
		String sql = "SELECT * FROM item ORDER BY code";
		try (// SQL実行オブジェクトを取得
			 PreparedStatement pstmt = this.conn.prepareStatement(sql);
			 // SQLの実行と結果セットの取得
			 ResultSet rs = pstmt.executeQuery();) {
			List<ItemBean> list = new ArrayList<>();
			// 結果セットから商品リストを生成
			while (rs.next()) {
				// 1レコードの商品のインスタンス化
				ItemBean bean = new ItemBean();
				bean.setCode(rs.getInt("code"));
				bean.setName(rs.getString("name"));
				bean.setPrice(rs.getInt("price"));
				// 商品リストに追加
				list.add(bean);
			}
			// 商品リストの返却
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}
	}

}
