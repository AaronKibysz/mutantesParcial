package services;

import entities.DnaEntity;
import org.springframework.stereotype.Service;
import repositories.DNAStorageRepository;

import java.util.HashMap;
import java.util.Map;

@Service
public class AnalysisService {
    private final DNAStorageRepository dnaRepository;

    public AnalysisService(DNAStorageRepository dnaRepository) {
        this.dnaRepository = dnaRepository;
    }

    public boolean isMutant(String[] dna) {
        boolean mutantDetected = analyzeDnaSequences(dna);
        DnaEntity dnaRecord = new DnaEntity();
        dnaRecord.setDnaSequence(String.join(",", dna)); // Convierte el array en una cadena separada por comas
        dnaRecord.setMutant(mutantDetected);
        dnaRepository.save(dnaRecord);

        return mutantDetected;
    }

    public Map<String, Object> fetchStats() {
        long mutantCount = dnaRepository.countByMutantFlag(true);
        long humanCount = dnaRepository.countByMutantFlag(false);

        Map<String, Object> stats = new HashMap<>();
        stats.put("count_mutant_dna", mutantCount);
        stats.put("count_human_dna", humanCount);
        stats.put("ratio", humanCount > 0 ? (double) mutantCount / humanCount : 0);

        return stats;
    }

    // Cambiar de private a public para que sea accesible desde las pruebas
    public boolean analyzeDnaSequences(String[] dna) {
        int sequenceOccurrences = 0; // Contador de secuencias encontradas

        for (int i = 0; i < dna.length; i++) {
            for (int j = 0; j < dna.length; j++) {
                // Evitar el análisis en el bloque inferior derecho si ya no es necesario
                if (i > dna.length - 4 && j > dna.length - 4) {
                    break;
                } else {
                    // Verificar horizontal y diagonal derecha ↘
                    if (j <= dna.length - 4) {
                        if (sequencesHorizontal(dna, i, j) || sequencesDiagonalStraight(dna, i, j)) {
                            sequenceOccurrences++;
                            if (sequenceOccurrences == 2) {
                                return true; // Si se encuentran dos secuencias, es mutante
                            }
                        }
                    }
                    // Verificar vertical y diagonal izquierda ↙
                    if (i <= dna.length - 4 && (sequencesVertical(dna, i, j) || sequencesReverseDiagonal(dna, i, j))) {
                        sequenceOccurrences++;
                        if (sequenceOccurrences == 2) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    // Métodos auxiliares
    private boolean sequencesHorizontal(String[] dna, int row, int col) {
        if (col + 3 >= dna[row].length()) {
            return false;
        }
        boolean isSequence = dna[row].charAt(col) == dna[row].charAt(col + 1)
                && dna[row].charAt(col + 1) == dna[row].charAt(col + 2)
                && dna[row].charAt(col + 2) == dna[row].charAt(col + 3);
        System.out.println("Horizontal sequence found: " + isSequence + " at row " + row + " col " + col);
        return isSequence;
    }

    private boolean sequencesVertical(String[] dna, int row, int col) {
        if (row + 3 >= dna.length) {
            return false;
        }
        boolean isSequence = dna[row].charAt(col) == dna[row + 1].charAt(col)
                && dna[row + 1].charAt(col) == dna[row + 2].charAt(col)
                && dna[row + 2].charAt(col) == dna[row + 3].charAt(col);
        System.out.println("Vertical sequence found: " + isSequence + " at row " + row + " col " + col);
        return isSequence;
    }

    private boolean sequencesDiagonalStraight(String[] dna, int row, int col) {
        if (row + 3 >= dna.length || col + 3 >= dna[row].length()) {
            return false;
        }
        boolean isSequence = dna[row].charAt(col) == dna[row + 1].charAt(col + 1)
                && dna[row + 1].charAt(col + 1) == dna[row + 2].charAt(col + 2)
                && dna[row + 2].charAt(col + 2) == dna[row + 3].charAt(col + 3);
        System.out.println("Diagonal right sequence found: " + isSequence + " at row " + row + " col " + col);
        return isSequence;
    }

    private boolean sequencesReverseDiagonal(String[] dna, int row, int col) {
        if (row + 3 >= dna.length || col < 3) {
            return false;
        }
        boolean isSequence = dna[row].charAt(col) == dna[row + 1].charAt(col - 1)
                && dna[row + 1].charAt(col - 1) == dna[row + 2].charAt(col - 2)
                && dna[row + 2].charAt(col - 2) == dna[row + 3].charAt(col - 3);
        System.out.println("Diagonal left sequence found: " + isSequence + " at row " + row + " col " + col);
        return isSequence;
    }
}
