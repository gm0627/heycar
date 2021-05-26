package com.ganesh.heycar.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ganesh.heycar.db.VehicleRepository;
import com.ganesh.heycar.model.DealerEntity;
import com.ganesh.heycar.model.Vehicle;
import com.ganesh.heycar.model.VehicleEntity;
import com.ganesh.heycar.util.HeyCarUtils;

@Service
public class VehicleService {

	@Autowired
	VehicleRepository vehicleRepository;

	@Autowired
	DealerService dealerService;

	public static String TYPE = "text/csv";
	public static final String CODE = "code";
	public static final String MAKE_MODEL = "make/model";
	public static final String POWER = "power-in-ps";
	public static final String YEAR = "year";
	public static final String COLOR = "color";
	public static final String PRICE = "price";
	public static final String KW = "kw";

	static String[] HEADERs = { CODE, MAKE_MODEL, POWER, YEAR, COLOR, PRICE };

	public  boolean hasCSVFormat(MultipartFile file) {

		if (!TYPE.equals(file.getContentType())) {
			return false;
		}

		return true;
	}

	public List<Vehicle> processCSVfile(InputStream is, long dealerID) {
		try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));

				CSVParser csvParser = new CSVParser(fileReader,
						CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim()
								.withIgnoreEmptyLines().withNullString("").withIgnoreSurroundingSpaces());) {
			List<Vehicle> vehicleList = new ArrayList<Vehicle>();
			Iterable<CSVRecord> csvRecords = csvParser.getRecords();

			for (CSVRecord csvRecord : csvRecords) {
				Vehicle vehicle = new Vehicle();
				vehicle.setDealerID(dealerID);
				updateVehicleData(csvRecord, vehicle);
				vehicleList.add(vehicle);
			}

			DealerEntity dealer = dealerService.findDealerEntityByID(dealerID);

			vehicleRepository.saveAll(HeyCarUtils.getVehicleEntity(vehicleList, dealer));
			return vehicleList;
		} catch (IOException e) {
			throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
		}
	}

	public void updateVehicleData(CSVRecord record, Vehicle vehicle) {

		Stream.of(HEADERs).forEach(fieldName -> {
			String value = "";
			try {
				value = record.get(fieldName);
			} catch (Exception e) {

			}
			switch (fieldName) {
			case CODE:
				vehicle.setCode(value);
				break;
			case MAKE_MODEL:
				String[] arr = value.split("/");
				vehicle.setMake(arr[0]);
				vehicle.setModel(arr[1]);
				break;
			case POWER:
				vehicle.setPower(value);
				break;
			case YEAR:
				vehicle.setYear(value);
				break;
			case COLOR:
				vehicle.setColor(value);
				break;
			case PRICE:
				vehicle.setPrice(value);
				break;
			default:
				break;
			}

		});

	}

	public void processVehicleListing(List<Vehicle> vehicleListing, long dealerID) {
		DealerEntity dealer = dealerService.findDealerEntityByID(dealerID);

		vehicleRepository.saveAll(HeyCarUtils.getVehicleEntity(vehicleListing, dealer));
	}

	public List<Vehicle> findVehicleBy(String make, String model, Integer year, String color) {

		VehicleEntity example = new VehicleEntity();
		example.setMake(make);
		example.setModel(model);
		example.setYear(year);
		example.setColor(color);

		List<VehicleEntity> list = HeyCarUtils.isAllNull(make, model, year, color) ? vehicleRepository.findAll()
				: vehicleRepository.findAll(Example.of(example));

		return HeyCarUtils.getVehicles(list);
	}
}
