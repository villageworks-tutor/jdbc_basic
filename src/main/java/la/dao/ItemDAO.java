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

	/**
	 * 価格について指定された並べ方すべてので商品を並べ替える。
	 * @param isAsc 昇順で並べ替える場合はtrue、それ以外はfalse
	 * @return List<ItemBean> 価格について並べ替えられた他商品リスト
	 * @throws DAOException
	 */
	public List<ItemBean> sortPrice(boolean isAsc) throws DAOException {
		// 実行するSQLの共通部分を設定
		String sql = "SELECT code, name, price FROM item ORDER BY price ";
		// 引数によって実行するSQLを変更
		if (isAsc) { // 昇順の場合
			sql += "ASC";
		} else { // 降順の場合
			sql += "DESC";
		}
		try (// SQL実効オブジェクトを取得
			 PreparedStatement pstmt = this.conn.prepareStatement(sql);
			 // SQLの実行と結果セットの取得
			 ResultSet rs = pstmt.executeQuery();) {
			List<ItemBean> list = new ArrayList<>();
			// 結果セットから商品リストを取得
			while (rs.next()) {
				// 1レコード分をインスタンス化
				ItemBean bean = new ItemBean();
				bean.setCode(rs.getInt("code"));
				bean.setName(rs.getString("name"));
				bean.setPrice(rs.getInt("price"));
				// 商品リストに追加
				list.add(bean);
			}
			// 商品リストを返却
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}
	}

	/**
	 * 商品を追加する。
	 * @param name 追加する商品の商品名
	 * @param price 追加する商品の価格
	 * @throws DAOException
	 */
	public void addItem(String name, int price) throws DAOException {
		String sql = "INSERT INTO item (name, price) VALUES (?, ?)";
		try (PreparedStatement pstmt = this.conn.prepareStatement(sql);) {
			// パラメータバインディング
			pstmt.setString(1, name);
			pstmt.setInt(2, price);
			// SQLの実行
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		}
	}

	/**
	 * 指定した価格以下の商品を取得する。
	 * @param price 価格の上限
	 * @return List<ItemBean> 商品リスト
	 * @throws DAOException
	 */
	public List<ItemBean> findByPrice(int price) throws DAOException {
		String sql = "SELECT code, name, price FROM item WHERE price <= ? ORDER BY price DESC";
		try (PreparedStatement pstmt = this.conn.prepareStatement(sql);) {
			// パラメータバインディング
			pstmt.setInt(1, price);
			try (// SQLの実行と結果セットの取得
				 ResultSet rs = pstmt.executeQuery();) {
				// 商品リストの初期化
				List<ItemBean> list = new ArrayList<>();
				// 結果セットから商品リストを生成
				while (rs.next()) {
					// 1レコード分をインスタンス化
					ItemBean bean = new ItemBean();
					bean.setCode(rs.getInt("code"));
					bean.setName(rs.getString("name"));
					bean.setPrice(rs.getInt("price"));
					// 商品リストに追加
					list.add(bean);
				}
				// 商品リストを返却
				return list;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}
	}

	public void deleteByPrimaryKey(int code) throws DAOException {
		String sql = "DELETE FROM item WHERE code = ?";
		try (// SQL実行オブジェクトを取得
				PreparedStatement pstmt = this.conn.prepareStatement(sql);) {
			// パラメータバインディング
			pstmt.setInt(1, code);
			// SQLを実行
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		}
	}

}
