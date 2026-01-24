package Controller;

import Dto.Operation;
import Dto.TaxResult;
import Service.CapitalGainsService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/capital-gains")
@Validated
public class CapitalGainsController {

    private final CapitalGainsService service;

    public CapitalGainsController(CapitalGainsService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<List<TaxResult>> calculateTaxes(
            @RequestBody
            @jakarta.validation.constraints.NotEmpty(message = "The list of operations cannot be empty.")
            List<@Valid Operation> operations) {

        List<TaxResult> results = service.calculateTaxes(operations);

        return ResponseEntity.ok(results);
    }
}
