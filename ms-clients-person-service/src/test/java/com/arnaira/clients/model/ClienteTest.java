package com.arnaira.clients.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ClienteTest {

    @Test
    void testCrearClienteConPersona(){
        Persona persona =new Persona();
        persona.setId(1L);
        persona.setNombre("Ana Rivera");
        persona.setGenero("F");
        persona.setEdad(27);
        persona.setDni("0801199908783");
        persona.setDireccion("Calle Prueba");
        persona.setTelefono("99999999");

        Cliente cliente =new Cliente();

        cliente.setId(10L);
        cliente.setClienteId("ARIVERA");
        cliente.setContrasena("1234");
        cliente.setPersona(persona);

        assertEquals(10,cliente.getId());
        assertEquals("ARIVERA",cliente.getClienteId());
        assertEquals("1234",cliente.getContrasena());

        assertNotNull(cliente.getPersona());
        assertEquals("Ana Rivera",cliente.getPersona().getNombre());
    }
}
