package dxtr.familytree.errors;

public enum Error {
    CHILD_ADDITION_FAILED("CHILD_ADDITION_FAILED"),
    CHILD_ADDITION_SUCCEEDED("CHILD_ADDITION_SUCCEEDED"),
    PERSON_NOT_FOUND("PERSON_NOT_FOUND"),
    RELATION_NOT_FOUND("NONE"),
    NOT_IMPLEMENTED("FUNCTIONALITY_NOT_YET_IMPLEMENTED");

    String errorMessage;
    Error(String errorMessage){
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage(){
        return this.errorMessage;
    }

}
