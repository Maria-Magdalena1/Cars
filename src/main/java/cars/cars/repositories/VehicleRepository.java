package cars.cars.repositories;

import cars.cars.entities.EngineType;
import cars.cars.entities.TransmissionType;
import cars.cars.entities.Vehicle;
import cars.cars.entities.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, UUID> {

    @Query("""
            SELECT v FROM Vehicle v
            WHERE (:type IS NULL OR v.type=:type)
            AND (:brand IS NULL OR :brand = '' OR v.brand=:brand)
            AND (:model IS NULL OR :model = '' OR v.model=:model)
            AND (:engine IS NULL OR v.engine=:engine)
            AND (:transmissionType IS NULL OR v.transmissionType=:transmissionType)
            AND (:color IS NULL OR :color = '' OR v.color=:color)
            AND (:minPrice IS NULL OR v.price >= :minPrice)
            AND (:maxPrice IS NULL OR v.price <= :maxPrice)
            AND (:minYear IS NULL OR YEAR(v.year) >= :minYear)
            AND (:maxYear IS NULL OR YEAR(v.year) <= :maxYear)
            """)
    List<Vehicle> search(@Param("type") VehicleType type,
                         @Param("brand") String brand,
                         @Param("model") String model,
                         @Param("engine") EngineType engine,
                         @Param("transmissionType") TransmissionType transmissionType,
                         @Param("color") String color,
                         @Param("minPrice") Integer minPrice,
                         @Param("maxPrice") Integer maxPrice,
                         @Param("minYear") Integer minYear,
                         @Param("maxYear") Integer maxYear);

    @Query("SELECT DISTINCT v.brand FROM Vehicle AS v ORDER BY v.brand")
    List<String> findAllBrands();

    @Query("SELECT DISTINCT v.model FROM Vehicle AS v ORDER BY v.model")
    List<String> findAllModels();

    @Query("SELECT DISTINCT v.color FROM Vehicle AS v ORDER BY v.color")
    List<String> findAllColors();
}
