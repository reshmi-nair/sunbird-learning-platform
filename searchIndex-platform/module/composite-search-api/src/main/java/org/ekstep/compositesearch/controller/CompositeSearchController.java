package org.ekstep.compositesearch.controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.ekstep.compositesearch.mgr.ICompositeSearchManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ilimi.common.dto.Request;
import com.ilimi.common.dto.Response;
import com.ilimi.common.logger.LogHelper;

@Controller
@RequestMapping("v2/search")
public class CompositeSearchController extends BaseCompositeSearchController {

	private static LogHelper LOGGER = LogHelper.getInstance(CompositeSearchController.class.getName());

	@Autowired
	private ICompositeSearchManager compositeSearchManager;

	@RequestMapping(value = "/sync/{id:.+}", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Response> create(@PathVariable(value = "id") String graphId,
			@RequestParam(name = "objectType", required = false) String objectType,
			@RequestBody Map<String, Object> map, @RequestHeader(value = "user-id") String userId,
			HttpServletResponse resp) {
		String apiId = "composite-search.sync";
		LOGGER.info(apiId + " | Graph : " + graphId + " | ObjectType: " + objectType);
		try {
			Request request = getRequest(map);
			Response response = compositeSearchManager.sync(graphId, objectType, request);
			return getResponseEntity(response, apiId, null);
		} catch (Exception e) {
			LOGGER.error("Error: " + apiId, e);
			return getExceptionResponseEntity(e, apiId, null);
		}
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Response> search(@RequestBody Map<String, Object> map,
			@RequestHeader(value = "user-id") String userId, HttpServletResponse resp) {
		String apiId = "composite-search.search";
		LOGGER.info(apiId + " | Request : " + map);
		try {
			Request request = getRequest(map);
			Response response = compositeSearchManager.search(request);
			return getResponseEntity(response, apiId, null);
		} catch (Exception e) {
			LOGGER.error("Error: " + apiId, e);
			return getExceptionResponseEntity(e, apiId, null);
		}
	}

	@RequestMapping(value = "/count", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Response> count(@RequestBody Map<String, Object> map,
			@RequestHeader(value = "user-id") String userId, HttpServletResponse resp) {
		String apiId = "composite-search.count";
		LOGGER.info(apiId + " | Request : " + map);
		try {
			Request request = getRequest(map);
			Response response = compositeSearchManager.count(request);
			return getResponseEntity(response, apiId, null);
		} catch (Exception e) {
			LOGGER.error("Error: " + apiId, e);
			return getExceptionResponseEntity(e, apiId, null);
		}
	}

}
