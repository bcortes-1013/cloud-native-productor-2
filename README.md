# 🕒 Microservicio Productor: Gestión de Horarios

API REST para la administración y actualización de los turnos de los recorridos.

## 📌 Funcionalidad Base
* **Rol:** Productor (Publisher).
* **Puerto:** 8081.
* **Endpoint:** `POST /api/v1/horarios`
* **Acción:** Recibe un JSON con el nuevo itinerario y lo envía a la cola `cola-horarios` en RabbitMQ.

## 🛠️ Stack Tecnológico
* **Java 17 & Spring Boot Web**
* **RabbitTemplate:** Para el envío de mensajes asíncronos.