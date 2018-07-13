package com.fsd.web1;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fsd.entity1.User;
import com.fsd.model1.AjaxResponseBody;
import com.fsd.model1.SearchCriteria;
import com.fsd.web.service.UserService;

@Controller
public class Jsoncontroller {

	UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/index1")
	public String index() {
		return "index1";
	}

	@PostMapping(value = "/api/search")
	@ResponseBody
	public ResponseEntity<?> getSearchResultViaAjax(@Valid @RequestBody SearchCriteria search, Errors errors) {
		AjaxResponseBody result = new AjaxResponseBody();

		if (errors.hasErrors()) {
			result.setMsg(
					errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));
			return ResponseEntity.badRequest().body(result);
		}

		List<User> users = userService.findByUserNameOrEmail(search.getUsername());

		if (users.isEmpty()) {
			result.setMsg("no user fount");
		} else {
			result.setMsg("success");
		}

		result.setResult(users);

		return ResponseEntity.ok(result);

	}
}
