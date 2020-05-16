package dxtr.familytree.errors;

public class FamiltyTreeException extends Exception {

    private Error error;

    public FamiltyTreeException(Error error){
        this.error = error;
    }

    @Override
    public String getMessage() {
        return error.getErrorMessage();
    }
}

