package dxtr.familytree.errors;

public class FamilyTreeException extends Exception {

    private Error error;

    public FamilyTreeException(Error error){
        this.error = error;
    }

    @Override
    public String getMessage() {
        return error.getErrorMessage();
    }
}

