package com.learn.todo_api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.Map;

// @JsonInclude: Solo incluye campos que NO son null en el JSON
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    private LocalDateTime timestamp;
    private int status;
    private String error;        // "Not Found", "Bad Request", etc.
    private String message;      // Mensaje legible para el cliente
    private String path;         // "/api/tasks/999"
    private Map<String, String> validationErrors;  // Para errores de validación

    public ErrorResponse(int status, String error, String message, String path) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    /**
     * // Resultado:
     * {
     *     "timestamp": "2024-01-15T10:30:00",
     *     "status": 404,
     *     "error": "Not Found",
     *     "message": "Task no encontrado con id: 5",
     *     "path": "/api/tasks/5"
     *     // ← NO hay validationErrors (no lo necesitamos)
     */

    public ErrorResponse(int status, String error, String message, String path, Map<String, String> validationErrors) {
        this(status, error, message, path);
        this.validationErrors = validationErrors;
    }

    /**
     * {
     *     "timestamp": "2024-01-15T10:30:00",
     *     "status": 400,
     *     "error": "Validation Error",
     *     "message": "Los datos enviados no son válidos",
     *     "path": "/api/tasks",
     *     "validationErrors": {        // ← SÍ aparece
     *         "title": "El título es obligatorio",
     *         "priority": "La prioridad máxima es 5"
     *     }
     */


    // === GETTERS Y SETTERS ===

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }

    public String getError() { return error; }
    public void setError(String error) { this.error = error; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getPath() { return path; }
    public void setPath(String path) { this.path = path; }

    public Map<String, String> getValidationErrors() { return validationErrors; }
    public void setValidationErrors(Map<String, String> validationErrors) {
        this.validationErrors = validationErrors;
    }
}