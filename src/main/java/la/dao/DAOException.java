package la.dao;

/**
 * すべてのDAOクラスで発生した例外を統一して処理するために変換する独自例外
 * @author tutor
 */
public class DAOException extends Exception {

	/**
	 * コンストラクタ
	 * @param message 例外メッセージ
	 */
	public DAOException(String message) {
		super(message);
	}

}
