package hello.servlet.web.frontcontoller.v5.adapter;

import hello.servlet.web.frontcontoller.ModelView;
import hello.servlet.web.frontcontoller.v3.ControllerV3;
import hello.servlet.web.frontcontoller.v5.MyHandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV3HandlerAdapter implements MyHandlerAdapter {
    @Override
    public boolean support(Object handler) {
        return (handler instanceof ControllerV3);
    }

    @Override
    public ModelView handler(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {

        ControllerV3 controller = (ControllerV3) handler;
        Map<String, String> pramMap = createPramMap(request);
        ModelView mv = controller.process(pramMap);

        return mv;
    }

    private static Map<String, String> createPramMap(HttpServletRequest request) {
        Map<String, String> pramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> pramMap.put(paramName, request.getParameter(paramName)));
        return pramMap;
    }

}
