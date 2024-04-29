package exceptions;

public class OwnerNotFoundException extends Exception {
    private final String name;

    public OwnerNotFoundException(String name) {
        super();
        this.name = name;
    }

    @Override
    public String getMessage() {
        return String.format("Аккаунт принадлежащий пользователю %s не найден!", name);
    }
}