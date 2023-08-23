package com.studycafe.studycafe_backend.config.aspect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDate;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Slf4j
@Component
public class LoggerAspect {
    @AfterReturning(pointcut = "execution(* com.studycafe.studycafe_backend..*Controller.*(..))", returning = "result") // 이런 패턴이 실행될 경우 수행
    public void AfterReturning(JoinPoint joinPoint, Object result) {

        StringBuilder loggingData = new StringBuilder();
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest(); // request 정보를 가져온다.

            String controllerName = joinPoint.getSignature().getDeclaringType().getSimpleName();
            String methodName = joinPoint.getSignature().getName();
            Object[] reqArgs = joinPoint.getArgs();
            ObjectMapper mapper = new ObjectMapper();
            String reqBody = "";
            for (Object reqArg : reqArgs)
            {
                try
                {
                    if (reqArg instanceof Object && reqArg instanceof String)
                    {
                        continue;
                    }
                    reqBody += mapper.writeValueAsString(reqArg) + "\n";
                }
                catch (JsonProcessingException e)
                {
                    reqBody = "";
                }
            }

            JSONObject reqParamJson = getParams(request);
            String prtReqParam = "";
            if (reqParamJson.values().size() > 0)
            {
                prtReqParam += reqParamJson.toJSONString() + "\n";
            }
            prtReqParam += reqBody;

            Map<String, Object> params = new HashMap<>();

            loggingData.append("\n===============================================================\n");
            loggingData.append("Request Date : " + LocalDate.now() + "\n");
            loggingData.append("Request URI : " + request.getRequestURI() + "\n");
            loggingData.append("Http Method : " + request.getMethod() + "\n");
            loggingData.append("Controller : " + controllerName + "\n");
            loggingData.append("Method : " + methodName + "\n");
            loggingData.append("Params : " + prtReqParam);
            loggingData.append("===============================================================\n");
            log.info(loggingData.toString());


        }
        catch (Throwable throwable) {
            log.info(loggingData.toString());

        }

    }



    /**
     * request 에 담긴 정보를 JSONObject 형태로 반환한다.
     * @param request
     * @return
     */
    private static JSONObject getParams(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        Enumeration<String> params = request.getParameterNames();
        while (params.hasMoreElements()) {
            String param = params.nextElement();
            String replaceParam = param.replaceAll("\\.", "-");
            jsonObject.put(replaceParam, request.getParameter(param));
        }
        return jsonObject;
    }
}
