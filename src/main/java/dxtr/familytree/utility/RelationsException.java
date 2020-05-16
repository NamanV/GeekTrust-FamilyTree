package dxtr.familytree.utility;

public class RelationsException extends Exception {
    private Error error;

    public RelationsException(Error error){
        this.error = error;
    }

    @Override
    public String getMessage() {
        return error.getErrorMessage();
    }
}
