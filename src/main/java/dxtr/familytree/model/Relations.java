package dxtr.familytree.model;

import dxtr.familytree.errors.Error;
import dxtr.familytree.errors.FamiltyTreeException;
import dxtr.familytree.errors.RelationsException;
import dxtr.familytree.handler.RelationsHandler;
import dxtr.familytree.interfaces.Member;
import dxtr.familytree.utility.EnumUtility.GENDER;

import java.util.List;
import java.util.stream.Collectors;

public enum Relations {

    MATERNAL_UNCLE {
        @Override
        public List<Member> getRelatives(Member ofMember) throws RelationsException {
            return RelationsHandler.getUnclesOrAunt(ofMember, RelationsHandler.RelationType.MATERNAL, GENDER.MALE);
        }
    }, PATERNAL_UNCLE {
        @Override
        public List<Member> getRelatives(Member ofMember) throws RelationsException {
            return RelationsHandler.getUnclesOrAunt(ofMember, RelationsHandler.RelationType.PATERNAL, GENDER.MALE);
        }
    }, MATERNAL_AUNT {
        @Override
        public List<Member> getRelatives(Member ofMember) throws RelationsException {
            return RelationsHandler.getUnclesOrAunt(ofMember, RelationsHandler.RelationType.MATERNAL, GENDER.FEMALE);
        }

    }, PATERNAL_AUNT {
        @Override
        public List<Member> getRelatives(Member ofMember) throws RelationsException {
            return RelationsHandler.getUnclesOrAunt(ofMember, RelationsHandler.RelationType.PATERNAL, GENDER.FEMALE);
        }

    }, SISTER_IN_LAW {
        @Override
        public List<Member> getRelatives(Member ofMember) {
            return RelationsHandler.getInLawsSiblings(ofMember, RelationsHandler.RelationType.SIBLING_IN_LAWS, GENDER.FEMALE);
        }

    }, BROTHER_IN_LAW {
        @Override
        public List<Member> getRelatives(Member ofMember) {
            return RelationsHandler.getInLawsSiblings(ofMember, RelationsHandler.RelationType.SIBLING_IN_LAWS, GENDER.MALE);
        }

    }, SON {
        @Override
        public List<Member> getRelatives(Member ofMember) {
            return ofMember.getChildren().stream().filter(child -> child.getGender().equals(GENDER.MALE)).collect(Collectors.toList());
        }

    }, DAUGHTER {
        @Override
        public List<Member> getRelatives(Member ofMember) {
            return ofMember.getChildren().stream().filter(child -> child.getGender().equals(GENDER.FEMALE)).collect(Collectors.toList());
        }

    }, SIBLINGS {
        @Override
        public List<Member> getRelatives(Member ofMember) {
            return null;
        }
    },
    NONE{
        @Override
        public List<Member> getRelatives(Member ofMember) throws RelationsException {
            throw new RelationsException(Error.PERSON_NOT_FOUND);
        }
    };

    public abstract List<Member> getRelatives(Member ofMember) throws RelationsException;

}
