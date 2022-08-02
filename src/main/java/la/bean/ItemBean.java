package la.bean;

import java.io.Serializable;

/**
 * 1件の商品を管理するJavaBEan
 * @author tutor
 *
 */
public class ItemBean implements Serializable {

	/**
	 * クラスフィールド
	 */
	private int code;	 // 商品番号
	private String name; // 商品名
	private int price;	 // 価格
	
	/**
	 * デフォルトコンストラクタ
	 */
	public ItemBean() {}

	/**
	 * コンストラクタ
	 * @param code  商品番号
	 * @param name  商品名
	 * @param price 価格
	 */
	public ItemBean(int code, String name, int price) {
		this.code = code;
		this.name = name;
		this.price = price;
	}

	/**
	 * 商品番号を取得する。
	 * @return code 商品番号
	 */
	public int getCode() {
		return code;
	}

	/**
	 * 商品番号を設定する。
	 * @param code 設定する商品番号
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * 商品名を取得する。
	 * @return name 商品名
	 */
	public String getName() {
		return name;
	}

	/**
	 * 商品名を設定する。
	 * @param name 設定する商品名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 価格を取得する。
	 * @return price 価格
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * 価格を設定する。
	 * @param price 設定する価格
	 */
	public void setPrice(int price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ItemBean [");
		builder.append("code=" + code + ", ");
		builder.append("name=" + name + ", ");
		builder.append("price=" + price + "]");
		return builder.toString();
	}
	

}
