package ua.training.restaurant.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import ua.training.restaurant.servlet.command.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "Servlet", urlPatterns = "/")
public class Servlet extends HttpServlet {
    private final static String REDIRECT_URL="redirect:";
    private Map<String, Command> commands = new HashMap<>();

    public void init(ServletConfig servletConfig) {
        commands.put("addfunds", new AddFunds());
        commands.put("admin", new AdminPage());
        commands.put("billmaking", new BillMaking());
        commands.put("billpaying", new BillPaying());
        commands.put("concreteuserstatistic", new ConcreteUserStatistic());
        commands.put("foodmenu", new FoodMenu());
        commands.put("order", new Order());
        commands.put("orderconfirmation", new OrderConfirmation());
        commands.put("shoppingcart", new ShoppingCart());
        commands.put("user", new UserPage());
        commands.put("userstatistics", new UserStatistics());
        commands.put("logout", new LogOut());
        commands.put("login", new Login());
        commands.put("signup", new SignUp());
        commands.put("error404", new Error404());
        commands.put("paybill", new PayBill());
        commands.put("buyproduct", new BuyProduct());
        commands.put("saveorder", new SaveOrder());
        commands.put("shoppingcartremovedish", new ShoppingCartRemoveDish());
        commands.put("acceptorder", new AcceptOrder());
        commands.put("makebill", new MakeBill());
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getRequestURI();
        path = path.replaceAll(".*/", "");

        var respValue = "";
        try {
            Command command = commands.get(path);
            respValue = command.execute(request);
        } catch (Exception e) {
            respValue = new BadRequest().execute(request);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, respValue);
            return;
        }

        response.setContentType("application/json");
        response.getWriter().write(respValue);
        /*String page = command.execute(request);
        if (page.contains(REDIRECT_URL)) {
            response.sendRedirect(page.replace(REDIRECT_URL, request.getContextPath()));
        } else {
            request.getRequestDispatcher(page).forward(request, response);
        }*/
    }
}
