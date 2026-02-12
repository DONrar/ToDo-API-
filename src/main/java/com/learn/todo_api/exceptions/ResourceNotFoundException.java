package com.learn.todo_api.exceptions;

// Excepción para cuando no se encuentra un recurso
// Extendemos RuntimeException para que sea "unchecked"
public class ResourceNotFoundException extends RuntimeException {

    private final String resourceName;  // "Task", "User", etc.
    private final String fieldName;     // "id", "email", etc.
    private final Object fieldValue;    // 123, "test@mail.com", etc.

    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        // Mensaje: "Task no encontrado con id: 123"
        super(String.format("%s no encontrado con %s: '%s'", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    // Getters
    public String getResourceName() { return resourceName; }
    public String getFieldName() { return fieldName; }
    public Object getFieldValue() { return fieldValue; }
}