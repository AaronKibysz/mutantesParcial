package validator;

import dto.DnaPayLoad;
import exceptions.NullDnaException;

public class DnaValidator {
    public static void validateDnaStructure(DnaPayLoad dnaPayload) {
        String[] dna = retrieveDnaArray(dnaPayload);

        for (String strand : dna) {
            if (!strand.matches("[ATCG]+")) {
                throw new NullDnaException("El ADN contiene caracteres no permitidos. Solo se permiten A, T, C, G.");
            }
        }

        for (String strand : dna) {
            if (strand.matches(".*\\d.*")) {
                throw new NullDnaException("El ADN contiene números, lo cual no es válido.");
            }
        }
    }

    private static String[] retrieveDnaArray(DnaPayLoad dnaPayload) {
        String[] dna = dnaPayload.getDnaStrands();

        if (dna == null) {
            throw new NullDnaException("El array de ADN no puede ser null.");
        }

        if (dna.length == 0) {
            throw new NullDnaException("El array de ADN no puede estar vacío.");
        }

        int size = dna.length;

        for (String strand : dna) {
            if (strand == null) {
                throw new NullDnaException("El array de ADN contiene filas nulas.");
            }
            if (strand.length() != size) {
                throw new NullDnaException("El array de ADN debe ser NxN.");
            }
        }

        return dna;
    }
}
