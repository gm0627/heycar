package com.ganesh.heycar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ganesh.heycar.exception.CSVParserException;
import com.ganesh.heycar.model.Vehicle;
import com.ganesh.heycar.service.VehicleService;
import com.ganesh.heycar.util.ResponseMessage;

@RestController
public class VehicleController {

	@Autowired
	VehicleService service;

	@PostMapping(value = "/upload_csv/{dealerId}")
	public ResponseEntity<Object> uploadCSVFile(@PathVariable(required = true) Long dealerId,
			@RequestParam("file") MultipartFile csv) {

		String message = "";
			try {
				service.processCSVfile(csv.getInputStream(), dealerId);

				message = "Uploaded the file successfully: " + csv.getOriginalFilename();
				return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(message));
			} catch (Exception e) {
				message = "Could not upload the file: " + csv.getOriginalFilename() + "!";
				throw new CSVParserException(e.getMessage());
			}

	}

	@PostMapping(value = "/vehicle_listings/{dealerId}")
	public ResponseEntity<Object> uploadJSONList(@Validated @RequestBody List<Vehicle> vehicleListing,
			@PathVariable(required = true) Long dealerId) {
		String message = "";
		try {
			if(vehicleListing.isEmpty()) {
				message = "Vehicle list is empty";
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
			}
			service.processVehicleListing(vehicleListing, dealerId);
			message = "Uploaded the list successfully ";
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			message = "Could not upload the list!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}
		
	}

	@GetMapping(path = "/search")
	public List<Vehicle> getListings(@RequestParam(value = "make", required = false) final String make,
			@RequestParam(value = "model", required = false) final String model,
			@RequestParam(value = "year", required = false) final Integer year,
			@RequestParam(value = "color", required = false) final String color) {

		return service.findVehicleBy(make, model, year, color);

	}
}
