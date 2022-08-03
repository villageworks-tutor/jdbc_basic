package la.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import la.bean.ItemBean;
import la.dao.DAOException;
import la.dao.ItemDAO;

/**
 * Servlet implementation class ItemServlet
 */
@WebServlet("/ItemServlet")
public class ItemServlet extends HttpServlet {
	
	/** シリアルバージョンUID */
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータの文字コードを設定
		request.setCharacterEncoding("utf-8");
		// リクエストパラメータのactionキーを取得
		String action = request.getParameter("action");
		// 遷移先URLの初期化
		String nextPage = "pages/errInternal.html";
		// actionキーによって処理を分岐
		if (action == null || action.isEmpty()) {
			// actionキー未送信または未入力の場合：トップページに遷移
			nextPage = "pages/top.jsp";
		} else if (action.equals("all") || action.equals("operations")) {
			// 全商品表示
			try {
				// 全商品を取得
				ItemDAO dao = new ItemDAO();
				List<ItemBean> list = dao.findAll();
				// 取得した商品リストをリクエストスコープに登録
				request.setAttribute("items", list);
				// actionによって遷移先を切り替え
				if (action.equals("all")) {
					nextPage = "pages/showAllItem.jsp";
				} else {
					nextPage = "pages/showItemForm.jsp";
				}
			} catch (DAOException e) {
				e.printStackTrace();
				throw new ServletException(e.getMessage());
			}
		} else if (action.equals("sort")) {
			try {
				// リクエストパラメータを取得
				String key = request.getParameter("key");
				// ソートキーによってソートされた商品リストを取得
				ItemDAO dao = new ItemDAO();
				boolean isAsc = false;
				if (key == null || key.isEmpty() || key.equals("price_asc")) {
					isAsc = true;
				}
				List<ItemBean> list = dao.sortPrice(isAsc);
				// リクエストスコープに登録
				request.setAttribute("items", list);
				// 遷移先URLを設定
				nextPage = "pages/showItemForm.jsp";
			} catch (DAOException e) {
				e.printStackTrace();
				throw new ServletException(e.getMessage());
			}
		}
		// 画面遷移　
		RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
