package com.ganesh.heycar.util;

import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.ganesh.heycar.model.Dealer;
import com.ganesh.heycar.model.DealerEntity;
import com.ganesh.heycar.model.Vehicle;
import com.ganesh.heycar.model.VehicleEntity;

public class HeyCarUtils {

	public static void ifNotEmpty(String value, Consumer<String> task) {
		if (!isEmpty(value))
			consume(task, value);
	}

	public static boolean isEmpty(String value) {
		return value == null || value.isEmpty();
	}

	public static <T> void consume(Consumer<T> consumer, T value) {
		if (consumer != null)
			consumer.accept(value);
	}

	public static List<VehicleEntity> getVehicleEntity(List<Vehicle> vehicleList,DealerEntity dealer) {
		// TODO Auto-generated method stub
		return vehicleList.stream().map(vehicle->getVehicle(vehicle,dealer)).collect(Collectors.toList());
	}

	public static Dealer getDealer(DealerEntity from) {

		return new Dealer(from.getID(), from.getName());
	}

	public static DealerEntity getDealer(Dealer from) {

		return new DealerEntity(from.getID(), from.getName());
	}
	
	public static Vehicle getVehicle(VehicleEntity from) {

		Vehicle entity = new Vehicle();
		entity.setCode(from.getCode());
		entity.setColor(from.getColor());
		entity.setMake(from.getMake());
		entity.setModel(from.getModel());
		entity.setPower(from.getPower());
		entity.setPrice(from.getPrice());
		entity.setYear(from.getYear());
		entity.setDealerID(from.getDealerID());
		return entity;
	}

	public static VehicleEntity getVehicle(Vehicle from,DealerEntity dealer) {
		
		VehicleEntity entity = new VehicleEntity();
		entity.setCode(from.getCode());
		entity.setColor(from.getColor());
		entity.setMake(from.getMake());
		entity.setModel(from.getModel());
		entity.setPower(from.getPower());
		entity.setPrice(from.getPrice());
		entity.setYear(from.getYear());
		entity.setDealerID(dealer.getID());
		return entity;
	}

	public static List<Vehicle> getVehicles(List<VehicleEntity> list) {
		// TODO Auto-generated method stub
		return list.stream().map(item->getVehicle(item)).collect(Collectors.toList());
	}
	
	public static boolean isAllNull(Object... values) {
        return Stream.of(values).allMatch(Objects::isNull);
    }

	
	
}
