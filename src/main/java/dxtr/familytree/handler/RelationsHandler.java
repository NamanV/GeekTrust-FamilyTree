package dxtr.familytree.handler;

import dxtr.familytree.interfaces.Member;
import dxtr.familytree.utility.EnumUtility.GENDER;
import dxtr.familytree.errors.Error;
import dxtr.familytree.errors.RelationsException;

import java.util.List;
import java.util.stream.Collectors;

public class RelationsHandler {

    public enum RelationType{
        MATERNAL, PATERNAL, SIBLING_IN_LAWS;
    }
    public static List<Member> getUnclesOrAunt(Member ofMember, RelationType realtionType, GENDER gender) throws RelationsException{
        Member parent;
        switch (realtionType){
            case MATERNAL:
                parent = ofMember.getParent(GENDER.FEMALE);
                break;
            case PATERNAL:
                parent = ofMember.getParent(GENDER.MALE);
                break;
            default:
                throw new RelationsException(Error.RELATION_NOT_FOUND);
        }

        Member grandMother = parent.getParent(GENDER.FEMALE);
        List<Member> unclesOrAunt =
                grandMother.getChildren().stream().filter(sibling -> sibling.getGender().equals(gender)).collect(Collectors.toList());
        return unclesOrAunt;
    }

    public List<Member> getInLawsSiblings(Member ofMember, RelationType relationType, GENDER gender){
        return null;
    }

    public List<Member> getChildren(Member ofMember, RelationType relationType, GENDER gender){
        return ofMember.getChildren().stream().filter(child -> child.getGender().equals(gender)).collect(Collectors.toList());
    }
}
