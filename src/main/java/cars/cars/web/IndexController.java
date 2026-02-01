package cars.cars.web;

import cars.cars.entities.*;
import cars.cars.exceptions.UsernameTakenException;
import cars.cars.services.UserServiceImpl;
import cars.cars.services.VehicleServiceImpl;
import cars.cars.web.dto.UserLoginDTO;
import cars.cars.web.dto.UserRegistrationDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
public class IndexController {

    private final UserServiceImpl userService;
    private final VehicleServiceImpl vehicleService;

    @GetMapping("/")
    public ModelAndView getIndexPage() {
        ModelAndView modelAndView = new ModelAndView("index");

        modelAndView.addObject("welcomeMessage", "Welcome to Cars!");
        modelAndView.addObject("type", vehicleService.getAllTypes());
        modelAndView.addObject("brand", vehicleService.getAllBrands());
        modelAndView.addObject("model", vehicleService.getAllModels());
        modelAndView.addObject("engine", vehicleService.getAllEngines());
        modelAndView.addObject("transmission", vehicleService.getAllTransmissions());
        modelAndView.addObject("color", vehicleService.getAllColors());

        int currentYear = LocalDate.now().getYear();
        List<Integer> years = new ArrayList<>();
        for (int i = currentYear; i >= 1920; i--) {
            years.add(i);
        }
        modelAndView.addObject("years", years);
        return modelAndView;
    }

    @GetMapping("/vehicles")
    public ModelAndView showVehicles(
            @RequestParam(required = false) VehicleType type,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String model,
            @RequestParam(required = false) EngineType engine,
            @RequestParam(required = false) TransmissionType transmissionType,
            @RequestParam(required = false) String color,
            @RequestParam(required = false) Integer minPrice,
            @RequestParam(required = false) Integer maxPrice,
            @RequestParam(required = false) Integer minYear,
            @RequestParam(required = false) Integer maxYear
    ) {
        ModelAndView modelAndView = new ModelAndView("vehicles");
        List<Vehicle> vehicles = vehicleService.searchVehicle(type, brand, model,
                engine, transmissionType, color, minPrice, maxPrice, minYear, maxYear);
        modelAndView.addObject("vehicles", vehicles);

        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView showLoginPage() {
        ModelAndView mav = new ModelAndView("login");
        mav.addObject("loginDTO", new UserLoginDTO());
        return mav;
    }

    @GetMapping("/register")
    public ModelAndView showRegisterPage() {
        ModelAndView mav = new ModelAndView("register");
        mav.addObject("registerDTO", new UserRegistrationDTO());
        return mav;
    }

    @PostMapping("/register")
    public ModelAndView registerUser(@Valid @ModelAttribute("registerDTO") UserRegistrationDTO registrationDTO,
                                     BindingResult bindingResult,
                                     RedirectAttributes redirectAttributes) {
        ModelAndView mav = new ModelAndView("register");
        if (bindingResult.hasErrors()) {
            return mav;
        }

        if (!registrationDTO.getPassword().equals(registrationDTO.getConfirmPassword())) {
            mav.addObject("passwordMismatch", "Password do not match!");
        }

        try {
            userService.register(registrationDTO);
        } catch (UsernameTakenException e) {
            mav.addObject("usernameTakenException", "Username already taken!");
        }

        redirectAttributes.addFlashAttribute("success", "Registration Successful!");
        return new ModelAndView("redirect:/login");

    }
}
