package la.dao;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import la.bean.ItemBean;

class ItemDaoTest {
	
	/** テスト対象クラス：system under test */
	private ItemDAO sut;

	@BeforeEach
	void setUp() throws Exception {
		this.sut = new ItemDAO();
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Nested
	@DisplayName("ItemDAO#sortPriceメソッドのテストクラス")
	class SortPriceTest {
		@Test
		@DisplayName("【Test_12】商品の価格の高い順に取得することができる")
		void test_12() throws Exception {
			// setup
			boolean target = false; // 降順
			List<ItemBean> expectedList = new ArrayList<>();
			expectedList.add(new ItemBean(8,"Invader Fighter", 3400));
			expectedList.add(new ItemBean(1,"Javaの基本", 2500));
			expectedList.add(new ItemBean(9,"Play the BascketBall", 2200));
			expectedList.add(new ItemBean(4,"なつかしのアニメシリーズ", 2000));
			expectedList.add(new ItemBean(6,"Space Wars 3", 1800));
			expectedList.add(new ItemBean(3,"料理BOOK!", 1200));
			expectedList.add(new ItemBean(5,"The Racer", 1000));
			expectedList.add(new ItemBean(2,"MLB Fun", 980));
			expectedList.add(new ItemBean(7,"パズルゲーム", 780));

			// execute
			List<ItemBean> actualList = sut.sortPrice(target);
			
			// verify
			if (actualList.size() > 0) {
				for (int i = 0; i < actualList.size(); i++) {
					ItemBean actual = actualList.get(i);
					ItemBean expected = expectedList.get(i);
					assertThat(actual.toString(), is(expected.toString()));
				}
			} else {
				fail("テスト対象メソッドはまだ実装されていません。");
			}
		}
		
		@Test
		@DisplayName("【Test_11】商品の価格の低い順に取得することができる")
		void test_11() throws Exception {
			// setup
			boolean target = true; // 降順
			List<ItemBean> expectedList = new ArrayList<>();
			expectedList.add(new ItemBean(7,"パズルゲーム", 780));
			expectedList.add(new ItemBean(2,"MLB Fun", 980));
			expectedList.add(new ItemBean(5,"The Racer", 1000));
			expectedList.add(new ItemBean(3,"料理BOOK!", 1200));
			expectedList.add(new ItemBean(6,"Space Wars 3", 1800));
			expectedList.add(new ItemBean(4,"なつかしのアニメシリーズ", 2000));
			expectedList.add(new ItemBean(9,"Play the BascketBall", 2200));
			expectedList.add(new ItemBean(1,"Javaの基本", 2500));
			expectedList.add(new ItemBean(8,"Invader Fighter", 3400));

			// execute
			List<ItemBean> actualList = sut.sortPrice(target);
			
			// verify
			if (actualList.size() > 0) {
				for (int i = 0; i < actualList.size(); i++) {
					ItemBean actual = actualList.get(i);
					ItemBean expected = expectedList.get(i);
					assertThat(actual.toString(), is(expected.toString()));
				}
			} else {
				fail("テスト対象メソッドはまだ実装されていません。");
			}
		}
	}

	@Nested
	@DisplayName("ItemDAO#constructorメソッドのテストクラス")
	class ConstructorTest {
		@Test
		@DisplayName("【Test_02】ItemDAOのconnフィールドはjava.ql.Connectionのインスタンスである")
		void test_02() throws Exception {
			// setup：privateフィールドにアクセスするための前準備
			ItemDAO target = new ItemDAO();
			Class<? extends ItemDAO> clazz = target.getClass();
			Field targetField = clazz.getDeclaredField("conn");
			targetField.setAccessible(true);
			// execute
			Object actual = targetField.get(target);
			// verify
			assertThat(actual, is(instanceOf(Connection.class)));
		}
		@Test
		@DisplayName("【Test_01】ItemDAOはインスタンス化できる")
		void test_01() throws Exception {
			// execute
			Object actual = new ItemDAO();
			// verify
			assertThat(actual, is(instanceOf(ItemDAO.class)));
		}
	}

}
