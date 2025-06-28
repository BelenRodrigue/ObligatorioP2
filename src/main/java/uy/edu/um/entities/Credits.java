package uy.edu.um.entities;

import uy.edu.um.tad.linkedlist.MyLinkedListImpl;

public class Credits {
    private int id;
    private MyLinkedListImpl<CastMember> castMembers = new MyLinkedListImpl<>();
    private MyLinkedListImpl<CrewMember> crewMembers = new MyLinkedListImpl<>();

    public Credits(int id, MyLinkedListImpl<CastMember> castMembers, MyLinkedListImpl<CrewMember> crewMembers) {
        this.id = id;
        this.castMembers = castMembers;
        this.crewMembers = crewMembers;
    }
}
