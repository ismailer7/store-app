package com.ur.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ur.pojo.CredentialBean;
import com.ur.pojo.ResponseBean;
import com.ur.pojo.SingleResponseBean;
import com.ur.pojo.StoreDTO;
import com.ur.pojo.UserDTO;
import com.ur.service.IPlacesService;
import com.ur.service.IUserService;

@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
@RestController
public class HomeController {

	@Autowired
	private IPlacesService placeService;

	@Autowired
	private IUserService userService;

	@GetMapping("/hi")
	public String sayHi() {
		return "Hello over there!";
	}

	@GetMapping("/places")
	public ResponseEntity<ResponseBean> fetchData() {
		return new ResponseEntity<ResponseBean>(placeService.fetchPlaces(), HttpStatus.OK);
	}

	@GetMapping("/place")
	public SingleResponseBean getStoreBeanByPlace(@RequestParam String placeID) {
		return placeService.fetchOnePlace(placeID);
	}

	@GetMapping("/something")
	public String unauthorizedPoint() {
		return "Hi ther you're authorized!";
	}

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<Object> login(@RequestBody CredentialBean bean) {
		UserDTO user = userService.findUserByCredentials(bean.getUsername(), bean.getPassword());
		return (user == null) ? new ResponseEntity<>("Invalid Credentials!", HttpStatus.BAD_REQUEST)
				: new ResponseEntity<>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<Boolean> register(@RequestBody UserDTO user) {
		UserDTO userDto = userService.register(user);
		return userDto != null ? new ResponseEntity<Boolean>(true, HttpStatus.OK)
				: new ResponseEntity<Boolean>(false, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping("/preffered")
	public List<StoreDTO> getAllPrefferedStores(@RequestParam Long id) {
		return userService.getAllPrefferedStores(id);
	}

	@RequestMapping(value = "/add/store", method = RequestMethod.POST)
	public ResponseEntity<String> addStoreToPrefferedList(@RequestParam Long id, @RequestBody StoreDTO storeDto) {
		return userService.addToPrefferedList(id, storeDto) != null ? new ResponseEntity<String>("Store added!", HttpStatus.OK)
				: new ResponseEntity<String>("Oops, Store was not Added!", HttpStatus.OK);
	}

	@RequestMapping(value = "/remove/store")
	public ResponseEntity<String> removeFromPreferredList(@RequestParam Long id, @RequestBody StoreDTO storeDto) {
		return userService.removeFromPreferredList(id, storeDto)
				? new ResponseEntity<String>("removed Successfully!", HttpStatus.OK)
				: new ResponseEntity<String>("Remove operation failed", HttpStatus.OK);
	}

}
