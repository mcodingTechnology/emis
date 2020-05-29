package com.mcoding.emis.goods.common.utils;

import com.mcoding.base.core.JsonResult;
import com.mcoding.emis.goods.common.utils.gson.GsonUtil;
import net.sf.json.JSONObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Comment :
 * Author : hexh
 * Time : 2017/7/4
 */
public class LoginFailedReturnJson extends SimpleUrlAuthenticationFailureHandler {


    public LoginFailedReturnJson() {
    }

    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws ServletException, IOException {
       responseOutWithJson(response);
    }

    protected void responseOutWithJson(HttpServletResponse response) {
        JsonResult<JSONObject> jsonResult = new JsonResult<JSONObject>();
        jsonResult.setStatus("01");
        jsonResult.setMsg("用户名或密码错误，请重新输入");

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.append(GsonUtil.bean2Json(jsonResult));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }
    }
}
