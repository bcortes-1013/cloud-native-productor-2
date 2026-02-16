package com.cloud.ms_productor;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/api/horarios") // Ruta específica
public class MsProductorApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsProductorApplication.class, args);
    }

    @Autowired
    private RabbitTemplate rabbitTemplate;
    
    private static final String COLA = "cola-horarios";

    // 1. GET: Listar (Simulado)
    @GetMapping
    public List<String> listar() {
        return List.of("08:00 - Ruta A", "09:30 - Ruta B");
    }

    // 2. POST: Crear Horario
    @PostMapping
    public String crear(@RequestBody String json) {
        rabbitTemplate.convertAndSend(COLA, json);
        return "Horario CREADO y enviado a cola.";
    }

    // 3. PUT: Actualizar
    @PutMapping("/{id}")
    public String actualizar(@PathVariable String id, @RequestBody String json) {
        // En un caso real, aquí iría lógica de negocio
        rabbitTemplate.convertAndSend(COLA, json); 
        return "Horario " + id + " ACTUALIZADO y notificado.";
    }

    // 4. DELETE: Eliminar
    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable String id) {
        String json = "{ \"accion\": \"ELIMINAR\", \"id\": \"" + id + "\" }";
        rabbitTemplate.convertAndSend(COLA, json);
        return "Horario " + id + " ELIMINADO y notificado.";
    }
}