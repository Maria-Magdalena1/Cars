package cars.cars.services;

import cars.cars.entities.EngineType;
import cars.cars.entities.TransmissionType;
import cars.cars.entities.Vehicle;
import cars.cars.entities.VehicleType;

import java.util.List;

public interface VehicleService {

    List<Vehicle> searchVehicle(VehicleType type,String brand,String model,EngineType engine,
                                TransmissionType transmission,String color,Integer minPrice,
                                Integer maxPrice,Integer minYear,Integer maxYear);

    List<String> getAllBrands();

    List<String> getAllModels();

    List<VehicleType> getAllTypes();

    List<EngineType> getAllEngines();

    List<TransmissionType> getAllTransmissions();

    List<String> getAllColors();

}
