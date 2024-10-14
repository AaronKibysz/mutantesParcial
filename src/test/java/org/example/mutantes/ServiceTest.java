package org.example.mutantes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import services.AnalysisService;
import services.AnalysisService.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ServiceTest {
    private final AnalysisService analysisService = new AnalysisService(null); // Simulamos el servicio sin necesidad de un repositorio real

    // Verifica coincidencias en filas
    @Test
    public void testHorizontalMatches(){
        String[] dnaSample = {
                "AAAAGT",
                "CCTTGC",
                "ATCGTA",
                "GGGGAT",
                "TCAGCC",
                "TGCCTA"
        };
        assertTrue(analysisService.analyzeDnaSequences(dnaSample));
    }

    // Verifica coincidencias en columnas
    @Test
    public void testVerticalMatches(){
        String[] dnaSample = {
                "ACTGCA",
                "CCTGCT",
                "GGTGTC",
                "ACTGTT",
                "CGTGGC",
                "TGCGGT"
        };
        assertTrue(analysisService.analyzeDnaSequences(dnaSample));
    }

    // Verifica coincidencias en las diagonales principales ↘
    @Test
    public void testDiagonalRightMatches(){
        String[] dnaSample = {
                "TTAAGG",
                "GTCCTC",
                "TGTGTC",
                "TCTGGT",
                "GTGACC",
                "TCTTGA"
        };
        assertTrue(analysisService.analyzeDnaSequences(dnaSample));
    }

    // Verifica coincidencias en las diagonales inversas ↙
    @Test
    public void testDiagonalLeftMatches(){
        String[] dnaSample = {
                "AAGTGA",
                "TGGTAA",
                "GTTTGC",
                "TGTCTC",
                "GCTTGG",
                "TGTTAC"
        };
        assertTrue(analysisService.analyzeDnaSequences(dnaSample));
    }

    // Verifica coincidencias tanto en filas como en columnas
    @Test
    public void testRowAndColumnMatches(){
        String[] dnaSample = {
                "CGATGC",
                "GGTGCT",
                "TCTCCT",
                "TCCGAT",
                "GGTCGC",
                "TCTGGT"
        };
        assertTrue(analysisService.analyzeDnaSequences(dnaSample));
    }

    // Verifica coincidencias en todas las direcciones (filas, columnas, diagonales)
    @Test
    public void testAllDirectionalMatches(){
        String[] dnaSample = {
                "GTTGGA",
                "TCTTGA",
                "CGTGAA",
                "GTCGAT",
                "GCGTTC",
                "GCACTA"
        };
        assertTrue(analysisService.analyzeDnaSequences(dnaSample));
    }

    // Verifica una secuencia que no es mutante
    @Test
    public void testNonMutantSequence(){
        String[] dnaSample = {
                "ATCGAT",
                "CTCTTG",
                "CAAGGC",
                "GGTATT",
                "ATCGAT",
                "AAGTCC"
        };
        assertFalse(analysisService.analyzeDnaSequences(dnaSample));
    }
}
