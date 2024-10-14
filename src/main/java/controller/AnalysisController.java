package controller;

import dto.DnaPayLoad;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.AnalysisService;
import validator.DnaValidator;

@RestController
@RequestMapping("/mutant")
public class AnalysisController {
    private final AnalysisService analysisService;

    public AnalysisController(AnalysisService analysisService) {
        this.analysisService = analysisService;
    }

    @PostMapping("/")
    public ResponseEntity<?> analyzeDna(@RequestBody DnaPayLoad dnaPayload) {
        DnaValidator.validateDnaStructure(dnaPayload);
        boolean mutantDetected = analysisService.isMutant(dnaPayload.getDnaStrands());
        if (mutantDetected) {
            return ResponseEntity.ok().build(); // HTTP 200 OK
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // HTTP 403 Forbidden
        }
    }

    @GetMapping("/stats")
    public ResponseEntity<?> retrieveStats() {
        return ResponseEntity.ok(analysisService.fetchStats());
    }
}
