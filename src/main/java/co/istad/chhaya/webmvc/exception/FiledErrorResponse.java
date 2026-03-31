package co.istad.chhaya.webmvc.exception;

public record FiledErrorResponse(
        String field,
        String reason
) {
}
