package com.learn.todo_api.exceptions;

import com.learn.todo_api.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

// @RestControllerAdvice = @ControllerAdvice + @ResponseBody
// Intercepta TODAS las excepciones de TODOS los controllers
@RestControllerAdvice
public class GlobalExceptionHandler {

    // ═══════════════════════════════════════════════════════════════
    // MANEJA: ResourceNotFoundException
    // CUANDO: No se encuentra una tarea, usuario, etc.
    // RETORNA: 404 Not Found
    // ═══════════════════════════════════════════════════════════════
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(
            ResourceNotFoundException ex,
            HttpServletRequest request) {

        ErrorResponse error = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),      // 404
                "Not Found",
                ex.getMessage(),                    // "Task no encontrado con id: 123"
                request.getRequestURI()             // "/api/tasks/123"
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    // ═══════════════════════════════════════════════════════════════
    // MANEJA: BadRequestException
    // CUANDO: El cliente envía datos inválidos (lógica de negocio)
    // RETORNA: 400 Bad Request
    // ═══════════════════════════════════════════════════════════════
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(
            BadRequestException ex,
            HttpServletRequest request) {

        ErrorResponse error = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),    // 400
                "Bad Request",
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    // ═══════════════════════════════════════════════════════════════
    // MANEJA: MethodArgumentNotValidException
    // CUANDO: Fallan las validaciones del DTO (@NotBlank, @Size, etc.)
    // RETORNA: 400 Bad Request + lista de errores por campo
    // ═══════════════════════════════════════════════════════════════
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationErrors(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {

        // Extraemos los errores de validación campo por campo
        Map<String, String> validationErrors = new HashMap<>();

        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            // "title" → "El título es obligatorio"
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        ErrorResponse error = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Validation Error",
                "Los datos enviados no son válidos",
                request.getRequestURI(),
                validationErrors                    // ← Incluimos el mapa de errores
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    // ═══════════════════════════════════════════════════════════════
    // MANEJA: Exception (cualquier otra excepción no controlada)
    // CUANDO: Error inesperado del servidor
    // RETORNA: 500 Internal Server Error
    // ═══════════════════════════════════════════════════════════════
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(
            Exception ex,
            HttpServletRequest request) {

        // ⚠️ En producción: loguear el error real pero NO exponerlo al cliente
        // log.error("Error inesperado: ", ex);

        ErrorResponse error = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),  // 500
                "Internal Server Error",
                "Ha ocurrido un error inesperado. Por favor, intente más tarde.",
                request.getRequestURI()
                // ¡NUNCA exponemos ex.getMessage() ni el stack trace!
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

}
