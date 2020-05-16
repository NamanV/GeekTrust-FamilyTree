package dxtr.familytree.utility;

public enum Error {
    CHILD_ADDITION_FAILED("CHILD_ADDITION_FAILED"),
    CHILD_ADDITION_SUCCEEDED("CHILD_ADDITION_SUCCEEDED"),
    PERSON_NOT_FOUND("PERSON_NOT_FOUND"),
    RELATION_NOT_FOUND("RELATION_NOT_FOUND");

    String errorMessage;
    Error(String errorMessage){
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage(){
        return this.errorMessage;
    }

}
