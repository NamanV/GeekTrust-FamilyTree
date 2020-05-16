package dxtr.familytree.utility;

public class FamiltyTreeError extends Exception {

    private Error error;

    public FamiltyTreeError(Error error){
        this.error = error;
    }

    @Override
    public String getMessage() {
        return error.getErrorMessage();
    }
}

