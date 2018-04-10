package com.chasel.blog.constant;

import org.apache.log4j.Logger;
import static com.chasel.blog.constant.MessagesConstant.NOT_LOGIN;

import com.chasel.blog.exception.BlogException;
import com.chasel.blog.util.I18NSupport;
import com.chasel.blog.util.ResourceUtil;
import com.github.pagehelper.PageInfo;

/**
 * 基础Controller
 * 
 * @author chasel
 *
 */
public abstract class BaseController extends I18NSupport {

	private Logger log = Logger.getLogger(BaseController.class);

	/**
	 * 分页
	 * 
	 * @param run
	 * @return
	 */
	protected PageInfo<?> doQuery(LamCallable run) {
		PageInfo<?> pageInfo = new PageInfo<>();
		try {
			pageInfo = (PageInfo<?>) run.run();
		} catch (BlogException e) {
			log.error("doQuery has an error {} {}:" + e.getErrCode() + "------and------" + e.getErrMsg());

		} catch (Exception e) {
			log.error("doQuery has an error {}:" + e.getMessage());

		}
		return pageInfo;
	}

	/**
	 * 无返回值
	 * 
	 * @param run
	 * @param successMsg
	 * @param failMsg
	 * @return
	 */
	protected ResponseResult process(LamRunnable run, String successMsg, String failMsg) {
		String responseMsg = "";
		try {
			boolean bool = ResourceUtil.getSession();
			if (!bool) {
				return new ResponseResult(ResponseStatus.NOT_LOGIN, getMassage(NOT_LOGIN));
			}else{
				run.run();
				return new ResponseResult(ResponseStatus.SUCCESS, getMassage(successMsg));
			}
		} catch (BlogException e) {
			log.error("process has an error {} {}:" + e.getErrCode() + "------and-------" + e.getErrMsg());
			responseMsg = (String) e.getErrMsg();

		} catch (Exception e) {
			log.error("process has an error {}:" + e.getMessage());
			responseMsg = failMsg;
		}
		return new ResponseResult(ResponseStatus.FAIL, getMassage(responseMsg));
	}

	/**
	 * 有返回值
	 * 
	 * @param run
	 * @param successMsg
	 * @param failMsg
	 * @return
	 */
	protected ResponseResult value(LamCallable run, String successMsg, String failMsg) {
		String responseMsg = "";
		try {
			boolean bool = ResourceUtil.getSession();
			if (!bool) {
				return new ResponseResult(ResponseStatus.NOT_LOGIN, getMassage(NOT_LOGIN));
			}else{
				Object obj = run.run();
				return new ResponseResult(ResponseStatus.SUCCESS, getMassage(successMsg), obj);
			}
		} catch (BlogException e) {
			responseMsg = (String) e.getErrMsg();
			log.error("value has an error {} {}:" + e.getErrCode() + "------and-------" + e.getErrMsg());
		} catch (Exception e) {
			log.error("value has an error {}:" + e.getMessage());
			responseMsg = failMsg;
		}
		return new ResponseResult(ResponseStatus.FAIL, getMassage(responseMsg), null);
	}
	
	/**
	 * 无返回值
	 * 
	 * @param run
	 * @param successMsg
	 * @param failMsg
	 * @return
	 */
	protected ResponseResult processz(LamRunnable run, String successMsg, String failMsg) {
		String responseMsg = "";
		try {
			run.run();
			return new ResponseResult(ResponseStatus.SUCCESS, getMassage(successMsg));

		} catch (BlogException e) {
			log.error("processz has an error {} {}:" + e.getErrCode() + "------and-------" + e.getErrMsg());
			responseMsg = (String) e.getErrMsg();

		} catch (Exception e) {
			log.error("processz has an error {}:" + e.getMessage());
			responseMsg = failMsg;
		}
		return new ResponseResult(ResponseStatus.FAIL, getMassage(responseMsg));
	}

	/**
	 * 有返回值
	 * 
	 * @param run
	 * @param successMsg
	 * @param failMsg
	 * @return
	 */
	protected ResponseResult valuez(LamCallable run, String successMsg, String failMsg) {
		String responseMsg = "";
		try {
			Object obj = run.run();
			return new ResponseResult(ResponseStatus.SUCCESS, getMassage(successMsg), obj);
		} catch (BlogException e) {
			responseMsg = (String) e.getErrMsg();
			log.error("valuez has an error {}:" + e.getErrCode() + "------and-------" + e.getErrMsg());
		} catch (Exception e) {
			log.error("valuez has an error {}:" + e.getMessage());
			responseMsg = failMsg;
		}
		return new ResponseResult(ResponseStatus.FAIL, getMassage(responseMsg), null);
	}
}
