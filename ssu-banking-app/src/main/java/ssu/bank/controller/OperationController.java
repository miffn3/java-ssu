package ssu.bank.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ssu.bank.service.iface.OperationService;

import java.util.List;

@RestController
@RequestMapping("/operations")
public class OperationController {

	private OperationService operationService;

	@Autowired
	public OperationController(OperationService operationService) {
		this.operationService = operationService;
	}

	@GetMapping("/history/{id}")
	@ApiOperation("get account's history")
	public List<String> showHistory(@PathVariable("id") String id) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder
				.getContext()
				.getAuthentication()
				.getPrincipal();
		String username = userDetails.getUsername();
		return operationService.getHistory(Long.valueOf(id));
	}
}
