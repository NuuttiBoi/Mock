import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*; 


public class TilaustenKasittelyMockitoTest {
@Mock
 IHinnoittelija hinnoittelijaMock;
 @BeforeEach
 public void setup() {
	 MockitoAnnotations.openMocks(this);
 }
 @Test
 public void testaaKäsittelijäWithMockitoHinnoittelija() {
 // Arrange
 float alkuSaldo = 100.0f;
 float listaHinta = 30.0f;
 float alennus = 20.0f;
 float loppuSaldo = alkuSaldo - (listaHinta * (1 - alennus / 100));
 
 Asiakas asiakas = new Asiakas(alkuSaldo);
 Tuote tuote = new Tuote("TDD in Action", listaHinta);
 
 // Record
 when(hinnoittelijaMock.getAlennusProsentti(asiakas, tuote))
 .thenReturn(alennus);
 
 
 // Act
 TilaustenKäsittely käsittelijä = new TilaustenKäsittely();
 käsittelijä.setHinnoittelija(hinnoittelijaMock);
 käsittelijä.käsittele(new Tilaus(asiakas, tuote));
 
 // Assert
 assertEquals(loppuSaldo, asiakas.getSaldo(), 0.001);
 
 verify(hinnoittelijaMock, times(2)).getAlennusProsentti(asiakas, tuote);
 }
 @Test
 public void testaaKäsittelijäWithMockYli100() {
	 
     // Arrange
     float alkuSaldo = 100.0f;
     float listaHinta = 120.0f;
     float alennus = 5.0f;
     float loppuSaldo = alkuSaldo - (listaHinta * (1 - alennus  / 100));

     Asiakas asiakas = new Asiakas(alkuSaldo);
     Tuote tuote = new Tuote("TDD in Action", listaHinta);

     // Record
     when(hinnoittelijaMock.getAlennusProsentti(asiakas, tuote))
     .thenReturn(alennus);
     System.out.println("alennus " + alennus);
     
     // Act
     TilaustenKäsittely käsittelijä = new TilaustenKäsittely();
     käsittelijä.setHinnoittelija(hinnoittelijaMock);
     käsittelijä.käsittele(new Tilaus(asiakas, tuote));
     
     // Assert
     assertEquals(loppuSaldo, asiakas.getSaldo(), 0.001);
 }
 @Test
 public void testaaKäsittelijäWithMockAlle100() {
     // Arrange
     float alkuSaldo = 100.0f;
     float listaHinta = 80.0f;
     float alennus = 20.0f;
     float loppuSaldo = alkuSaldo - (listaHinta * (1 - alennus / 100));
     
     Asiakas asiakas = new Asiakas(alkuSaldo);
     Tuote tuote = new Tuote("TDD in Action", listaHinta);
     
     // Record
     when(hinnoittelijaMock.getAlennusProsentti(asiakas, tuote))
     .thenReturn(alennus);
     
     // Act
     TilaustenKäsittely käsittelijä = new TilaustenKäsittely();
     käsittelijä.setHinnoittelija(hinnoittelijaMock);
     käsittelijä.käsittele(new Tilaus(asiakas, tuote));
     
     // Assert
     assertEquals(loppuSaldo, asiakas.getSaldo(), 0.001);
 }
}

