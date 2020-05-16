package dxtr.familytree.model;

public class EnumUtility {
    public enum GENDER {
        MALE, FEMALE;
    }

    public enum RELATIONS {
        MATERNAL_UNCLE, PATERNAL_UNCLE, MATERNAL_AUNT, PATERNAL_AUNT, SISTER_IN_LAW, BROTHER_IN_LAW, SON, DAUGHTER, SIBLINGS;
    }

    public enum COMMANDS {
        GET_RELATIONSHIP, ADD_CHILD;
    }
}
