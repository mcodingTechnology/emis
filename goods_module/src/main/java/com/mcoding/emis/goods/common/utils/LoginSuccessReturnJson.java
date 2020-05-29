package com.mcoding.emis.goods.common.utils;

import com.mcoding.base.core.JsonResult;
import com.mcoding.emis.goods.common.utils.gson.GsonUtil;
import net.sf.json.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

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
public class LoginSuccessReturnJson extends SavedRequestAwareAuthenticationSuccessHandler {


    public LoginSuccessReturnJson() {
    }

    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
       responseOutWithJson(response,authentication.getPrincipal());
    }

    protected void responseOutWithJson(HttpServletResponse response,Object responseObject) {
        JsonResult<JSONObject> jsonResult = new JsonResult<JSONObject>();
        JSONObject responseJSONObject = JSONObject.fromObject(responseObject);
        jsonResult.setStatus("00");
        jsonResult.setMsg("登录成功");
        jsonResult.setData(responseJSONObject);

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
