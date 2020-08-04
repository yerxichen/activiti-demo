package cn.yer.activiti.demo.exception;



import cn.yer.activiti.demo.response.BaseRespCode;
import cn.yer.activiti.demo.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler extends BasicErrorController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public GlobalExceptionHandler() {
        super(new DefaultErrorAttributes(), new ErrorProperties());

    }
    public GlobalExceptionHandler(ErrorAttributes errorAttributes, ErrorProperties errorProperties) {
        super(errorAttributes, errorProperties);
    }

    /**
     * 系统繁忙，请稍候再试"
     */
    @ExceptionHandler(value = Exception.class)
    public <T> Result<Object> handleException(Exception e) {
        logger.info("默认异常", e);
        return Result.error(BaseRespCode.SYSTEM_BUSY.getCode(),BaseRespCode.SYSTEM_BUSY.getMsg());
    }

    /**
     * 自定义全局异常处理
     */
    @ExceptionHandler(value = GlobalException.class)
    <T> Result<Object> globalExceptionHandler(GlobalException e) {
        logger.info("自定义异常：", e.getDetailMessage());
        return Result.error(e.getMessageCode(), e.getDetailMessage());
    }


    /**
     * 处理filter中无法捕获的异常
     * @param request
     * @return
     */
//    @Override
//    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
//        Map<String, Object> body = getErrorAttributes(request, isIncludeStackTrace(request, MediaType.ALL));
//        logger.info("特殊异常："+body.get("message"));
//        if(body.get("message").equals("10003")){
//            Result result=Result.error(10003,"token失效");
//            String str=JSONObject.toJSONString(result);
//            Map<String,Object> map=JSONObject.parseObject(str,Map.class);
//            return new ResponseEntity(map, HttpStatus.OK);
//        }else{
//            return super.error(request);
//        }
//    }


}
