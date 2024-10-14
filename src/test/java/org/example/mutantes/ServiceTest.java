package org.example.mutantes;

import org.junit.jupiter.api.Test;
import services.AnalysisService;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ServiceTest {
    private final AnalysisService analysisService = new AnalysisService(null); // Simulamos el servicio sin necesidad de un repositorio real

    @Test
    public void testHorizontalMatches() {
        String[] dnaSample = {
                "AAAAGT",
                "CCTTGC",
                "ATCGTA",
                "GGGGAT",
                "TCAGCC",
                "TGCCTA"
        };
        assertTrue(analysisService.analyzeDnaSequences(dnaSample), "Should detect horizontal match");
    }

    @Test
    public void testVerticalMatches() {
        String[] dnaSample = {
                "ACTGCA",
                "CCTGCT",
                "GGTGTC",
                "ACTGTT",
                "CGTGGC",
                "TGCGGT"
        };
        assertTrue(analysisService.analyzeDnaSequences(dnaSample), "Should detect vertical match");
    }

    @Test
    public void testDiagonalRightMatches() {
        String[] dnaSample = {
                "ATGCGA",
                "CAGTAC",
                "TTGAGC",
                "AGAAGG",
                "CCCCTA",
                "TCACTG"
        }; // Diagonal de G's de arriba a la derecha
        assertTrue(analysisService.analyzeDnaSequences(dnaSample), "Should detect diagonal right match");
    }

    @Test
    public void testDiagonalLeftMatches() {
        String[] dnaSample = {
                "AAGTGA",
                "TGGTAA",
                "GTTTGC",
                "TGTCTC",
                "GCTTGG",
                "TGTTAC"
        };
        assertTrue(analysisService.analyzeDnaSequences(dnaSample), "Should detect diagonal left match");
    }

    @Test
    public void testRowAndColumnMatches() {
        String[] dnaSample = {
                "AAAAAA", // Fila con secuencia de 'A'
                "CCCCCC", // Columna con secuencia de 'C'
                "ATCGAT",
                "ATCGAT",
                "ATCGAT",
                "ATCGAT"
        };
        assertTrue(analysisService.analyzeDnaSequences(dnaSample), "Should detect row and column match");
    }

    @Test
    public void testAllDirectionalMatches() {
        String[] dnaSample = {
                "GTTGGA",
                "TCTTGA",
                "CGTGAA",
                "GTCGAT",
                "GCGTTC",
                "GCACTA"
        };
        assertTrue(analysisService.analyzeDnaSequences(dnaSample), "Should detect matches in all directions");
    }

    @Test
    public void testNonMutantSequence() {
        String[] dnaSample = {
                "ATCGAT",
                "CTCTTG",
                "CAAGGC",
                "GGTATT",
                "ATCGAT",
                "AAGTCC"
        };
        assertFalse(analysisService.analyzeDnaSequences(dnaSample), "Should not detect mutant sequences");
    }
}
