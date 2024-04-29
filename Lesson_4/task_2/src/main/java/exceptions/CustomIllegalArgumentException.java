package exceptions;

import enums.Type;

public class CustomIllegalArgumentException extends Exception {
    private String owner;
    private Type type;


    public CustomIllegalArgumentException(String owner, Type type) {
        super();
        this.owner = owner;
        this.type = type;
    }

    @Override
    public String getMessage() {
        return String.format("Не удалось завершить операцию по созданию аккаунта с типом %s у %s.", type, owner);
    }

}
