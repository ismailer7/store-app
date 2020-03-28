package com.ur.web;

import java.text.ParseException;
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

import com.ur.pojo.Bean;
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
	public ResponseEntity<ResponseBean> fetchData(@RequestParam String latitude, @RequestParam String longitude) {
		return new ResponseEntity<ResponseBean>(placeService.fetchPlaces(latitude, longitude), HttpStatus.OK);
	}

	@GetMapping("/place")
	public SingleResponseBean getStoreBeanByPlace(@RequestParam String placeId) {
		return placeService.fetchOnePlace(placeId);
	}

	@GetMapping("/something")
	public String unauthorizedPoint() {
		return "Hi there you're authorized!";
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
	
	@GetMapping("/user/getPlaces")
	public ResponseEntity<ResponseBean> fetchDataAuthenticated(@RequestParam Long userId) throws ParseException {
//		if (userService.getAllAvailableStores(userId).isEmpty()) {
//			List<StoreDTO> stores = placeService.fetchPlaces(latitude, longitude).getResults();
//			for (StoreDTO store : stores) {
//				userService.addToAvailableStores(userId, store);
//			}
//			return new ResponseEntity<ResponseBean>(placeService.fetchPlaces(latitude, longitude), HttpStatus.OK);
//		} else {
			return new ResponseEntity<ResponseBean>(placeService.fetchUnremovedPlaces(userId), HttpStatus.OK);
//		}

	}

	@GetMapping("/preferred/show")
	public List<StoreDTO> getAllPrefferedStores(@RequestParam Long userId) {
		return userService.getAllPrefferedStores(userId);
	}

	@RequestMapping(value = "/preferred/add", method = RequestMethod.POST)
	public ResponseEntity<String> addStoreToPrefferedList(@RequestBody Bean bean)
			throws ParseException {
		return userService.addToPrefferedList(bean.getUserId(), bean.getStoreId()) != null
				? new ResponseEntity<String>("Store added!", HttpStatus.OK)
				: new ResponseEntity<String>("Oops, Store was not Added!", HttpStatus.OK);
	}

	@RequestMapping(value = "/preferred/remove")
	public ResponseEntity<String> removeFromPreferredList(@RequestBody Bean bean) {
		return userService.removeFromPreferredList(bean.getUserId(), bean.getStoreId()) != null
				? new ResponseEntity<String>("removed Successfully From Preferred List!", HttpStatus.OK)
				: new ResponseEntity<String>("Remove operation failed", HttpStatus.OK);
	}

	@RequestMapping("/nearby/remove")
	public ResponseEntity<String> removeFromNearby(@RequestBody Bean bean) {
		return userService.removeFromNearby(bean.getUserId(), bean.getStoreId()) != null
				? new ResponseEntity<String>("removed From Nearby", HttpStatus.OK)
				: new ResponseEntity<String>("removed Operation Failed!", HttpStatus.OK);
	}
}
