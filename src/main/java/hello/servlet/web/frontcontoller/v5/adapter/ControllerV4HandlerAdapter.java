package hello.servlet.web.frontcontoller.v5.adapter;

import hello.servlet.web.frontcontoller.ModelView;
import hello.servlet.web.frontcontoller.v4.ControllerV4;
import hello.servlet.web.frontcontoller.v5.MyHandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV4HandlerAdapter implements MyHandlerAdapter {
    @Override
    public boolean support(Object handler) {
        return (handler instanceof ControllerV4);
    }

    @Override
    public ModelView handler(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {

        ControllerV4 controller = (ControllerV4) handler;
        Map<String, String> pramMap = createPramMap(request);
        Map<String, Object> model = new HashMap<>();
        String viewName = controller.process(pramMap, model); //논리 이름

        controller.process(pramMap, model);

        ModelView mv = new ModelView(viewName);
        mv.setModel(model);

        return mv;
    }

    private static Map<String, String> createPramMap(HttpServletRequest request) {
        Map<String, String> pramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> pramMap.put(paramName, request.getParameter(paramName)));
        return pramMap;
    }
}
