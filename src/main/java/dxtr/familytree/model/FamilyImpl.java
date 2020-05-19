package dxtr.familytree.model;

import dxtr.familytree.errors.Error;
import dxtr.familytree.interfaces.Family;
import dxtr.familytree.interfaces.Member;
import dxtr.familytree.errors.FamiltyTreeException;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

public class FamilyImpl implements Family {

    // Both are at the same hierarchy, can be done with just one of the two....
    private Member king, queen;

    public FamilyImpl(){

    }

    public FamilyImpl(Member king, Member queen){
        this.king = king;
        this.queen = queen;
    }

    @Override
    public Member findMember(String name) throws FamiltyTreeException {
        Queue<Member> members = new LinkedList<>();
        members.add(king);
        while(!members.isEmpty()){
           Member member = members.poll();
           if(member.getName().equalsIgnoreCase(name)){
               return member;
           }

           if(Objects.nonNull(member.getSpouse()) && member.getSpouse().getName().equalsIgnoreCase(name)){
               return member.getSpouse();
           }
           members.addAll(member.getChildren());
        }
        throw new FamiltyTreeException(Error.PERSON_NOT_FOUND);
    }

    @Override
    public List<Member> getRelatives(String name, String relation) throws FamiltyTreeException {
        List<Member> relatives = Relations.valueOf(relation).getRelatives(findMember(name));
        if(Objects.isNull(relatives) || relatives.isEmpty()){
            throw new FamiltyTreeException(Error.RELATION_NOT_FOUND);
        }
        return relatives;
    }

    @Override
    public Member addMember(Member newMember, Relations relation, Member toMember) throws FamiltyTreeException {
        return relation.addMember(newMember,toMember);
    }

    @Override
    public void addKing(Member member) {
        this.king = member;
    }

    @Override
    public void addQueen(Member member) {
        this.queen = member;
    }

    @Override
    public void printFamilyTree() {

    }
}

