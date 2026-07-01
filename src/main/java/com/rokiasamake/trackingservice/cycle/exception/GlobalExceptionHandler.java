package com.rokiasamake.trackingservice.cycle.exception;
import com.rokiasamake.trackingservice.cycle.dto.response.ApiResponse;
import com.rokiasamake.trackingservice.emotionJournal.exception.EmotionJournalNotFoundException;
import com.rokiasamake.trackingservice.foodJournal.exception.FoodJournalNotFoundException;
import com.rokiasamake.trackingservice.symptom.exception.CycleNotCompletedException;
import com.rokiasamake.trackingservice.symptom.exception.CycleNotFoundException;
import com.rokiasamake.trackingservice.symptom.exception.SymptomNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CycleProfileAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<Void>> handleCycleProfileAlreadyExists(
            CycleProfileAlreadyExistsException ex) {

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ApiResponse.error(ex.getMessage()));
    }

    @ExceptionHandler(CycleProfileNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleCycleProfileNotFound(
            CycleProfileNotFoundException ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error(ex.getMessage()));
    }

    @ExceptionHandler(MenstrualCycleNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleMenstrualCycleNotFound(
            MenstrualCycleNotFoundException ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error(ex.getMessage()));
    }

    @ExceptionHandler(CycleAlreadyOngoingException.class)
    public ResponseEntity<ApiResponse<Void>> handleCycleAlreadyOngoing(
            CycleAlreadyOngoingException ex) {

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ApiResponse.error(ex.getMessage()));
    }

    @ExceptionHandler(CycleAlreadyCompletedException.class)
    public ResponseEntity<ApiResponse<Void>> handleCycleAlreadyCompleted(
            CycleAlreadyCompletedException ex) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(ex.getMessage()));
    }

    @ExceptionHandler(InvalidCycleDatesException.class)
    public ResponseEntity<ApiResponse<Void>> handleInvalidCycleDates(
            InvalidCycleDatesException ex) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidation(
            MethodArgumentNotValidException ex) {

        String message = ex.getBindingResult()
                .getFieldErrors()
                .getFirst()
                .getDefaultMessage();

        return ResponseEntity.badRequest()
                .body(ApiResponse.error(message));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleException(
            Exception ex) {

        return ResponseEntity.internalServerError()
                .body(ApiResponse.error(ex.getMessage()));
    }
    @ExceptionHandler(CycleNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleCycleNotFound(
            CycleNotFoundException ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error(ex.getMessage()));
    }

    @ExceptionHandler(SymptomNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleSymptomNotFound(
            SymptomNotFoundException ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error(ex.getMessage()));
    }

    @ExceptionHandler(CycleNotCompletedException.class)
    public ResponseEntity<ApiResponse<Void>> handleCycleNotCompleted(
            CycleNotCompletedException ex) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(ex.getMessage()));
    }
    @ExceptionHandler(FoodJournalNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleFoodJournalNotFound(
            FoodJournalNotFoundException ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error(ex.getMessage()));
    }
    @ExceptionHandler(EmotionJournalNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleEmotionJournalNotFound(
            EmotionJournalNotFoundException ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error(ex.getMessage()));
    }

}