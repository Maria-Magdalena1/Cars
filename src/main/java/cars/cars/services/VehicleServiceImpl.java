package cars.cars.services;

import cars.cars.entities.EngineType;
import cars.cars.entities.TransmissionType;
import cars.cars.entities.Vehicle;
import cars.cars.entities.VehicleType;
import cars.cars.repositories.VehicleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;

    @Override
    public List<Vehicle> searchVehicle(VehicleType type, String brand, String model, EngineType engine, TransmissionType transmission, String color, Integer minPrice, Integer maxPrice, Integer minYear, Integer maxYear) {
        if (minPrice != null && minPrice < 0) {
            minPrice = 0;
        }

        if (maxPrice != null && maxPrice < 0) {
            maxPrice = 0;
        }

        if (minPrice != null && maxPrice != null && minPrice > maxPrice) {
            int temp = minPrice;
            minPrice = maxPrice;
            maxPrice = temp;
        }

        if (minYear != null && maxYear != null && minYear > maxYear) {
            int temp = minYear;
            minYear = maxYear;
            maxYear = temp;
        }

        return vehicleRepository.search(type, brand, model, engine, transmission, color,
                minPrice, maxPrice, minYear, maxYear);
    }

    @Override
    public List<String> getAllBrands() {
        return vehicleRepository.findAllBrands();
    }

    @Override
    public List<String> getAllModels() {
        return vehicleRepository.findAllModels();
    }

    @Override
    public List<VehicleType> getAllTypes() {
        return Arrays.asList(VehicleType.values());
    }

    @Override
    public List<EngineType> getAllEngines() {
        return Arrays.asList(EngineType.values());
    }

    @Override
    public List<TransmissionType> getAllTransmissions() {
        return Arrays.asList(TransmissionType.values());
    }

    @Override
    public List<String> getAllColors() {
        return vehicleRepository.findAllColors();
    }
}
