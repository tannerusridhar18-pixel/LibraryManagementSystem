package service;

import exception.MemberNotFoundException;
import exception.MemberAlreadyExistException;

import java.util.ArrayList;
import model.Member;

public class MemberService {
    private ArrayList<Member> members;

    public MemberService(){
        members = new ArrayList<>();
    }

    private boolean memberExist(long memberId){
        for(Member member : members){
            if(member.getMemberId() == memberId)
                return true;
        }
        return false;
    }

    public void addMember(Member member) throws MemberAlreadyExistException {
        if(member == null){
            throw new IllegalArgumentException("Member cannot be null");
        }
        if(memberExist(member.getMemberId())){
            throw new MemberAlreadyExistException("Member of ID: " + member.getMemberId() + "already exist." );
        }
        members.add(member);
    }

    public void removeMember(long memberId) throws MemberNotFoundException{
        Member member = findMemberById(memberId);
        members.remove(member);
    }

    public void displayMembers(){
        if(members.isEmpty()){
            System.out.println("No members found in the Library");
        } else {
            for(Member member : members){
                System.out.print(member);
            }
        }
    }

    public Member findMemberById(long memberid) throws MemberNotFoundException {

        for(Member member : members){
            if(member.getMemberId() == memberid){
                return member;
            }
        }
        
        throw new MemberNotFoundException("Member with Id:"+memberid + "not found.");
    }

}

